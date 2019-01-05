/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package core;

import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import constant.FlowStatus;
import model.InvocationMethod;
import model.Transaction;
import model.TransformModel;
import router.IRouter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 * @author jishu.ly
 * @version $Id: InvocationProcessor.java, v 0.1 2018年11月29日 下午10:18 jishu.ly Exp $
 */
public class InvocationProcessor<T> {

    private Logger logger = LoggerFactory.getLogger(FlowDispatcher.class);

    private FlowBuilder flowBuilder;



    public IRouter<T> doInvoke(Transaction<T> transaction) {
        FlowStatus currentStatus = transaction.getCurrentStatus();
        FlowStatus destStatus = transaction.getDestStatus();
        String flowName = transaction.getFlowName();

        Map<TransformModel, InvocationMethod> flowMap = flowBuilder.getFlowMap(flowName);

        TransformModel transformModel = new TransformModel();
        transformModel.setCurrentStatus(currentStatus);
        transformModel.setDestStatus(destStatus);
        transformModel.setAction(transaction.getAction());

        InvocationMethod invocationMethod = getInvocationMethod(transformModel,flowMap);

        Method method = invocationMethod.getMethod();
        Object bean  =  invocationMethod.getBean();

        try {
            method.invoke(bean, transaction);

            return invocationMethod.getRouter();
        } catch (IllegalAccessException e) {
            logger.error("", e);

            return invocationMethod.getFailRouter();
        } catch (InvocationTargetException e) {
            logger.error("", e);

            return invocationMethod.getFailRouter();
        } catch (Exception e) {
            logger.error("", e);

            return invocationMethod.getFailRouter();
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
}