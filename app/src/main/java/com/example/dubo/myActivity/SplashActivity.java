package com.example.dubo.myActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dubo.my_begin_test.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import utils.dbToolUtils;

public class SplashActivity extends Activity {

    public final int Error = 1;
    public final int Success = 2;
    private String pathUrl;
    private TextView tvSplashVersion;
    private ProgressBar pb;
    private ProgressDialog pd;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Error:
                    Toast.makeText(SplashActivity.this,"fail",Toast.LENGTH_SHORT).show();
                    loadMaiUi();
                    break;
                case Success:
                    Toast.makeText(SplashActivity.this,"fail",Toast.LENGTH_SHORT).show();
                    String desc = (String) msg.obj;
                    showDiaLog(desc);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSplashVersion = (TextView) findViewById(R.id.tv_splash_version);//// TODO: 2016/12/4 buttonKnife怎么就不能用
        tvSplashVersion.setText("版本号:");
        new Thread(new CheckVerson()).start();
       /* SharedPreferences sharedPreferences = getSharedPreferences("a",MODE_APPEND);
        boolean aBoolean = savedInstanceState.getBoolean("a", true);
        if(aBoolean){
            new Thread(new CheckVerson()).start();
        }else{
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    SystemClock.sleep(200);
                    loadMaiUi();
                }
            }.start();
        }*/
    }



    private void showDiaLog(String desc) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setMessage(desc);
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, final int i) {
                pd = new ProgressDialog(SplashActivity.this);
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.show();
                HttpUtils httpUtils = new HttpUtils();
                File storageDirectory = Environment.getExternalStorageDirectory();
                File file = new File(storageDirectory, SystemClock.uptimeMillis() +"apk");
                if(Environment.getExternalStorageState().equals(storageDirectory)){
                    httpUtils.download(pathUrl, file.getAbsolutePath(), new RequestCallBack<File>() {
                        @Override
                        public void onSuccess(ResponseInfo<File> responseInfo) {
                            Toast.makeText(SplashActivity.this,"success",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            intent.addCategory("android.intent.category.DEFAULT");
                            intent.setDataAndType(Uri.fromFile(responseInfo.result), "application/vnd.android.package-archive");
                            startActivity(intent);
                            pd.dismiss();
                        }

                        @Override
                        public void onLoading(long total, long current, boolean isUploading) {
                            pd.setProgress((int) total);
                            pd.setMax((int) current);
                            super.onLoading(total, current, isUploading);
                        }

                        @Override
                        public void onFailure(HttpException error, String msg) {
                            Toast.makeText(SplashActivity.this,"fail",Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    });
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                loadMaiUi();
            }
        });
        builder.show();
    }

    class CheckVerson implements Runnable{
        @Override
        public void run() {
            Message msg = Message.obtain();
            String path = getResources().getString(R.string.url);
            long startTime = SystemClock.currentThreadTimeMillis();//// TODO: 2016/12/4 systemclock和system的区别
            try {
                URL url = new URL(path);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("Get");
                httpURLConnection.setConnectTimeout(2000);
                int code = httpURLConnection.getResponseCode();
                if (code == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    String result = dbToolUtils.ReadStream(inputStream);
                    JSONObject jsonObject = new JSONObject(result);
                    String version = jsonObject.getString("version");//// TODO: 2016/12/4 服务器版本号
                    //// TODO: 2016/12/4 虚拟路径
                    pathUrl = jsonObject.getString("pathUrl");
                    String versionName = dbToolUtils.versionName(SplashActivity.this);
                    if(versionName.equals(version)){
                        loadMaiUi();
                    }else {
                        msg.what = Success;
                        msg.obj = "===============>";
                    }
                }else{
                    msg.what = Error;
                    msg.obj = "code 401";
                }
            } catch (MalformedURLException e) {
                //e.printStackTrace();
                msg.what = Error;
                msg.obj = "code 401";
                handler.sendMessage(msg);
            } catch (IOException e) {
                msg.what = Error;
                msg.obj = "code 401";
                e.printStackTrace();
                handler.sendMessage(msg);
            } catch (JSONException e) {
                msg.what = Error;
                msg.obj = "code 401";
                e.printStackTrace();
            }finally {
                long endTime = SystemClock.currentThreadTimeMillis();
                long time = endTime -startTime;
                if(time > 200){

                }else{
                    SystemClock.sleep(200);
                }
                //handler.sendMessage(msg);
            }
        }
    }

    private void loadMaiUi() {
        Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }


}
