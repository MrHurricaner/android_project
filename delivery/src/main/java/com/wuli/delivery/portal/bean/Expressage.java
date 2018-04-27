package com.wuli.delivery.portal.bean;

/**
 * @author ziv
 * @date 2018/4/26
 */

public class Expressage {

    /**
     * 快递id
     */
    private String expressageID;
    /**
     * 快递代领号
     */
    private String expressageLeadNum;
    /**
     * 快递送达时间
     */
    private String deliveryTime;
    /**
     * 快递送达地点
     */
    private String deliverySite;
    /**
     * 快递代领报酬
     */
    private String expressageLeadReward;
    /**
     * 收件人姓名
     */
    private String deliveryUserName;
    /**
     * 收件人手机号码
     */
    private String deliveryPhoneNumber;
    /**
     * 快递代领备注
     */
    private String expressageLeadRemark;
    /**
     * 快递状态
     */
    private String expressageStatus;

    public Expressage() {
        super();
    }

    Expressage(String expressageID, String expressageLeadNum, String deliveryTime, String deliverySite, String expressageLeadReward, String deliveryUserName, String deliveryPhoneNumber, String expressageLeadRemark, String expressageStatus) {
        this.expressageID = expressageID;
        this.expressageLeadNum = expressageLeadNum;
        this.deliveryTime = deliveryTime;
        this.deliverySite = deliverySite;
        this.expressageLeadReward = expressageLeadReward;
        this.deliveryUserName = deliveryUserName;
        this.deliveryPhoneNumber = deliveryPhoneNumber;
        this.expressageLeadRemark = expressageLeadRemark;
        this.expressageStatus = expressageStatus;
    }

    public final static class Builder {

        /**
         * 快递id
         */
        private String expressageID;
        /**
         * 快递代领号
         */
        private String expressageLeadNum;
        /**
         * 快递送达时间
         */
        private String deliveryTime;
        /**
         * 快递送达地点
         */
        private String deliverySite;
        /**
         * 快递代领报酬
         */
        private String expressageLeadReward;
        /**
         * 收件人姓名
         */
        private String deliveryUserName;
        /**
         * 收件人手机号码
         */
        private String deliveryPhoneNumber;
        /**
         * 快递代领备注
         */
        private String expressageLeadRemark;
        /**
         * 快递状态
         */
        private String expressageStatus;

        public Builder expressageID(String expressageID) {
            this.expressageID = expressageID;
            return this;
        }

        public Builder expressageLeadNum(String expressageLeadNum) {
            this.expressageLeadNum = expressageLeadNum;
            return this;
        }

        public Builder deliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
            return this;
        }

        public Builder deliverySite(String deliverySite) {
            this.deliverySite = deliverySite;
            return this;
        }

        public Builder expressageLeadReward(String expressageLeadReward) {
            this.expressageLeadReward = expressageLeadReward;
            return this;
        }

        public Builder deliveryUserName(String deliveryUserName) {
            this.deliveryUserName = deliveryUserName;
            return this;
        }

        public Builder deliveryPhoneNumber(String deliveryPhoneNumber) {
            this.deliveryPhoneNumber = deliveryPhoneNumber;
            return this;
        }

        public Builder expressageLeadRemark(String expressageLeadRemark) {
            this.expressageLeadRemark = expressageLeadRemark;
            return this;
        }

        public Builder expressageStatus(String expressageStatus) {
            this.expressageStatus = expressageStatus;
            return this;
        }

        public Builder() {

        }

        public Builder(Expressage expressage) {
            this.expressageID = expressage.expressageID;
            this.expressageLeadNum = expressage.expressageLeadNum;
            this.deliveryTime = expressage.deliveryTime;
            this.deliverySite = expressage.deliverySite;
            this.expressageLeadReward = expressage.expressageLeadReward;
            this.deliveryUserName = expressage.deliveryUserName;
            this.deliveryPhoneNumber = expressage.deliveryPhoneNumber;
            this.expressageLeadRemark = expressage.expressageLeadRemark;
            this.expressageStatus = expressage.expressageStatus;
        }

        public Expressage build() {
            return new Expressage(this.expressageID, this.expressageLeadNum, this.deliveryTime,
                    this.deliverySite, this.expressageLeadReward, this.deliveryUserName,
                    this.deliveryPhoneNumber, this.expressageLeadRemark, this.expressageStatus);
        }

    }


}
