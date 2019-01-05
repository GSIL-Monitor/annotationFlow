/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package constant;

/**
 *
 * @author jishu.ly
 * @version $Id: RouterEnum.java, v 0.1 2018��11��23�� ����12:25 jishu.ly Exp $
 */
public enum  RouterEnum {

    SUCC_ROUTER("router", ""),
    FAIL_ROUTER("fail", "");


    /** ���� */
    private final String code;

    /** ���� */
    private final String message;


    RouterEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private  String getCode() {

        return code;
    }


}