package com.example.administrator.ad20170221downorderonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

/**
 * Created by Administrator on 2017/2/22 0022.
 */
public class ShowInfoActivity  extends AppCompatActivity{
    private TextView sNameTv;
    private TextView sProTv;
    private TextView sCityTv;
    private TextView sDisTv;
    private TextView sAddTv;
    private TextView sPostTv;
    private TextView sTelTv;

    private TextView rNameTv;
    private TextView rProTv;
    private TextView rCityTv;
    private TextView rDisTv;
    private TextView rAddTv;
    private TextView rPostTv;
    private TextView rTelTv;
    private SenderInfo sinfo;
    private ReceiverInfo rinfo;
    private Button cancelOrderBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinfo);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        sinfo = (SenderInfo) bundle.getSerializable("senderinfo");
        rinfo = (ReceiverInfo) bundle.getSerializable("receiverinfo");

        findViews();
        showGuestInfo(sinfo,rinfo);
    }

    private void showGuestInfo(SenderInfo sinfo, ReceiverInfo rinfo) {
        sNameTv.setText(sinfo.getSenderName());
        sProTv.setText(sinfo.getSenderPro());
        sCityTv.setText(sinfo.getSenderCity());
        sDisTv.setText(sinfo.getSenderDis());
        sAddTv.setText(sinfo.getSenderAdd());
        sPostTv.setText(sinfo.getSenderPost());
        sTelTv.setText(sinfo.getSenderTel());

        rNameTv.setText(rinfo.getReceiverName());
        rProTv.setText(rinfo.getReceiverPro());
        rCityTv.setText(rinfo.getReceiverCity());
        rDisTv.setText(rinfo.getReceiverDis());
        rAddTv.setText(rinfo.getReceiverAdd());
        rPostTv.setText(rinfo.getReceiverPost());
        rTelTv.setText(rinfo.getReceiverTel());
    }

    private void findViews() {
       rNameTv   = (TextView) findViewById(R.id.treceiver_name);
        rProTv   = (TextView) findViewById(R.id.treceiver_province_name);
        rCityTv   = (TextView) findViewById(R.id.treceiver_city_name);
        rDisTv   = (TextView) findViewById(R.id.treceiver_district_name);
        rAddTv   = (TextView) findViewById(R.id.treceiver_address);
        rPostTv   = (TextView) findViewById(R.id.treceiver_post_code);
        rTelTv   = (TextView) findViewById(R.id.treceiver_telphone);

        sNameTv   = (TextView) findViewById(R.id.tsender_name);
        sProTv   = (TextView) findViewById(R.id.tsender_province_name);
        sCityTv   = (TextView) findViewById(R.id.tsender_city_name);
        sDisTv   = (TextView) findViewById(R.id.tsender_district_name);
        sAddTv   = (TextView) findViewById(R.id.tsender_address);
        sPostTv   = (TextView) findViewById(R.id.tsender_post_code);
        sTelTv   = (TextView) findViewById(R.id.tsender_telphone);
        cancelOrderBtn = (Button) findViewById(R.id.cancelOrderBtn);
        cancelOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    URL url = new URL(OrderOnlineAPI.CANCEL_ORDER_URL+"?key="+OrderOnlineAPI.APPKEY+
                                            "&"+ OrderString.ORDER_NO+"="+"45623135487000"+
                                            "&"+ OrderString.CARRIER_CODE+"=zjs");
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
                                    OrderNetEntity orderEntity =  g.fromJson(sb.toString(), OrderNetEntity.class);
                                    if (orderEntity.getReason().equals("取消成功")){
                                        finish();
                                    }
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                ).start();

            }
        });

    }
}
