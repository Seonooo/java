package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import com.example.dto.ItemDTO;
import com.example.dto.ItemImageDTO;
import com.example.mapper.ItemImageMapper;
import com.example.service.ItemImageService;
import com.example.service.ItemService;

@Controller
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    ItemService iService;

    @Autowired
    ItemImageService iiService;

    @Autowired
    ItemImageMapper imgMapper;

    @Autowired
    ResourceLoader resLoader;

    @GetMapping(value = "/insert")
    public String insertGET() {
        return "/item/insert";
    }

    @PostMapping(value = "/insert")
    public String insertPOST(HttpSession httpSession, @ModelAttribute ItemDTO item,
            @RequestParam(name = "timage") MultipartFile file) throws IOException {
        System.out.println(item.toString());
        System.out.println(file.getOriginalFilename());

        // 파일관련내용
        item.setIimagetype(file.getContentType());
        item.setIimagename(file.getOriginalFilename());
        item.setIimagesize(file.getSize());
        item.setIimage(file.getBytes());

        // 세션에서 이메일 꺼내기
        String em = (String) httpSession.getAttribute("SESSION_UEMAIL");
        item.setUemail(em);

        int ret = iService.insertItemOne(item);
        if (ret == 1) {
            return "redirect:/item/selectlist";
        }
        return "redirect:/item/insert";
    }

    // 127.0.0.1:9090/ROOT/item/selectlist?txt=검색어&page=1
    @GetMapping(value = "/selectlist")
    public String selectListGET(Model model, HttpSession httpSession,
            @RequestParam(name = "txt", defaultValue = "") String txt,
            @RequestParam(name = "page", defaultValue = "1") int page) {

        String em = (String) httpSession.getAttribute("SESSION_UEMAIL");
        if (em != null) {
            String role = (String) httpSession.getAttribute("SESSION_UROLE");
            if (role.equals("SELLER")) {
                Map<String, Object> map = new HashMap<>();

                map.put("txt", txt);
                map.put("start", page * 10 - 9);
                map.put("end", page * 10);
                map.put("uemail", em);

                List<ItemDTO> list = iService.selectItemList(map);
                model.addAttribute("list", list);

                long cnt = iService.selectItemCount(map);
                model.addAttribute("pages", (cnt - 1) / 10 + 1);
                return "/item/selectlist";
            }

        }
        return "redirect:/seller/select";
    }

    @GetMapping(value = "/selectone")
    public String selectoneGET(Model model, @RequestParam(name = "code") Long code) {
        ItemDTO item = iService.selectItemOne(code);
        model.addAttribute("item", item);

        List<Long> imgcode = iiService.selectItemImageList(code);
        model.addAttribute("imgcode", imgcode);

        return "/item/selectone";
    }

    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> imageGET(Model model, @RequestParam(name = "code") Long code) throws IOException {
        ItemDTO item = iService.selectItemImageOne(code);
        {
            {
                if (item.getIimagesize() > 0) {
                    HttpHeaders headers = new HttpHeaders();
                    if (item.getIimagetype().equals("image/gif")) {
                        headers.setContentType(MediaType.IMAGE_GIF);
                    } else if (item.getIimagetype().equals("image/png")) {
                        headers.setContentType(MediaType.IMAGE_PNG);
                    } else if (item.getIimagetype().equals("image/jpeg")) {
                        headers.setContentType(MediaType.IMAGE_JPEG);
                    }
                    ResponseEntity<byte[]> response = new ResponseEntity<>(item.getIimage(), headers, HttpStatus.OK);
                    return response;
                } else {
                    InputStream is = resLoader
                            .getResource("classpath:/static/img/default.jpg")
                            .getInputStream();

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.IMAGE_JPEG);

                    ResponseEntity<byte[]> response = new ResponseEntity<>(is.readAllBytes(),
                            headers, HttpStatus.OK);

                    return response;
                }
            }
        }
    }

    @GetMapping(value = "/subimage")
    public ResponseEntity<byte[]> subimageGET(Model model, @RequestParam(name = "code") Long code)
            throws IOException {
        ItemImageDTO item = imgMapper.selectItemImageCodeOne(code);
        {
            {
                if (item.getIimagesize() > 0) {
                    HttpHeaders headers = new HttpHeaders();
                    if (item.getIimagetype().equals("image/gif")) {
                        headers.setContentType(MediaType.IMAGE_GIF);
                    } else if (item.getIimagetype().equals("image/png")) {
                        headers.setContentType(MediaType.IMAGE_PNG);
                    } else if (item.getIimagetype().equals("image/jpeg")) {
                        headers.setContentType(MediaType.IMAGE_JPEG);
                    }
                    ResponseEntity<byte[]> response = new ResponseEntity<>(item.getIimage(), headers, HttpStatus.OK);
                    return response;
                } else {
                    InputStream is = resLoader
                            .getResource("classpath:/static/img/default.jpg")
                            .getInputStream();

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.IMAGE_JPEG);

                    ResponseEntity<byte[]> response = new ResponseEntity<>(is.readAllBytes(),
                            headers, HttpStatus.OK);

                    return response;
                }
            }
        }
    }

    @PostMapping(value = "/insertimages")
    public String insertImagePOST(@RequestParam(name = "timage") MultipartFile[] files,
            @RequestParam(name = "icode") Long icode, @ModelAttribute ItemImageDTO iimage) throws IOException {

        // itemimagedto를 n개 보관할 list
        List<ItemImageDTO> list = new ArrayList<>();
        for (MultipartFile file : files) {
            ItemImageDTO obj = new ItemImageDTO();
            obj.setIcode(icode); // 물품코드
            obj.setIimage(file.getBytes());
            obj.setIimagename(file.getOriginalFilename());
            obj.setIimagesize(file.getSize());
            obj.setIimagetype(file.getContentType());

            list.add(obj);
        }
        iiService.insertItemImageBatch(list);
        return "redirect:/item/selectone?code=" + icode;

    }

}
