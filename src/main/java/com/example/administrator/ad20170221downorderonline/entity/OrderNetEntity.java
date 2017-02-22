package com.example.administrator.ad20170221downorderonline.entity;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class OrderNetEntity {
    /**
     * reason : 下单成功
     * result : {"order_no":"20160129121200956523","carrier_code":"zjs"}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * order_no : 20160129121200956523
         * carrier_code : zjs
         */

        private String order_no;
        private String carrier_code;

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getCarrier_code() {
            return carrier_code;
        }

        public void setCarrier_code(String carrier_code) {
            this.carrier_code = carrier_code;
        }
    }
}
