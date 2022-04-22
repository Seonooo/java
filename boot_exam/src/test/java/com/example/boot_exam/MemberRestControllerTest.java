package com.example.boot_exam;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.entity.Member;

public class MemberRestControllerTest {

    RestTemplate restTemplate;

    // 테스트 수행전 필요한 객체 생성 위치
    @BeforeEach
    public void setup() {
        restTemplate = new RestTemplate();
    }

    // 판매자 등록 테스트
    @Test
    public void insertMember() {
        // 테스트할 객체 생성
        Member member = new Member();
        member.setUserid("a");
        member.setUserpw("a");
        member.setUsername("가나다");
        member.setUsertel("010-0000-0001");
        member.setUserrole("SELLER");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Member> entity = new HttpEntity<>(member, headers);

        String url = "http://127.0.0.1:9090/ROOT/member/insert.rest";
        ResponseEntity<String> result = restTemplate.exchange(url,
                HttpMethod.POST, entity, String.class);
        System.out.println(result.getBody());

        // 반환되는 결과값이 정확하게 일치하는지 확인
        // {"status":200} = "{\"status\":200}"
        assertThat(result.getBody().toString()).isEqualTo("{\"status\":200}");
    }

}