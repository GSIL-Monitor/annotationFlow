/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package core;

import constant.FlowStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author jishu.ly
 * @version $Id: Transform.java, v 0.1 2018��11��22�� ����11:45 jishu.ly Exp $
 */

/*@Retention(RetentionPolicy.RUNTIME)
14 //Retentionע�����MyAnnotationע���������
// @Target( { ElementType.METHOD, ElementType.TYPE })*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Transform {

    FlowStatus  currentStatus ();

    FlowStatus  destStatus ();

    String action() default "";

    String  router () default "";

    String  failRouter () default "";
}