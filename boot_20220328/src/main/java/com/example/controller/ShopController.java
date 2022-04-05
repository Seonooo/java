package com.example.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.dto.BuyDTO;
import com.example.dto.CartDTO;
import com.example.dto.ItemDTO;
import com.example.mapper.BuyMapper;
import com.example.mapper.CartMapper;
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
    CartMapper cMapper;

    @Autowired
    BuyMapper bMapper;

    @Autowired
    HttpSession httpSession;

    @PostMapping(value = "cart")
    public String cartPOST(@RequestParam(name = "btn") String btn, @RequestParam(name = "code") long code,
            @RequestParam(name = "cnt") long cnt) {
        String em = (String) httpSession.getAttribute("M_EMAIL");
        if (em == null) {
            return "redirect:/member/login";
        }
        if (btn.equals("장바구니")) {
            CartDTO cart = new CartDTO();
            cart.setCcnt(cnt);
            cart.setIcode(code);
            cart.setUemail(em);
            cMapper.insertCartOne(cart);
            return "redirect:/shop/cartlist";
        } else if (btn.equals("주문하기")) {
            BuyDTO buy = new BuyDTO();
            buy.setBcnt(cnt);
            buy.setIcode(code);
            buy.setUemail(em);
            bMapper.insertBuyOne(buy);
            return "redirect:/shop/orderlist";
        }
        return "redirect:/";
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

    @GetMapping(value = "/buylist")
    public String buylistGET(Model model) {
        String em = (String) httpSession.getAttribute("M_EMAIL");
        if (em == null) { // 로그인 되지 않았다면
            return "redirect:/member/login";
        }

        // 로그인 되었을 때
        List<Map<String, Object>> list = bMapper.selectBuyListMap(em);
        model.addAttribute("list", list);

        return "/shop/buylist";
    }

}
