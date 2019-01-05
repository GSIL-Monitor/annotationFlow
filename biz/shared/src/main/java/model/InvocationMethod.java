/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package model;

import router.IRouter;

import java.lang.reflect.Method;

/**
 *
 * @author jishu.ly
 * @version $Id: InvocationMethod.java, v 0.1 2018年11月23日 上午12:56 jishu.ly Exp $
 */
public class InvocationMethod {

    private Class calzz;

    private Method method;

    private Object  bean;

    private IRouter router;

    private IRouter failRouter;

    public Class getCalzz() {
        return calzz;
    }

    public void setCalzz(Class calzz) {
        this.calzz = calzz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public IRouter getRouter() {
        return router;
    }

    public void setRouter(IRouter router) {
        this.router = router;
    }

    public IRouter getFailRouter() {
        return failRouter;
    }

    public void setFailRouter(IRouter failRouter) {
        this.failRouter = failRouter;
    }
}