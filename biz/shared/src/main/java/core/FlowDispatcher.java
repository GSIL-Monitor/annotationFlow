/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package core;

import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import model.InvocationMethod;
import model.Transaction;
import model.TransformModel;
import router.IRouter;

import java.util.Map;

/**
 *
 * @author jishu.ly
 * @version $Id: FlowDispatcher.java
 */
public class FlowDispatcher<T> {

    private Logger logger = LoggerFactory.getLogger(FlowDispatcher.class);

    private FlowBuilder flowBuilder;

    private  InvocationProcessor invocationProcessor;


    public void handle(Transaction<T> transaction) {
        doHandle(transaction);
    }

    private void doHandle(Transaction<T> transaction) {
        IRouter iRouter = invocationProcessor.doInvoke(transaction);
        if (iRouter == null) {
            return;
        }
        iRouter.route(transaction);
        // 循环到无法路由
        while (null != iRouter) {
            iRouter = invocationProcessor.doInvoke(transaction);
            if (iRouter == null) {
                logger.info("无路由服务可用");
                break;
            }
            iRouter.route(transaction);
        }
    }




    /**
     *
     *
     * @param transformModel
     * @param flowMap
     * @return
     */
    InvocationMethod getInvocationMethod(TransformModel transformModel,  Map<TransformModel, InvocationMethod> flowMap ) {

        InvocationMethod invocationMethod =  flowMap.get(transformModel);

        return invocationMethod;
    }

    public FlowBuilder getFlowBuilder() {
        return flowBuilder;
    }

    public void setFlowBuilder(FlowBuilder flowBuilder) {
        this.flowBuilder = flowBuilder;
    }

    public InvocationProcessor getInvocationProcessor() {
        return invocationProcessor;
    }

    public void setInvocationProcessor(InvocationProcessor invocationProcessor) {
        this.invocationProcessor = invocationProcessor;
    }
}