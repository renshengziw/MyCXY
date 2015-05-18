package com.chen.cxy.view.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.fragment.AbAlertDialogFragment;
import com.ab.soap.AbSoapListener;
import com.ab.soap.AbSoapParams;
import com.ab.soap.AbSoapUtil;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.chen.cxy.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * 登录控制
 */
public class LoginActivity extends AbActivity{

    private EditText userName;
    private EditText password;
    private Button loginBtn;
    private AbSoapUtil mAbSoapUtil = null;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setAbContentView(R.layout.login);

        init();

        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);//默认
        sharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String u = sharedPreferences.getString("userName", ""); //""为没取到值的默认值
        //不保存密码


        //如果用户名和密码不为空 则直接登录
        if(!AbStrUtil.isEmpty(u)){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void init(){
        //获取Http工具类
        mAbSoapUtil = AbSoapUtil.getInstance(this);
        mAbSoapUtil.setTimeout(10000);
        userName = (EditText)this.findViewById(R.id.userName);
        password = (EditText)this.findViewById(R.id.userName);
        loginBtn = (Button)this.findViewById(R.id.login_btn);
        //userName.setOnClickListener(this);
        //password.setOnClickListener(this);
        //loginBtn.setOnClickListener(this);
        
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = "http://192.168.1.103:8088/services/cxy?wsdl";
                String nameSpace = "http://webservice.cxy/";
                String methodName = "login";
                AbSoapParams params = new AbSoapParams();
                params.put("userName", userName.getText().toString());
                params.put("password", password.getText().toString());

                mAbSoapUtil.call(urlString,nameSpace,methodName,params,new AbSoapListener(){

                    //获取数据成功会调用这里
                    @Override
                    public void onSuccess(int statusCode, SoapObject object) {

                        AbDialogUtil.showAlertDialog(LoginActivity.this,"返回结果",object.toString(),new AbAlertDialogFragment.AbDialogOnClickListener(){

                            @Override
                            public void onNegativeClick() {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void onPositiveClick() {
                                // TODO Auto-generated method stub

                            }


                        });
                    }

                    // 失败，调用
                    @Override
                    public void onFailure(int statusCode, String content,
                                          Throwable error) {

                        AbToastUtil.showToast(LoginActivity.this, error.getMessage());
                    }

                    // 失败，调用
                    @Override
                    public void onFailure(int statusCode, SoapFault fault) {
                        AbToastUtil.showToast(LoginActivity.this,fault.faultstring);
                    }

                    // 开始执行前
                    @Override
                    public void onStart() {
                        //显示进度框
                        AbDialogUtil.showProgressDialog(LoginActivity.this,0,"正在登录...");
                    }


                    // 完成后调用，失败，成功
                    @Override
                    public void onFinish() {
                        //移除进度框
                       // AbDialogUtil.showProgressDialog(LoginActivity.this,0,"正在查询...");
                        AbDialogUtil.removeDialog(LoginActivity.this);
                    };

                });
            }
        });
    }

}
