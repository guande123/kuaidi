package com.example.administrator.ad20170221downorderonline.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.ad20170221downorderonline.R;
import com.example.administrator.ad20170221downorderonline.entity.KDHInfo;

/**
 * Created by Administrator on 2017/2/22 0022.
 */
public class KDHInfoActivity extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_item);
        mTextView = (TextView) findViewById(R.id.tcontent);
       KDHInfo kdhInfo = (KDHInfo) getIntent().getExtras().getSerializable("kdhinfo");
        mTextView.setText(kdhInfo.getResult().getCompany()+"\n"+
                kdhInfo.getResult().getNo()+"\n"+
                kdhInfo.getResult().getList().get(0).getDatetime()+"\n"+
                kdhInfo.getResult().getList().get(0).getRemark()+"\n"+
                kdhInfo.getResult().getList().get(0).getZone()+"\n");
    }
}
