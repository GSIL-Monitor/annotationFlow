/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package model;

import constant.FlowStatus;

/**
 *
 * @author jishu.ly
 * @version $Id: TransformModel.java, v 0.1 2018年11月23日 上午12:52 jishu.ly Exp $
 */
public class TransformModel {

    private FlowStatus currentStatus;

    private FlowStatus destStatus;

    private String action;


    public TransformModel(FlowStatus currentStatus, FlowStatus destStatus) {
        this.currentStatus = currentStatus;
        this.destStatus = destStatus;
    }

    public TransformModel(FlowStatus currentStatus, FlowStatus destStatus, String action) {
        this.currentStatus = currentStatus;
        this.destStatus = destStatus;
        this.action = action;
    }

    public TransformModel() {
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof TransformModel)) { return false; }

        TransformModel that = (TransformModel) o;

        if (currentStatus != that.currentStatus) { return false; }
        if (destStatus != that.destStatus) { return false; }
        return action != null ? action.equals(that.action) : that.action == null;

    }

    @Override
    public int hashCode() {
        int result = currentStatus != null ? currentStatus.hashCode() : 0;
        result = 31 * result + (destStatus != null ? destStatus.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        return result;
    }
}