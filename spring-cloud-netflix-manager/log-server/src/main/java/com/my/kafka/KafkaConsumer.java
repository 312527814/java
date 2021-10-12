package com.my.kafka;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-10-08 17:54
 */

import com.my.log.LoggerInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 类功能描述：<br>
 * <ul>
 * <li>类功能描述1<br>
 * <li>类功能描述2<br>
 * <li>类功能描述3<br>
 * </ul>
 * 修改记录：<br>
 * <ul>
 * <li>修改记录描述1<br>
 * <li>修改记录描述2<br>
 * <li>修改记录描述3<br>
 * </ul>
 *
 * @author xuefl
 * @version 5.0 since 2020-01-13
 */
@Component
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "my-log", groupId = "log-server")
    public void topic_test(ConsumerRecord<?, LoggerInfo> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        try {
            LoggerInfo loggerInfo = record.value();
            String str = loggerInfo.getDateTime() + "  " + loggerInfo.getApplication() + "(" + loggerInfo.getHost() + ":" + loggerInfo.getPort() + ") ";
            str += " [" + loggerInfo.getTheadName() + "][";
            str += loggerInfo.getClassName() + "][" + loggerInfo.getTraceId() + "," + loggerInfo.getSpanceId() + "]:" + loggerInfo.getLine();
            str += " - " + loggerInfo.getMessage() + "\n";
            switch (loggerInfo.getLevel()) {
                case "error":
                    log.error(str);
                    break;
                case "info":
                    log.info(str);
            }
        } finally {
            ack.acknowledge();
        }


    }
}
