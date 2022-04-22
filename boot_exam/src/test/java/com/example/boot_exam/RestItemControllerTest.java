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

import com.example.entity.Item;
import com.example.entity.Member;

public class RestItemControllerTest {

    RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void insertItem() {
        Item item = new Item();
        item.setName("A13");
        item.setPrice(970L);
        item.setQuantity(300L);
        item.setContent("B13");

        Member member = new Member();
        member.setUserid("a");
        item.setMember(member);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Item> entity = new HttpEntity<>(item, headers);

        String url = "http://127.0.0.1:9090/ROOT/item/insert.rest";
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(result.getBody());

        assertThat(result.getBody().toString()).isEqualTo("{\"status\":200}");
    }

    @Test
    public void deleteItem() {
        Item item = new Item();
        item.setName("A12");
        // jwt security config filter //
        String url = "http://127.0.0.1:9090/ROOT/item/delete.rest?name=" + item.getName();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Item> entity = new HttpEntity<>(item, headers);

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
        System.out.println(result.getBody());

        assertThat(result.getBody().toString()).isEqualTo("{\"status\":200}");

    }

    @Test
    public void updateItem() {
        Item item = new Item();
        item.setNo(1L);

        item.setName("A22");
        item.setContent("B22");
        item.setPrice(1880L);
        item.setQuantity(220L);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Item> entity = new HttpEntity<>(item, headers);

        String url = "http://127.0.0.1:9090/ROOT/item/update.rest";
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        System.out.println(result.getBody());

        assertThat(result.getBody().toString()).isEqualTo("{\"status\":200}");

    }

    @Test
    public void selectItemListGET() {
        Item item = new Item();
        item.setPrice(10320L);
        String url = "http://127.0.0.1:9090/ROOT/item/select.rest?price=" + item.getPrice();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("result : " + result);
        assertThat(result).contains("\"status\":200");

    }
}
