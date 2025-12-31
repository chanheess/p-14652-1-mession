package com.back.infra.kafka;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@EmbeddedKafka  //테스트 환경에서 카프카 처리
@SpringBootTest
class KafkaTest {
    @Autowired
    private MyEventListener listener;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;    // <String, Object> 뒤에 보낼 정보에 따라서 처리

    //카프카가 제대로 구동되는지 테스트
    @Test
    @DisplayName("이벤트 수신 테스트")
    void t001() throws InterruptedException {
        MyEvent myEvent = new MyEvent("hello Kafka");
        kafkaTemplate.send(myEvent.getEventName(), myEvent);

        boolean received = listener.getLatch().await(10, TimeUnit.SECONDS);

        assertThat(received).isTrue();
        assertThat(listener.getReceivedEvent().msg()).isEqualTo("hello Kafka");
    }
}