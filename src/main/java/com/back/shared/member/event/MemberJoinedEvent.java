package com.back.shared.member.event;

import com.back.shared.member.dto.MemberDto;
import com.back.standard.event.HasEventName;

public record MemberJoinedEvent(
        MemberDto member
) implements HasEventName {
}