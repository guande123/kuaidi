package com.example.administrator.ad20170221downorderonline;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.administrator.ad20170221downorderonline.entity.CompanyList;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   /* @BindView(R.id.spinner)
    Spinner mSpinner;*/
    private static  final String   APPKEY ="dcd5d4b4fa1585ceb7fd279c4b45f641";
    private static  final String   COMPANY_CONTENT_URL="http://v.juhe.cn/expressonline/getCarriers.php";
    private Spinner mSpinner;
     private ArrayAdapter<String> mArrayAdapter;
    private ArrayList<String> mStrings = new ArrayList<String>();
    private URL url;
    private HttpURLConnection conn;
    private CompanyList mCompanyList;
    private Handler mHandler;
    public static final int LOAD_LIST =1002 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // ButterKnife.bind(this);

        runHttpInputStream();
        initHandler();

    }

    private void initHandler() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what==LOAD_LIST){
                    initSpinner();
                    Log.i("AAAA","handler已回调!");
                }
                return true;
            }
        });
    }

    private void initSpinner() {
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mArrayAdapter=new ArrayAdapter<String>(this,R.layout.company_item, mStrings);
        mArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(mArrayAdapter);
        Log.i("AAAA",mStrings.toString());

    }


    public void jsonFormat(String json){
        Gson g= new Gson();
        mCompanyList = g.fromJson(json,CompanyList.class);
    }

    public void runHttpInputStream() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    url = new URL(COMPANY_CONTENT_URL + "?key=" + APPKEY);
                    conn = (HttpURLConnection) url.openConnection();
                    InputStream is = conn.getInputStream();
                    StringBuffer sb = new StringBuffer();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    br.close();
                    is.close();

                    jsonFormat( sb.toString());


                    Log.i("AAAA", sb.toString());
                    Log.i("AAAA", mCompanyList.getReason());


                    if(mCompanyList.getReason().equals("查询成功")){
                        Log.i("AAAA","公司列表加载完成");

                        for(int i = 0;i<mCompanyList.getResult().size();i++){
                            mStrings.add(mCompanyList.getResult().get(i).getCarrier_name());
                        }
                    }
                    Message msg= new Message();
                    msg.what = LOAD_LIST;
                    mHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }
}
