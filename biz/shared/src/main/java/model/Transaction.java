/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package model;

import constant.FlowStatus;

/**
 *
 * @author jishu.ly
 */
public class Transaction <T> {

    private String flowName;

    private FlowStatus currentStatus;

    private FlowStatus destStatus;

    private String action;

    private  T bizObject;

    public T getBizObject() {
        return bizObject;
    }

    public void setBizObject(T bizObject) {
        this.bizObject = bizObject;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "bizObject=" + bizObject +
                '}';
    }

    public FlowStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(FlowStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public FlowStatus getDestStatus() {
        return destStatus;
    }

    public void setDestStatus(FlowStatus destStatus) {
        this.destStatus = destStatus;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}