package com.example.template;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MarketingService {

    @Autowired
    UserOrderHistoryRepository repository;

    @KafkaListener(topics = "${eventTopic}")
    public void onListener(@Payload String message, ConsumerRecord<?, ?> consumerRecord) {
//        System.out.println("##### listener : " + message);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        DeliveryCompleted deliveryCompleted = null;
        try {
            deliveryCompleted = objectMapper.readValue(message, DeliveryCompleted.class);

            /**
             * 배송이 완료되었을때 상품 선호도를 수집한다
             */
            if( deliveryCompleted.getEventType() != null && deliveryCompleted.getEventType().equals(DeliveryCompleted.class.getSimpleName())){
                // TODO 상품선호도 수집
                System.out.println(" #### 상품선호도 수집 = 고객 ID : " + deliveryCompleted.getCustomerName() + " , 상품명 : " + deliveryCompleted.getProductName());

                UserOrderHistory userOrderHistory = new UserOrderHistory();
                userOrderHistory.setOrderId(deliveryCompleted.getOrderId());
                userOrderHistory.setProductName(deliveryCompleted.getProductName());
                userOrderHistory.setQuantity(deliveryCompleted.getQuantity());
                userOrderHistory.setTimestamp(deliveryCompleted.getTimestamp());
                userOrderHistory.setUserId(deliveryCompleted.getCustomerName());

                repository.save(userOrderHistory);
            }

        }catch (Exception e){

        }
    }
}
