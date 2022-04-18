package com.example.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.ProductEntity;
import com.example.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*") // 앱으로 만들경우 앱에서 데이터를 가져갈 수 없음.
@RequestMapping(value = "/api/product")
public class ProductRestController {

    @Autowired
    ProductRepository pRepository;

    // json => RequestBody
    // form-data => ModelAttribute // 이미지, 배열, 등등.. body사용이 힘든경우에
    // 127.0.0.1:9090/ROOT/api/product/insert_json
    @PostMapping(value = "/insert.json", consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> insertPost(@ModelAttribute ProductEntity product,
            @RequestParam(name = "file", required = false) MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (file != null) {
                if (!file.isEmpty()) {
                    product.setImagedata(file.getBytes());
                    product.setImagename(file.getOriginalFilename());
                    product.setImagesize(file.getSize());
                    product.setImagetype(file.getContentType());
                }
            }
            pRepository.save(product);

            map.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }

    @PostMapping(value = "/insertbatch.json", consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> insertBatchPost(
            @RequestParam(name = "name") String[] name,
            @RequestParam(name = "price") Long[] price,
            @RequestParam(name = "file", required = false) MultipartFile[] files) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<ProductEntity> list = new ArrayList<>();
            ProductEntity prd = new ProductEntity();
            for (int i = 0; i < name.length; i++) {
                prd.setName(name[i]);
                prd.setPrice(price[i]);
                if (!files[i].isEmpty()) {
                    prd.setImagedata(files[i].getBytes());
                    prd.setImagename(files[i].getOriginalFilename());
                    prd.setImagesize(files[i].getSize());
                    prd.setImagetype(files[i].getContentType());
                }
                list.add(prd);
            }
            pRepository.saveAll(list);
            map.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }

    @PutMapping(value = "/update.json", consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> updatePatch(@RequestBody ProductEntity product) {
        Map<String, Object> map = new HashMap<>();
        try {
            ProductEntity product1 = pRepository.findById(product.getNo()).orElse(null);
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            pRepository.save(product1);
            map.put("status", 200);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);

        }
        return map;

    }
}
