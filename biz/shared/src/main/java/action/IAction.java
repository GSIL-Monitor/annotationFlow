package action; /**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */

import model.Transaction;

/**
 *
 *
 * @author jishu.ly
 * @version $Id: action.IAction.java, v 0.1 2018��11��23�� ����12:10 jishu.ly Exp $
 */
public interface IAction<T> {

    void  process(Transaction<T> transaction);

}