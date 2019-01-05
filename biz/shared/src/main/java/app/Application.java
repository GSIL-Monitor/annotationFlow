/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package app;

import constant.FlowConst;
import constant.FlowStatus;
import core.FlowDispatcher;
import model.InfoTransaction;
import model.Transaction;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author jishu.ly
 * @version $Id: Application.java, v 0.1 2018��11��23�� ����11:25 jishu.ly Exp $
 */
public class Application {

    public static void  main(String [] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {
                "META-INF/spring/biz-shared.xml"
                });

        File directory = new File("");//����Ϊ��
        String courseFile = directory.getCanonicalPath() ;
        System.out.println(directory.getAbsolutePath());

        //System.out.println( System.getProperty("java.class.path"));

        //System.setProperty("log.dir", logDir);
        FlowDispatcher dispatcherHandler = (FlowDispatcher) applicationContext.getBean("dispatcher");

        Transaction<InfoTransaction> transaction = new Transaction<InfoTransaction>();
        InfoTransaction infoTransaction = new InfoTransaction();
        transaction.setDestStatus(FlowStatus.ACCEPT);
        transaction.setCurrentStatus(FlowStatus.NULL);
        transaction.setAction("");
        transaction.setBizObject(infoTransaction);
        transaction.setFlowName(FlowConst.SIMPLESYNCFLOW);
        System.out.println(dispatcherHandler);

        dispatcherHandler.handle(transaction);

        System.out.println("end");

    }

}