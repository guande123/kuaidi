package com.example.administrator.ad20170221downorderonline.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class ChangyongComList {

    /**
     * resultcode : 200
     * reason : 查询支持的快递公司成功
     * result : [{"com":"顺丰","no":"sf"},{"com":"申通","no":"sto"},{"com":"圆通","no":"yt"},{"com":"韵达","no":"yd"},{"com":"天天","no":"tt"},{"com":"EMS","no":"ems"},{"com":"中通","no":"zto"},{"com":"汇通","no":"ht"},{"com":"全峰","no":"qf"},{"com":"德邦","no":"db"},{"com":"自动匹配","no":"auto"},{"com":"国通","no":"gt"},{"com":"如风达","no":"rfd"},{"com":"京东快递","no":"jd"},{"com":"宅急送","no":"zjs"},{"com":"EMS国际","no":"emsg"},{"com":"Fedex国际","no":"fedex"},{"com":"邮政国内（挂号信）","no":"yzgn"},{"com":"UPS国际快递","no":"ups"},{"com":"中铁快运","no":"ztky"}]
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * com : 顺丰
         * no : sf
         */

        private String com;
        private String no;

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }
    }
}
