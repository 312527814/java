package com.my.producers;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class JMSQueueTranProducer {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory=
                new ActiveMQConnectionFactory
                        ("tcp://192.168.77.130:61616");
        Connection connection=null;
        try {

            connection=connectionFactory.createConnection();
            connection.start();

            Session session=connection.createSession
                    (Boolean.TRUE,Session.CLIENT_ACKNOWLEDGE);

            //创建目的地
            Destination destination=session.createQueue("myQueue");
            //创建发送者
            MessageProducer producer=session.createProducer(destination);
            int deliveryMode = producer.getDeliveryMode();
//            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
          //  producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            for(int i=0;i<1000;i++) {
                BytesMessage bytesMessage = session.createBytesMessage();

                //创建需要发送的消息
                TextMessage message = session.createTextMessage("Hello World:"+i);
                //Text   Map  Bytes  Stream  Object
                producer.send(message);
                System.out.println(message.getText());

                try {
                    Thread.sleep(1000*2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

//            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
