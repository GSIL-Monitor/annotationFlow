/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package action;

import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import model.Transaction;

/**
 *
 * @author jishu.ly
 * @version $Id: AccecptAction.java, v 0.1 2018年11月23日 上午12:30 jishu.ly Exp $
 */
public class AccecptAction<T> implements IAction<T> {

    Logger logger = LoggerFactory.getLogger(AccecptAction.class);
    @Override
    public void process(Transaction<T> transaction) {
        logger.info("AccecptAction process ");
        System.out.println("accept");
    }
}