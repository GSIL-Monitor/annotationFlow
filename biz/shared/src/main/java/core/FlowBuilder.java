/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package core;

import com.alibaba.common.lang.StringUtil;
import constant.FlowStatus;
import model.InvocationMethod;
import model.TransformModel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import router.IRouter;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * @author jishu.ly
 * @version $Id: FlowBuilder.java, v 0.1 2018��11��23�� ����12:38 jishu.ly Exp $
 */
public class FlowBuilder implements BeanPostProcessor , ApplicationContextAware {

    private static Map<String, ConcurrentHashMap<TransformModel, InvocationMethod>>  transFormMap =
            new ConcurrentHashMap<String, ConcurrentHashMap<TransformModel, InvocationMethod>> ();


    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {

        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {

        FlowBean flowBean =   bean.getClass().getAnnotation(FlowBean.class);
        if (null == flowBean) {
            return bean;
        }

        Class clazz  = bean.getClass();

        String flowName = flowBean.flowName();

        ConcurrentHashMap<TransformModel, InvocationMethod> flowTransformMap = transFormMap.get(flowName);
        if (null == flowTransformMap) {
            flowTransformMap = new ConcurrentHashMap<TransformModel, InvocationMethod>();
            transFormMap.put(flowName, flowTransformMap);
        }



        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        if (methods != null) {
            for (Method method : methods) {
                Transform transform = AnnotationUtils.findAnnotation(method, Transform.class);
                // process

                if (transform != null) {
                    FlowStatus currentStatus = transform.currentStatus();
                    FlowStatus destStatus = transform.destStatus();
                    String action = transform.action();
                    String router = transform.router();
                    String failRouter = transform.failRouter();


                    IRouter routerBean = getBean(router);
                    IRouter failRouterBean = getBean(failRouter);

                    TransformModel transformModel = new TransformModel();
                    transformModel.setCurrentStatus(currentStatus);
                    transformModel.setDestStatus(destStatus);
                    transformModel.setAction(action);

                    // ���õ��÷���
                    InvocationMethod invocationMethod = new InvocationMethod();
                    invocationMethod.setBean(bean);
                    invocationMethod.setMethod(method);
                    invocationMethod.setCalzz(clazz);
                    invocationMethod.setRouter(routerBean);
                    invocationMethod.setFailRouter(failRouterBean);

                    flowTransformMap.put(transformModel, invocationMethod);
                }
            }
        }
        return bean;
    }

    /**
     *
     * @param flowName
     * @return
     */
    public ConcurrentHashMap<TransformModel, InvocationMethod> getFlowMap(String flowName) {
        ConcurrentHashMap<TransformModel, InvocationMethod>  map = transFormMap.get(flowName);

        return map;
    }


    private  ApplicationContext applicationContext = null;

    /**
     * ��ȡ��̬�����е�ApplicationContext.
     */
    public  ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * �Ӿ�̬����applicationContext�еõ�Bean, �Զ�ת��Ϊ����ֵ���������.
     */
    @SuppressWarnings("unchecked")
    public  <T> T getBean(String name) {
        if (StringUtil.isBlank(name)) {
            return null;
        }
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * �Ӿ�̬����applicationContext�еõ�Bean, �Զ�ת��Ϊ����ֵ���������.
     */
    public  <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    /**
     * ���SpringContextHolder�е�ApplicationContextΪNull.
     */
    public  void clearHolder() {
        applicationContext = null;
    }

    void assertContextInjected() {
        //Assert.isNull(applicationContext, "applicationContext is null");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}