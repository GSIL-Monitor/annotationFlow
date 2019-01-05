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
 * @version $Id: Router.java, v 0.1 2018��11��22�� ����11:59 jishu.ly Exp $
 */
public class Router<T> implements IRouter<T> {



    @Override
    public void route(Transaction<T> transactions) {
        FlowStatus destStatus = transactions.getDestStatus();

        if (destStatus.equals(FlowStatus.ACCEPT)) {
            transactions.setCurrentStatus(destStatus);
            transactions.setDestStatus(FlowStatus.SUBMIT);
        } else if (destStatus.equals(FlowStatus.SUBMIT)) {
            transactions.setCurrentStatus(destStatus);
            transactions.setDestStatus(FlowStatus.SUCCESS);
        }
    }
}