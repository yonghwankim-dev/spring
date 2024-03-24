package com.jojoldu.book.springboot.web.dto;

import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class HelloResponseDtoTest {

    @Test
    @DisplayName("롬복 기능 테스트")
    public void lombok() {
        //given
        String name = "test";
        int amount = 1000;
        //when
        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);
        //then
        Assertions.assertThat(helloResponseDto.getName()).isEqualTo(name);
        Assertions.assertThat(helloResponseDto.getAmount()).isEqualTo(amount);
    }
}
