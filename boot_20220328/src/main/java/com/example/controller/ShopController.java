package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.dto.ItemDTO;
import com.example.mapper.ItemImageMapper;
import com.example.mapper.ItemMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    ItemImageMapper imgMapper;

    @Autowired
    HttpSession httpSession;

    @PostMapping(value = "cart")
    public String cartPOST(@RequestParam(name = "code") long code,
            @RequestParam(name = "cnt") long cnt) {
        String em = (String) httpSession.getAttribute("M_UEMAIL");
        if (em != null) {

            return "";
        } else {
            httpSession.getId();

        }

        return "redirect:/shop/detail?code=" + code;
    }

    @GetMapping(value = { "/", "/home" })
    public String shopGET(Model model) {
        // 등록일
        List<ItemDTO> list1 = itemMapper.selectItemList(1);
        model.addAttribute("list1", list1);

        // 물품명
        List<ItemDTO> list2 = itemMapper.selectItemList(2);
        model.addAttribute("list2", list2);

        // 가격
        List<ItemDTO> list3 = itemMapper.selectItemList(3);
        model.addAttribute("list3", list3);

        return "/shop/home";
    }

    @GetMapping(value = "/detail")
    public String detailGET(Model model, @RequestParam(name = "code") long code) {
        ItemDTO item = itemMapper.selectItemOne(code);
        model.addAttribute("obj", item);

        List<Long> list = imgMapper.selectItemImageCodeList(code);
        model.addAttribute("list", list);
        return "/shop/detail";
    }

}
