package com.my.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class JMSQueueExclusiveConsumer {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory
                        ("tcp://192.168.77.130:61616");
        Connection connection = null;
        try {

            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession
                    (Boolean.FALSE, Session.AUTO_ACKNOWLEDGE); //延迟确认

            //创建目的地
            Destination destination = session.createQueue("myQueue?consumer.exclusive=true&consumer.priority=10");

            //创建发送者
            MessageConsumer consumer = session.createConsumer(destination);
            TextMessage message = null;
            for (int i = 0; ; i++) {
                TextMessage textMessage = (TextMessage) consumer.receive();
                System.out.println("exclusive1:" + textMessage.getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
