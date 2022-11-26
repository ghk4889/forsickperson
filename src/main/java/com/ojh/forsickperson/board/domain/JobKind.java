package com.ojh.forsickperson.board.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum JobKind {
    SIMPLE, COMPLICATED;

    //@RequestBody로 받은 String 데이터를 Enum 타입으로 매핑해준다.
    @JsonCreator
    public static JobKind from(String s) {
        return JobKind.valueOf(s.toUpperCase());
    }
}
