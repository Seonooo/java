package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.NationEntity;
import com.example.service.NationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/nation")
public class NationRestController {

    @Autowired
    NationService nService;

    @PostMapping(value = "/insertnationlist", consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> insertPOST(
            @RequestBody List<NationEntity> nationlist) {
        Map<String, Object> map = new HashMap<>();
        try {
            int ret = nService.insertNationList(nationlist);
            if (ret == 1) {
                map.put("status", 200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }
}
