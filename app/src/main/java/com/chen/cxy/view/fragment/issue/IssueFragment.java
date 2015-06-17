package com.chen.cxy.view.fragment.issue;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.http.entity.MultipartEntity;
import com.ab.http.entity.mine.content.ContentBody;
import com.ab.http.entity.mine.content.FileBody;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.chen.cxy.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@SuppressLint("ValidFragment")
//解决构造方法Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead
public class IssueFragment extends Fragment implements View.OnClickListener {

    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;//保存临时文件
    private String tempPath;//临时文件路径
    String url;
    private Bitmap bitmap;

    private DialogFragment mAlertDialog = null;
    private AbHttpUtil mAbHttpUtil = null;

    Button upload;
    Button btn;
    ImageView img;
    LayoutInflater inflater;
    Context context;
    Dialog dialog;

    Button gallery_btn;
    Button camera_btn;
    Button cancel_btn;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER 多余
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters 多余
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DynamicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IssueFragment newInstance(String param1, String param2) {
        //多余的
        IssueFragment fragment = new IssueFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public IssueFragment() {
        // Required empty public constructor
    }

    public IssueFragment(Context context) {
        this.context = context;//用于给dialog使用
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //多余的
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflater = inflater;

        //获取朋友(聊天)的fragment,然后再往里面的Fragment添加ListViewFragment
        View view = inflater.inflate(R.layout.fragment_issue, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        upload = (Button) view.findViewById(R.id.fi_upload);
        btn = (Button) view.findViewById(R.id.fi_btn);
        img = (ImageView) view.findViewById(R.id.fi_img);

        btn.setOnClickListener(this);
        upload.setOnClickListener(this);

        //获取Http工具类
        mAbHttpUtil = AbHttpUtil.getInstance(context);

        dialog = getDialog();

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fi_btn:

                // Toast.makeText(context,"测试",Toast.LENGTH_SHORT).show();
                dialog.show();
                break;
            case R.id.fi_upload:

                upload(v);
                // tempFile.delete();//上传完后删除临时图片
                break;

            case R.id.dpc_gallery:
                // 激活系统图库，选择一张图片
                gallery(v);
                break;
            case R.id.dpc_camera:
                camera(v);
                break;
            case R.id.dpc_cancel:
                dialog.cancel();
                break;


        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (hasSdcard()) {

                tempFile = new File(Environment.getExternalStorageDirectory(),
                        PHOTO_FILE_NAME);
                //剪切前的图片不要
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(context, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            try {
               // bitmap = data.getParcelableExtra("data");
                //File file = new File(tempPath);
                //this.img.setImageBitmap(bitmap);
                Uri uri = data.getData();
                tempPath = getRealPathFromURI(uri);
                bitmap =MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                this.img.setImageBitmap(bitmap);
                // 上传完后再进行删除
                //File f = new File(tempPath);
                //boolean delete = f.delete();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        dialog.cancel();
        super.onActivityResult(requestCode, resultCode, data);
    }

    //获取文件地址
    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    /*
     * 上传图片
	 */
    public void upload(View v) {

        //已经在后台上传
       /* if(mAlertDialog!=null){
            mAlertDialog.show(getFragmentManager(), "dialog");
            return;
        }*/
        //url = "http://192.168.1.103:8080/core/cxyUserController.do?saveFile";
        url = "http://192.168.1.103:8080/core/UploadPhotoServlet";
        AbRequestParams params = new AbRequestParams();

        try {
            //多文件上传添加多个即可
            params.put("data1", URLEncoder.encode("如果包含中文的处理方式", HTTP.UTF_8));
            params.put("data2", "100");
            //文件参数，去掉后边那个按钮
            File file = new File(tempPath);
            if (!file.isFile()) {
                AbToastUtil.showToast(context, "上传文件不存在");
            }
            //params.put(file.getName(), file);
            params.put(file.getName(), file.getName(), getByte(file));//保存成功

           /* new Thread(){

                @Override

                public void run() {

                    try {
                    //post(tempPath, url);
                        uploadFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //handler.sendEmptyMessage(0);

                }

            }.start();*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        mAbHttpUtil.post(url, params, new AbStringHttpResponseListener() {


            @Override
            public void onSuccess(int statusCode, String content) {
                AbToastUtil.showToast(context, content);
            }

            // 开始执行前
            @Override
            public void onStart() {
                AbToastUtil.showToast(context, "正在上传");
            }

            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                AbToastUtil.showToast(context, error.getMessage());
            }

            // 进度
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            // 完成后调用，失败，成功，都要调用
            public void onFinish() {
                //下载完成取消进度框
                if (mAlertDialog != null) {
                    mAlertDialog.dismiss();
                    mAlertDialog = null;
                }
            }

            ;


        });

    }

    private void uploadFile()
    {
        String end ="\r\n";
        String twoHyphens ="--";
        String boundary ="*****";
        try
        {
            URL url =new URL("http://192.168.1.103:8080/core/UploadPhotoServlet");
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
          /* 允许Input、Output，不使用Cache */
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
          /* 设置传送的method=POST */
            con.setRequestMethod("POST");
          /* setRequestProperty */
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary="+boundary);
          /* 设置DataOutputStream */
            DataOutputStream ds =
                    new DataOutputStream(con.getOutputStream());
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; "+
                    "name=\"file1\";filename=\""+
                    "img.png" +"\""+ end);
            ds.writeBytes(end);
          /* 取得文件的FileInputStream */
            FileInputStream fStream =new FileInputStream(tempPath);
          /* 设置每次写入1024bytes */
            int bufferSize =1024;
            byte[] buffer =new byte[bufferSize];
            int length =-1;
          /* 从文件读取数据至缓冲区 */
            while((length = fStream.read(buffer)) !=-1)
            {
            /* 将资料写入DataOutputStream中 */
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
          /* close streams */
            fStream.close();
            ds.flush();
          /* 取得Response内容 */
            InputStream is = con.getInputStream();
            int ch;
            StringBuffer b =new StringBuffer();
            while( ( ch = is.read() ) !=-1 )
            {
                b.append( (char)ch );
            }
          /* 将Response显示于Dialog */
            AbToastUtil.showToast(context, "上传成功");
          /* 关闭DataOutputStream */
            ds.close();
        }
        catch(Exception e)
        {
            //showDialog("上传失败"+e);
        }
    }

    /**
     * 　　* 把一个文件转化为字节
     * <p/>
     * 　　* @param file
     * <p/>
     * 　　* @return byte[]
     * <p/>
     * 　　* @throws Exception
     * <p/>
     *
     */
    public static byte[] getByte(File file) throws Exception{
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;

}

    /*
         * 从相册获取
         */
    public void gallery(View view) {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /*
     * 从相机获取
     */
    public void camera(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", false);// true:不返回uri，false：返回uri
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    private void initPhoto(View v) {
        gallery_btn = (Button) v.findViewById(R.id.dpc_gallery);
        camera_btn = (Button) v.findViewById(R.id.dpc_camera);
        cancel_btn = (Button) v.findViewById(R.id.dpc_cancel);

        gallery_btn.setOnClickListener(this);
        camera_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
    }


    private Dialog getDialog() {
        View view = inflater.inflate(R.layout.dialog_photo_choose, null);
        initPhoto(view);
        Dialog dialog = new Dialog(context, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);

        return dialog;
    }
}
