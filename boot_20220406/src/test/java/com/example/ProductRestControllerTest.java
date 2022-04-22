package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.entity.ProductCountEntity;
import com.example.entity.ProductEntity;

public class ProductRestControllerTest {

    RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        restTemplate = new RestTemplate();
    }

    // http://127.0.0.1:9090/ROOT/api/product/insert.json
    // @ModelAttribute
    @Test
    public void insertTest() {
        // 전송하고자 하는 값 생성
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("name", "테스트용");
        body.add("price", 1234L);

        // header생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> result = restTemplate.exchange("http://127.0.0.1:9090/ROOT/api/product/insert.json",
                HttpMethod.POST, entity, String.class);

        assertThat(result.getStatusCode().toString()).isEqualTo("200 OK");
    }

    // http://127.0.0.1:9090/ROOT/api/productcount/insert.json
    // @RequestBody
    @Test
    public void insertProductCount() {
        ProductCountEntity productCount = new ProductCountEntity();
        productCount.setCnt(10L);
        productCount.setType("I");

        ProductEntity product = new ProductEntity();
        product.setNo(1002L);

        productCount.setProduct(product);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProductCountEntity> entity = new HttpEntity<>(productCount, headers);

        ResponseEntity<String> result = restTemplate.exchange("http://127.0.0.1:9090/ROOT/api/productcount/insert.json",
                HttpMethod.POST, entity, String.class);

        assertThat(result.getStatusCode().toString()).isEqualTo("200 OK");
    }

    @Test
    public void selectCountCount() {
        String url = "http://127.0.0.1:9090/ROOT/api/productcount/selectcount.json?no=1002";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
        assertThat(result).contains("\"status\":200");
    }

    // http://127.0.0.1:9090/ROOT/api/product/update.json
    // @RequestBody
    @Test
    public void updateTest() {
        // 전송하고자 하는 값 생성
        ProductEntity product = new ProductEntity();
        product.setNo(1002L);
        product.setName("가나다라");
        product.setPrice(9999L);

        // header생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProductEntity> entity = new HttpEntity<>(product, headers);

        ResponseEntity<String> result = restTemplate.exchange("http://127.0.0.1:9090/ROOT/api/product/update.json",
                HttpMethod.PUT, entity, String.class);

        assertThat(result.getStatusCode().toString()).isEqualTo("200 OK");
    }
}
