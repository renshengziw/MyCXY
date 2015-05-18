/*
package com.chen.cxy.view.login;

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
import com.ab.util.AbToastUtil;
import com.chen.cxy.R;

*/
/**
 * 登录控制
 *//*

public class LoginActivity1 extends AbActivity implements View.OnClickListener{

    private EditText userName;
    private EditText password;
    private Button loginBtn;
    private AbSoapUtil mAbSoapUtil = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setAbContentView(R.layout.login);

        init();
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

                // 一个url地址
                String urlString = "http://192.168.168.121:8088/services/cxy?wsdl";
                String nameSpace = "http://webservice.cxy/";
                String methodName = "login";
                AbSoapParams params = new AbSoapParams();
                params.put("userName", "18801296610");
                params.put("password", "123456");

                mAbSoapUtil.call(urlString,nameSpace,methodName,params,new AbSoapListener(){
                    @Override
                    public void onSuccess(int statusCode, String content) {

                        AbDialogUtil.showAlertDialog(LoginActivity1.this, "返回结果", content, new AbAlertDialogFragment.AbDialogOnClickListener() {

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

                        AbToastUtil.showToast(LoginActivity1.this, error.getMessage());
                    }

                    // 开始执行前
                    @Override
                    public void onStart() {
                        //显示进度框
                        AbDialogUtil.showProgressDialog(LoginActivity1.this,0,"正在查询...");
                    }


                    // 完成后调用，失败，成功
                    @Override
                    public void onFinish() {
                        //移除进度框
                        AbDialogUtil.removeDialog(LoginActivity1.this);
                    };

                });
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.login_btn:
                Toast.makeText(this, "登录测试", Toast.LENGTH_SHORT).show();

                // 一个url地址
                String urlString = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx?op=getMobileCodeInfo";
                String nameSpace = "http://WebXml.com.cn/";
                String methodName = "getMobileCodeInfo";
                AbSoapParams params = new AbSoapParams();
                params.put("mobileCode", "15150509589");
                params.put("userID", "");

                mAbSoapUtil.call(urlString,nameSpace,methodName,params,new AbSoapListener(){
                    @Override
                    public void onSuccess(int statusCode, String content) {

                        AbDialogUtil.showAlertDialog(LoginActivity1.this, "返回结果", content, new AbAlertDialogFragment.AbDialogOnClickListener() {

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

                        AbToastUtil.showToast(LoginActivity1.this, error.getMessage());
                    }

                    // 开始执行前
                    @Override
                    public void onStart() {
                        //显示进度框
                        AbDialogUtil.showProgressDialog(LoginActivity1.this,0,"正在查询...");
                    }


                    // 完成后调用，失败，成功
                    @Override
                    public void onFinish() {
                        //移除进度框
                        AbDialogUtil.removeDialog(LoginActivity1.this);
                    };

                });
               // Intent intent = new Intent(this, MainActivity.class);
               // this.startActivity(intent);
                break;
        }
    }
}
*/
