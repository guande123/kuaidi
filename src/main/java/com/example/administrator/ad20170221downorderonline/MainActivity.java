package com.example.administrator.ad20170221downorderonline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.ad20170221downorderonline.entity.CompanyList;
import com.example.administrator.ad20170221downorderonline.entity.OrderNetEntity;
import com.example.administrator.ad20170221downorderonline.entity.ReceiverInfo;
import com.example.administrator.ad20170221downorderonline.entity.SenderInfo;
import com.example.administrator.ad20170221downorderonline.orderOnlineAPI.OrderOnlineAPI;
import com.example.administrator.ad20170221downorderonline.orderOnlineAPI.OrderString;
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
     //widget
    private Spinner mSpinner;
    private EditText mSNameEdt;
    private EditText mSProEdt;
    private EditText mSCityEdt;
    private EditText mSDisEdt;
    private EditText mSTelEdt;
    private EditText mSAddEdt;
    private EditText mSPostEdt;

    private EditText mRNameEdt;
    private EditText mRProEdt;
    private EditText mRCityEdt;
    private EditText mRDisEdt;
    private EditText mRTelEdt;
    private EditText mRAddEdt;
    private EditText mRPostEdt;
    private Button mSubmitBtn;
     private ArrayAdapter<String> mArrayAdapter;
    private ArrayList<String> mStrings = new ArrayList<String>();
    private HttpURLConnection conn;
    private CompanyList mCompanyList;
    private Handler mHandler;
    public static final int LOAD_LIST =1002 ;
    public static final int FAIL_ORDER = 1003;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // ButterKnife.bind(this);
        findViews();
        runHttpInputStream();
        initHandler();

    }

    private void findViews() {
        mSNameEdt= (EditText) findViewById(R.id.sender_name);
        mSTelEdt= (EditText) findViewById(R.id.sender_telphone);
        mSProEdt= (EditText) findViewById(R.id.sender_province_name);
        mSCityEdt= (EditText) findViewById(R.id.sender_city_name);
        mSDisEdt= (EditText) findViewById(R.id.sender_district_name);
        mSAddEdt= (EditText) findViewById(R.id.sender_address);
        mSPostEdt= (EditText) findViewById(R.id.sender_post_code);

        mRNameEdt= (EditText) findViewById(R.id.receiver_name);
        mRTelEdt= (EditText) findViewById(R.id.receiver_telphone);
        mRProEdt= (EditText) findViewById(R.id.receiver_province_name);
        mRCityEdt= (EditText) findViewById(R.id.receiver_city_name);
        mRDisEdt= (EditText) findViewById(R.id.receiver_district_name);
        mRAddEdt= (EditText) findViewById(R.id.receiver_address);
        mRPostEdt= (EditText) findViewById(R.id.receiver_post_code);
        mSubmitBtn = (Button) findViewById(R.id.submit);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             final String sName =    mSNameEdt.getText().toString().trim();
                final String sTel =    mSTelEdt.getText().toString().trim();
                final String sPro =    mSProEdt.getText().toString().trim();
                final String sCity =    mSCityEdt.getText().toString().trim();
                final String sDis =    mSDisEdt.getText().toString().trim();
                final String sAdd =    mSAddEdt.getText().toString().trim();
                final String sPost =    mSPostEdt.getText().toString().trim();

                final   String rName =    mRNameEdt.getText().toString().trim();
                final  String rTel =    mRTelEdt.getText().toString().trim();
                final  String rPro =    mRProEdt.getText().toString().trim();
                final  String rCity =    mRCityEdt.getText().toString().trim();
                final  String rDis =    mRDisEdt.getText().toString().trim();
                final String rAdd =    mRAddEdt.getText().toString().trim();
                final  String rPost =    mRPostEdt.getText().toString().trim();

                final SenderInfo sInfo = new SenderInfo(sName,sPro,sCity,sDis,sAdd,sPost,sTel);
                final ReceiverInfo rInfo = new ReceiverInfo(rName,rPro,rCity,rDis,rAdd,rPost,rTel);
                new Thread(new Runnable() {


                    @Override
                    public void run() {
                        try {
                            URL url = new URL(OrderOnlineAPI.ORDER_ONLINE_URL+"?key="+OrderOnlineAPI.APPKEY+
                            "&"+ OrderString.CARRIER_CODE+"=zjs"+
                                    "&"+ OrderString.ISWAYBILL+"=1"+
                                    "&"+ OrderString.ORDER_NO+"=45623135487000"+
                                    "&"+ OrderString.SEND_METHOD+"=addOrderInfoMes"+
                                    "&"+ OrderString.SENDER_NAME+"="+sName+
                                    "&"+ OrderString.SENDER_PROVINCE+"="+sPro+
                                    "&"+ OrderString.SENDER_CITY+"="+sCity+
                                    "&"+ OrderString.SENDER_DISTRICT+"="+sDis+
                                    "&"+ OrderString.SENDER_ADDRESS+"="+sAdd+
                                    "&"+ OrderString.SENDER_POST_CODE+"="+sPost+
                                    "&"+ OrderString.SENDER_TELPHONE+"="+sTel+
                                    "&"+ OrderString.RECEIVER_NAME+"="+rName+
                                    "&"+ OrderString.RECEIVER_PROVINCE+"="+rPro+
                                    "&"+ OrderString.RECEIVER_CITY+"="+rCity+
                                    "&"+ OrderString.RECEIVER_DISTRICT+"="+rDis+
                                    "&"+ OrderString.RECEIVER_ADDRESS+"="+rAdd+
                                    "&"+ OrderString.RECEIVER_POST_CODE+"="+rPost+
                                    "&"+ OrderString.RECEIVER_TELPHONE+"="+rTel);
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
                            Gson g = new Gson();
                            OrderNetEntity orderEntity =  g.fromJson(sb.toString(), OrderNetEntity.class);
                           if (orderEntity.getReason().equals("下单成功")){
                               startActivityToShowInfo(sInfo,rInfo);
                           }else{
                               Message msg= new Message();
                               msg.what = FAIL_ORDER;
                               mHandler.sendMessage(msg);
                           }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    }
    private void startActivityToShowInfo(SenderInfo sInfo,ReceiverInfo rInfo) {
        Intent intent = new Intent(MainActivity.this,ShowInfoActivity.class);
        intent.putExtra("senderinfo",sInfo);
        intent.putExtra("receiverinfo",rInfo);
        startActivity(intent);
    }

    private void initHandler() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what==LOAD_LIST){
                    initSpinner();
                    Log.i("AAAA","handler已回调!");
                }
                if(msg.what==FAIL_ORDER){
                    Toast.makeText(MainActivity.this,"下单失败!",Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }

    private void initSpinner() {
        mArrayAdapter=new ArrayAdapter<String>(this,R.layout.company_item, mStrings);
        mArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(mArrayAdapter);
        Log.i("AAAA",mStrings.toString());

    }


 /*   public <T> T jsonFormat(String json,Class c){
        Gson g= new Gson();
        return  g.fromJson(json,(Type) c.getClass());
    }*/

    public void runHttpInputStream() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                URL    url = new URL(OrderOnlineAPI.COMPANY_CONTENT_URL + "?key=" + OrderOnlineAPI.APPKEY);
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
                    Gson g = new Gson();
                    mCompanyList = g.fromJson( sb.toString(),CompanyList.class);
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
