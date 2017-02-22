package com.example.administrator.ad20170221downorderonline.activity;

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

import com.example.administrator.ad20170221downorderonline.R;
import com.example.administrator.ad20170221downorderonline.entity.ChangyongComList;
import com.example.administrator.ad20170221downorderonline.entity.CompanyList;
import com.example.administrator.ad20170221downorderonline.entity.KDHInfo;
import com.example.administrator.ad20170221downorderonline.entity.OrderNetEntity;
import com.example.administrator.ad20170221downorderonline.entity.ReceiverInfo;
import com.example.administrator.ad20170221downorderonline.entity.SenderInfo;
import com.example.administrator.ad20170221downorderonline.orderOnlineAPI.ChangyongkuaidiAPi;
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
import java.net.URLEncoder;
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
    private Button mSearchOrder;
    private EditText mKuaidihao;
     private ArrayAdapter<String> mArrayAdapter;
    private ArrayList<String> mStrings = new ArrayList<String>();
    private HttpURLConnection conn;
    private CompanyList mCompanyList;
    private ChangyongComList  mchangyongcom;
    private Handler mHandler;
    public static final int LOAD_LIST =1002 ;
    public static final int FAIL_ORDER = 1003;
    public static final int  FAIL_SEARCH =1004 ;
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

        mSearchOrder = (Button) findViewById(R.id.search_click);
        mKuaidihao = (EditText) findViewById(R.id.kuaidihao);
        mSearchOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String kuaidihao =   mKuaidihao.getText().toString().trim();
              int position =  mSpinner.getSelectedItemPosition();
                Log.i("AAAA","position"+position);
                final String urlstr = ChangyongkuaidiAPi.KUAIDI_SEARCH_URL+"?key="+ChangyongkuaidiAPi.APPKEY+
                        "&no="+kuaidihao+"&com="+mchangyongcom.getResult().get(position).getNo();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        URL url = null;
                        try {
                            url = new URL(urlstr);
                        Log.i("AAAA", urlstr);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
                            Log.i("AAAA",g.toString());
                        KDHInfo kdhInfo =  g.fromJson(sb.toString(), KDHInfo.class);
                            if(kdhInfo.getReason().equals("查询物流信息成功")){
                                   //返回物流信息
                                Intent intent = new Intent(MainActivity.this,KDHInfoActivity.class);
                                intent.putExtra("kdhinfo",kdhInfo);
                                startActivity(intent);
                            }else{
                                Message msg= new Message();
                                msg.what = FAIL_SEARCH;
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
                            //String urlstr2= OrderOnlineAPI.ORDER_ONLINE_URL+"?dtype=&send_method=addOrderInfoMes&order_no=546123487651&isWaybill=1&carrier_code=zjs&sender_name=%E9%94%82%E8%81%9A%E5%90%88&sender_telphone=13760790960&sender_phone=&sender_province_name=%E5%B9%BF%E4%B8%9C%E7%9C%81&sender_city_name=%E5%B9%BF%E5%B7%9E%E5%B8%82&sender_district_name=%E5%85%83%E5%B2%97%E5%8C%BA&sender_address=%E5%B9%BF%E4%B8%9C%E4%BA%A4%E9%80%9A%E8%81%8C%E4%B8%9A%E6%8A%80%E6%9C%AF%E5%AD%A6%E9%99%A2&sender_post_code=520134&receiver_name=%E9%83%91%E6%88%90%E5%8A%9F&receiver_telphone=13828243673&receiver_phone=&receiver_province_name=%E5%B9%BF%E4%B8%9C%E7%9C%81&receiver_city_name=%E5%B9%BF%E5%B7%9E%E5%B8%82&receiver_district_name=%E5%85%83%E5%B2%97%E5%8C%BA&receiver_address=%E5%B9%BF%E4%B8%9C%E4%BA%A4%E9%80%9A%E8%81%8C%E4%B8%9A%E6%8A%80%E6%9C%AF%E5%AD%A6%E9%99%A2&receiver_org_name=&receiver_post_code=520134&remark=&item_weight=&item_name=&item_spec=&send_start_time=&send_end_time=&key=dcd5d4b4fa1585ceb7fd279c4b45f641";
                            String urlstr = OrderOnlineAPI.ORDER_ONLINE_URL+"?key="+OrderOnlineAPI.APPKEY+
                                    "&"+ OrderString.CARRIER_CODE+"=zjs"+
                                    "&"+ OrderString.ISWAYBILL+"=1"+
                                    "&"+ OrderString.ORDER_NO+"=45623135487000"+
                                    "&"+ OrderString.SEND_METHOD+"=addOrderInfoMes"+
                                    "&"+ OrderString.SENDER_NAME+"="+ URLEncoder.encode(sName,"UTF-8")+
                                    "&"+ OrderString.SENDER_PROVINCE+"="+URLEncoder.encode(sPro,"UTF-8")+
                                    "&"+ OrderString.SENDER_CITY+"="+URLEncoder.encode(sCity,"UTF-8")+
                                    "&"+ OrderString.SENDER_DISTRICT+"="+URLEncoder.encode(sDis,"UTF-8")+
                                    "&"+ OrderString.SENDER_ADDRESS+"="+URLEncoder.encode(sAdd,"UTF-8")+
                                    "&"+ OrderString.SENDER_POST_CODE+"="+URLEncoder.encode(sPost,"UTF-8")+
                                    "&"+ OrderString.SENDER_TELPHONE+"="+URLEncoder.encode(sTel,"UTF-8")+
                                    "&"+ OrderString.RECEIVER_NAME+"="+URLEncoder.encode(rName,"UTF-8")+
                                    "&"+ OrderString.RECEIVER_PROVINCE+"="+URLEncoder.encode(rPro,"UTF-8")+
                                    "&"+ OrderString.RECEIVER_CITY+"="+URLEncoder.encode(rCity,"UTF-8")+
                                    "&"+ OrderString.RECEIVER_DISTRICT+"="+URLEncoder.encode(rDis,"UTF-8")+
                                    "&"+ OrderString.RECEIVER_ADDRESS+"="+URLEncoder.encode(rAdd,"UTF-8")+
                                    "&"+ OrderString.RECEIVER_POST_CODE+"="+URLEncoder.encode(rPost,"UTF-8")+
                                    "&"+ OrderString.RECEIVER_TELPHONE+"="+URLEncoder.encode(rTel,"UTF-8");
                            URL url = new URL(urlstr);
                            Log.i("AAAA", urlstr);
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
                            Log.i("AAAA", sb.toString());
                           if (orderEntity.getReason().equals("下单成功")||orderEntity.getReason().equals("订单号已存在")){
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
                if(msg.what==FAIL_SEARCH){
                    Toast.makeText(MainActivity.this,"查询失败!",Toast.LENGTH_LONG).show();
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
                URL    url = new URL(ChangyongkuaidiAPi.COMPANY_TABLE_URL + "?key=" + ChangyongkuaidiAPi.APPKEY);
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
                    mchangyongcom = g.fromJson( sb.toString(),ChangyongComList.class);
                    Log.i("AAAA", sb.toString());
                    Log.i("AAAA", mchangyongcom.getReason());

                    if(mchangyongcom.getReason().equals("查询支持的快递公司成功")){
                        Log.i("AAAA","公司列表加载完成");

                        for(int i = 0;i<mchangyongcom.getResult().size();i++){
                            mStrings.add(mchangyongcom.getResult().get(i).getCom());
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
