package com.back.boundedContext.post.in;

import com.back.shared.member.event.MemberJoinedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostEventListener {
    @KafkaListener(topics = "MemberJoinedEvent", groupId = "PostEventListener__handle__1")
    public void handle(MemberJoinedEvent event) {
        System.out.println("handle, MemberJoinedEvent, member: " + event.member().id());
    }

    // handle2, handle2Again 는 같은 groupId 를 사용하므로 같은 consumer group 에 속하게 된다.
    // 즉, 둘 중 하나의 메서드에서만 이벤트가 처리된다.
    @KafkaListener(topics = "MemberJoinedEvent", groupId = "PostEventListener__handle__2")
    public void handle2(MemberJoinedEvent event) {
        System.out.println("handle2, MemberJoinedEvent, member: " + event.member().id());
    }

    // handle2, handle2Again 는 같은 groupId 를 사용하므로 같은 consumer group 에 속하게 된다.
    // 즉, 둘 중 하나의 메서드에서만 이벤트가 처리된다.
    @KafkaListener(topics = "MemberJoinedEvent", groupId = "PostEventListener__handle__2")
    public void handle2Again(MemberJoinedEvent event) {
        System.out.println("handle2-again, MemberJoinedEvent, member: " + event.member().id());
    }
}
