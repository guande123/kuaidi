package com.example.administrator.ad20170221downorderonline.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class KDHInfo {
    /**
     * resultcode : 200
     * reason : 查询物流信息成功
     * result : {"company":"EMS","com":"ems","no":"1186465887499","status":"1","list":[{"datetime":"2016-06-15 21:44:04","remark":"离开郴州市 发往长沙市【郴州市】","zone":""},{"datetime":"2016-06-15 21:46:45","remark":"郴州市邮政速递物流公司国际快件监管中心已收件（揽投员姓名：侯云,联系电话:）【郴州市】","zone":""},{"datetime":"2016-06-16 12:04:00","remark":"离开长沙市 发往贵阳市（经转）【长沙市】","zone":""},{"datetime":"2016-06-17 07:53:00","remark":"到达贵阳市处理中心（经转）【贵阳市】","zone":""},{"datetime":"2016-06-18 07:40:00","remark":"离开贵阳市 发往毕节地区（经转）【贵阳市】","zone":""},{"datetime":"2016-06-18 09:59:00","remark":"离开贵阳市 发往下一城市（经转）【贵阳市】","zone":""},{"datetime":"2016-06-18 12:01:00","remark":"到达  纳雍县 处理中心【毕节地区】","zone":""},{"datetime":"2016-06-18 17:34:00","remark":"离开纳雍县 发往纳雍县阳长邮政支局【毕节地区】","zone":""},{"datetime":"2016-06-20 17:55:00","remark":"投递并签收，签收人：单位收发章 *【毕节地区】","zone":""}]}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

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
         * company : EMS
         * com : ems
         * no : 1186465887499
         * status : 1
         * list : [{"datetime":"2016-06-15 21:44:04","remark":"离开郴州市 发往长沙市【郴州市】","zone":""},{"datetime":"2016-06-15 21:46:45","remark":"郴州市邮政速递物流公司国际快件监管中心已收件（揽投员姓名：侯云,联系电话:）【郴州市】","zone":""},{"datetime":"2016-06-16 12:04:00","remark":"离开长沙市 发往贵阳市（经转）【长沙市】","zone":""},{"datetime":"2016-06-17 07:53:00","remark":"到达贵阳市处理中心（经转）【贵阳市】","zone":""},{"datetime":"2016-06-18 07:40:00","remark":"离开贵阳市 发往毕节地区（经转）【贵阳市】","zone":""},{"datetime":"2016-06-18 09:59:00","remark":"离开贵阳市 发往下一城市（经转）【贵阳市】","zone":""},{"datetime":"2016-06-18 12:01:00","remark":"到达  纳雍县 处理中心【毕节地区】","zone":""},{"datetime":"2016-06-18 17:34:00","remark":"离开纳雍县 发往纳雍县阳长邮政支局【毕节地区】","zone":""},{"datetime":"2016-06-20 17:55:00","remark":"投递并签收，签收人：单位收发章 *【毕节地区】","zone":""}]
         */

        private String company;
        private String com;
        private String no;
        private String status;
        private List<ListBean> list;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * datetime : 2016-06-15 21:44:04
             * remark : 离开郴州市 发往长沙市【郴州市】
             * zone :
             */

            private String datetime;
            private String remark;
            private String zone;

            public String getDatetime() {
                return datetime;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getZone() {
                return zone;
            }

            public void setZone(String zone) {
                this.zone = zone;
            }
        }
    }
}
