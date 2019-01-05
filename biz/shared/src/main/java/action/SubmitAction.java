/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package action;

import model.Transaction;

/**
 *
 * @author jishu.ly
 * @version $Id: SubmitAction.java, v 0.1 2018��11��23�� ����12:36 jishu.ly Exp $
 */
public class SubmitAction<T> implements IAction<T> {

    @Override
    public void process(Transaction<T> transaction) {
        System.out.print("submit");
    }
}