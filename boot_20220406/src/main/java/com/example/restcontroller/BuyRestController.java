package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.BuyEntity;
import com.example.entity.BuyProjection;
import com.example.entity.ItemEntity;
import com.example.entity.MemberEntity;
import com.example.repository.BuyRepository;

@RestController
@RequestMapping(value = "/api/buy")
public class BuyRestController {

        @Autowired
        BuyRepository bRepository;

        @RequestMapping(value = "/insert", method = { RequestMethod.POST }, consumes = {
                        MediaType.ALL_VALUE }, produces = {
                                        MediaType.APPLICATION_JSON_VALUE })
        public Map<String, Object> buyInsertPost(
                        @RequestBody Map<String, Object> buy) {
                System.out.println(buy.toString());

                BuyEntity buyEntity = new BuyEntity();
                buyEntity.setBcnt(Long.parseLong(buy.get("bcnt").toString()));

                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setIcode(
                                Long.parseLong(buy.get("icode").toString()));
                buyEntity.setItem(itemEntity);

                MemberEntity memberEntity = new MemberEntity();
                memberEntity.setUemail((String) buy.get("uemail"));
                buyEntity.setMember(memberEntity);

                bRepository.save(buyEntity);

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("status", 200);
                return map;
        }

        // { bcnt:2, item:{icode: 3}, member:{uemail:'b3'}}
        @RequestMapping(value = "/insert1", method = { RequestMethod.POST }, consumes = {
                        MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
        public Map<String, Object> buyInsertPost(
                        @RequestBody BuyEntity buyEntity) {
                System.out.println(buyEntity.toString());
                Map<String, Object> map = new HashMap<String, Object>();
                bRepository.save(buyEntity);
                map.put("status", 200);
                return map;
        }

        // 127.0.0.1:9090/ROOT/api/buy/selectone?bno=8
        @RequestMapping(value = "/selectone", method = { RequestMethod.GET }, consumes = {
                        MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
        public Map<String, Object> buySelectOneGET(

                        @RequestParam(name = "bno") long bno) {
                System.out.println(bno);
                Map<String, Object> map = new HashMap<String, Object>();

                // BuyEntity buy = bRepository.findById(bno).orElse(null);

                BuyProjection buy = bRepository.findByBno(bno);
                map.put("result", buy);
                map.put("status", 200);
                return map;
        }

        @RequestMapping(value = "/select", method = { RequestMethod.GET }, consumes = {
                        MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
        public Map<String, Object> buySelectListGET(

                        @RequestParam(name = "uemail") String uemail) {
                Map<String, Object> map = new HashMap<String, Object>();

                // BuyEntity buy = bRepository.findById(bno).orElse(null);

                List<BuyProjection> list = bRepository.findByMember_uemail(uemail);
                map.put("result", list);
                map.put("status", 200);
                return map;
        }

}
