package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.entity.Item;
import com.example.service.ItemDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/item")
public class ItemController {
    // autowired는 객체생성한것이라 생각
    // ex> ItemDB itemdb = new ItemDB();
    @Autowired
    private ItemDB itemdb;

    // resources/하위풀더의 파일을 읽기위한 객체생성
    @Autowired
    private ResourceLoader resource;

    @PostMapping(value = "/update")
    public String updatePOST(@ModelAttribute Item item, @RequestParam(name = "image") MultipartFile file)
            throws IOException {
        if (!file.isEmpty()) {
            item.setFiletype(file.getContentType());
            item.setFilename(file.getName());
            item.setFilesize(file.getSize());
            item.setFiledate(file.getBytes());
        }
        int ret = itemdb.updateItemOne(item);
        if (ret == 1) {
            return "redirect:/item/selectlist";
        }

        return "redirect:/item/update?code=" + item.getCode();
    }

    @GetMapping(value = "/update")
    public String updateGET(Model model, @RequestParam(name = "code") long code) {
        System.out.println(code);
        Item item = itemdb.selectOneItem(code);
        model.addAttribute("item", item);

        return "item/update";
    }

    @GetMapping(value = "delete")
    public String deleteGET(@RequestParam(name = "id") Long id) {

        int ret = itemdb.deleteItem(id);
        if (ret == 1) {
            return "redirect:/item/selectlist";
        }
        return "redirect:/item/selectlist";
    }

    @GetMapping(value = "selectlist")
    public String selectListGET(Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "text", defaultValue = "") String text) {
        if (page == 0) {
            return "redirect:/item/selectlist?page=1";
        }
        // 1페이지 > 0
        // 2페이지 > 1
        List<Item> list = itemdb.selectListItem(page, text);
        long pages = itemdb.countSearchItem(text);

        model.addAttribute("list", list);
        model.addAttribute("pages", (pages - 1) / 10 + 1);
        return "/item/selectlist";
    }

    @GetMapping(value = "/insert")
    public String insertGET() {
        return "item/insert";
    }

    @PostMapping(value = "insert")
    public String insertPOST(
            @ModelAttribute Item item,
            @RequestParam(name = "image") MultipartFile file) throws IOException {

        if (file.isEmpty() == false) {
            item.setFiletype(file.getContentType());
            item.setFilename(file.getName());
            item.setFilesize(file.getSize());
            item.setFiledate(file.getBytes());
        }
        int ret = itemdb.insertItem(item);
        if (ret == 1) {
            return "redirect:/item/selectlist";
        }
        return "redirect:/item/insert";
    }

    // @GetMapping(value = "/selectone")
    // public String selectOneGET(@RequestParam(name = "id") long id){
    // int ret = itemdb.selectOneItem(id);
    // if(ret == 1){
    // return "redirect:/item/selectone"
    // }
    // return "redirect:/item/selectone"
    // }

    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> imageGET(
            @RequestParam(name = "code") long code) {
        try {
            Item item = itemdb.selectOneImage(code);
            if (item.getFilesize() > 0) { // 이미지가 있음
                HttpHeaders headers = new HttpHeaders();
                if (item.getFiletype().equals("image/jpeg")) {
                    headers.setContentType(MediaType.IMAGE_JPEG);
                } else if (item.getFiletype().equals("image/png")) {
                    headers.setContentType(MediaType.IMAGE_PNG);
                }
                if (item.getFiletype().equals("image/gif")) {
                    headers.setContentType(MediaType.IMAGE_GIF);
                }
                // 생성자(실제데이터, headers, 200)
                ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(item.getFiledate(), headers,
                        HttpStatus.OK);
                return response;
            } else {
                InputStream stream = resource.getResource("classpath:/static/img/3.jpg").getInputStream();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(stream.readAllBytes(), headers,
                        HttpStatus.OK);
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
