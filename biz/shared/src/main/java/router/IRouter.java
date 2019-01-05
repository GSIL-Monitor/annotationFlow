/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package router;

import model.Transaction;

/**
 *
 * @author jishu.ly
 * @version $Id: IRouter.java, v 0.1 2018��11��23�� ����12:00 jishu.ly Exp $
 */
public interface  IRouter<T> {

    void route(Transaction<T> transactions) ;

}