package com.example.controller;

import java.util.Date;
import java.util.List;

import com.example.entity.Item;
import com.example.service.ItemService;
import com.example.service.SequenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    SequenceService sequenceService;

    @Autowired
    ItemService itemService;
    

    @GetMapping(value = "/insert")
    public String insertGET(){
        return "/insert";
    }

    @PostMapping(value="/insert")
    public String insertPOST(Model model, @ModelAttribute Item item) {
        item.setCode(sequenceService.generatorSequence("SEQ_ITEM_EXAM"));
        item.setRegdate( new Date() );
        item.setQuantity(item.getQuantity()+20);
        System.out.println(item.toString());

        itemService.insertItem(item);
        
        model.addAttribute("msg", "물품 등록 완료");
        model.addAttribute("url", "/item/select");
    
        return "alert";
        
    }

    @GetMapping(value="/select")
    public String selectGET(
            Model model,
            @RequestParam(name = "page", defaultValue = "1") int page ) {
        
        Pageable pageable = PageRequest.of(page-1, 5, Direction.ASC, "code");
        List<Item> list =  itemService.selectlistItem(pageable);    
        model.addAttribute("list", list);

        long pages = itemService.countItem();
        model.addAttribute("pages", (pages-1)/5+1);

        return "select";
    }

    @PostMapping(value = "/action")
    public String actionPOST(Model model,
        @RequestParam(name = "btn") String btn, @ModelAttribute Item item){
        if(btn.equals("1개수정")){
            itemService.updateItem(item);
            model.addAttribute("msg", "물품 수정 완료");
            model.addAttribute("url", "/item/select");
            return "alert";
        }
        else if(btn.equals("1개삭제")){
            itemService.deleteItem(item.getCode());
            model.addAttribute("msg", "물품 삭제 완료");
            model.addAttribute("url", "/item/select");
            return "alert";
        }
        return "redirect:/item/select";
    }

}
