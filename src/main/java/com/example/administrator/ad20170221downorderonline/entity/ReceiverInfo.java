package com.example.administrator.ad20170221downorderonline.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class ReceiverInfo implements Serializable{
    public ReceiverInfo(){
        this(null,null,null,null,null,null,null);
    }
    public ReceiverInfo(String mReceiverName,
                        String mReceiverPro, String mReceiverCity,  String mReceiverDis,String mReceiverAdd, String mReceiverPost, String mReceiverTel){
        this.mReceiverName=mReceiverName;
        this.mReceiverPro=mReceiverPro;
        this.mReceiverCity=mReceiverCity;
        this.mReceiverAdd = mReceiverAdd;
        this.mReceiverTel = mReceiverTel;
        this.mReceiverPost =mReceiverPost;
        this.mReceiverDis = mReceiverDis;
    }


    private String mReceiverName;
    private String mReceiverPro;
    private String mReceiverCity;
    private String mReceiverAdd;
    private String mReceiverDis;
    private String mReceiverPost;
    private String mReceiverTel;

    public String getReceiverDis() {
        return mReceiverDis;
    }

    public void setReceiverDis(String receiverDis) {
        mReceiverDis = receiverDis;
    }



    public String getReceiverTel() {
        return mReceiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        mReceiverTel = receiverTel;
    }

    public String getReceiverName() {
        return mReceiverName;
    }

    public void setReceiverName(String receiverName) {
        mReceiverName = receiverName;
    }

    public String getReceiverPro() {
        return mReceiverPro;
    }

    public void setReceiverPro(String receiverPro) {
        mReceiverPro = receiverPro;
    }

    public String getReceiverCity() {
        return mReceiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        mReceiverCity = receiverCity;
    }

    public String getReceiverAdd() {
        return mReceiverAdd;
    }

    public void setReceiverAdd(String receiverAdd) {
        mReceiverAdd = receiverAdd;
    }

    public String getReceiverPost() {
        return mReceiverPost;
    }

    public void setReceiverPost(String receiverPost) {
        mReceiverPost = receiverPost;
    }




}
