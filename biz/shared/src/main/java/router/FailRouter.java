/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package router;

import constant.FlowStatus;
import model.Transaction;

/**
 *
 * @author jishu.ly
 */
public class FailRouter<T> implements IRouter<T>{


    @Override
    public void route(Transaction<T> transactions) {
        FlowStatus srcStatus = transactions.getCurrentStatus();
        if (FlowStatus.ACCEPT.equals(srcStatus)) {
            transactions.setDestStatus(FlowStatus.FAIL);
        } else if (FlowStatus.SUBMIT.equals(srcStatus)) {
            transactions.setDestStatus(FlowStatus.FAIL);
        }
    }
}