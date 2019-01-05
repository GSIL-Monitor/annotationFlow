/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package constant;

/**
 *
 * @author jishu.ly
 * @version $Id: FlowStatus.java, v 0.1 2018��11��22�� ����11:48 jishu.ly Exp $
 */
public enum  FlowStatus  implements BaseEnum{

    NULL("NULL", ""),
    ACCEPT("AC", ""),
    PROCESS("PR", ""),
    SUBMIT("SM", ""),
    CHECKDATA("CD", ""),
    SUCCESS("SU", ""),
    FAIL("FA", "");


    /** ���� */
    private final String code;

    /** ���� */
    private final String message;


    FlowStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private  String getCode() {

        return code;
    }

}