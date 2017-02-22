package com.example.administrator.ad20170221downorderonline.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class SenderInfo implements Serializable{
    private String mSenderName;
    private String mSenderPro;
    private String mSenderCity;
    private String mSenderAdd;
    private String mSenderPost;
    private String mSenderTel;
    private String mSenderDis;
    public SenderInfo(){}
    public SenderInfo(String mSenderName,
           String mSenderPro, String mSenderCity,String mSenderDis, String mSenderAdd, String mSenderPost,String mSenderTel){
        this.mSenderName=mSenderName;
        this.mSenderPro=mSenderPro;
        this.mSenderCity=mSenderCity;
        this.mSenderAdd = mSenderAdd;
        this.mSenderTel = mSenderTel;
        this.mSenderDis = mSenderDis;
        this.mSenderPost =mSenderPost;

    }
    public String getSenderName() {
        return mSenderName;
    }

    public void setSenderName(String senderName) {
        mSenderName = senderName;
    }

    public String getSenderPro() {
        return mSenderPro;
    }

    public void setSenderPro(String senderPro) {
        mSenderPro = senderPro;
    }

    public String getSenderCity() {
        return mSenderCity;
    }

    public void setSenderCity(String senderCity) {
        mSenderCity = senderCity;
    }

    public String getSenderAdd() {
        return mSenderAdd;
    }

    public void setSenderAdd(String senderAdd) {
        mSenderAdd = senderAdd;
    }

    public String getSenderPost() {
        return mSenderPost;
    }

    public void setSenderPost(String senderPost) {
        mSenderPost = senderPost;
    }

    public String getSenderTel() {
        return mSenderTel;
    }

    public void setSenderTel(String senderTel) {
        mSenderTel = senderTel;
    }



    public String getSenderDis() {
        return mSenderDis;
    }

    public void setSenderDis(String senderDis) {
        mSenderDis = senderDis;
    }



}
