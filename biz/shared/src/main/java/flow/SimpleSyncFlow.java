/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package flow;

import action.IAction;
import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import constant.FlowConst;
import constant.FlowStatus;
import core.FlowBean;
import core.Transform;
import model.Transaction;
import router.IRouter;

/**
 *
 * @author jishu.ly
 * @version $Id: SimpleSyncFlow.java, v 0.1 2018��11��23�� ����12:15 jishu.ly Exp $
 */

@FlowBean(flowName = FlowConst.SIMPLESYNCFLOW)
public class SimpleSyncFlow<T> {

    Logger logger = LoggerFactory.getLogger(SimpleSyncFlow.class);

    private IAction<T>  acceptAction;

    private IAction<T>  submitAction;

    private IRouter<T> failRouter;

    private IRouter<T> router;



    @Transform(currentStatus = FlowStatus.NULL, destStatus = FlowStatus.ACCEPT, router = "router",
    failRouter = "failRouter")
    public void transNUllToAC(Transaction<T> transaction) {
        logger.info("transNUllToAC");
        acceptAction.process(transaction);
    }

    @Transform(currentStatus = FlowStatus.ACCEPT, destStatus = FlowStatus.SUBMIT, router = "router",
            failRouter = "failRouter")
    public void accToSubmit(Transaction<T> transaction) {
        logger.info("accToSubmit");
        logger.info("transACToSU");
    }

    @Transform(currentStatus = FlowStatus.SUBMIT, destStatus = FlowStatus.SUCCESS, router = "router",
            failRouter = "failRouter")
    public void transSMToSU(Transaction<T> transaction) {
        logger.info("transSMToSU");
        submitAction.process(transaction);
        throw  new RuntimeException("transACToSU fail");
    }

    @Transform(currentStatus = FlowStatus.SUBMIT, destStatus = FlowStatus.FAIL)
    public void transSUBMITToFA(Transaction<T> transaction) {
        logger.info("transSUBMITToFA");
    }


    public void  transAcToSm(Transaction<T> transaction) {
        submitAction.process(transaction);
    }

    public IAction<T> getAcceptAction() {
        return acceptAction;
    }

    public void setAcceptAction(IAction<T> acceptAction) {
        this.acceptAction = acceptAction;
    }

    public IAction<T> getSubmitAction() {
        return submitAction;
    }

    public void setSubmitAction(IAction<T> submitAction) {
        this.submitAction = submitAction;
    }

    public IRouter<T> getFailRouter() {
        return failRouter;
    }

    public void setFailRouter(IRouter<T> failRouter) {
        this.failRouter = failRouter;
    }

    public IRouter<T> getRouter() {
        return router;
    }

    public void setRouter(IRouter<T> router) {
        this.router = router;
    }
}