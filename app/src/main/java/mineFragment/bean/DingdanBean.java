package mineFragment.bean;

import java.util.List;

/**
 * 周旋
 * 2017/11/22  18:26
 */

public class DingdanBean {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2017-11-21T14:37:32","orderid":2638,"price":99.99,"status":0,"title":null,"uid":983},{"createtime":"2017-11-21T14:38:03","orderid":2639,"price":100,"status":0,"title":null,"uid":983},{"createtime":"2017-11-22T09:39:50","orderid":3001,"price":100,"status":0,"title":null,"uid":983},{"createtime":"2017-11-22T09:40:01","orderid":3002,"price":101,"status":0,"title":null,"uid":983}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2017-11-21T14:37:32
         * orderid : 2638
         * price : 99.99
         * status : 0
         * title : null
         * uid : 983
         */

        private String createtime;
        private int orderid;
        private double price;
        private int status;
        private Object title;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
