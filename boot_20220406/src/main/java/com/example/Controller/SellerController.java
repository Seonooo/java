package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.dto.ItemDTO;
import com.example.entity.BuyProjection;
import com.example.entity.ItemEntity;
import com.example.entity.MemberEntity;
import com.example.mapper.ItemMapper;
import com.example.repository.BuyRepository;
import com.example.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/seller")
public class SellerController {

    // int pagecnt = 10;
    @Value("${board.page.count}")
    int PAGECNT;

    @Autowired
    ItemMapper iMapper;

    @Autowired
    BuyRepository buyRepository;

    @Autowired
    ResourceLoader resLoader;

    @Autowired
    ItemService iService;

    @GetMapping(value = "/deleteupdatebatch")
    public String deleteupdateBatchGet(@RequestParam(name = "btn") String btn, @RequestParam(name = "no") Long[] no,
            Model model) {
        if (btn.equals("일괄수정")) {
            List<ItemEntity> list = iService.selectItemEntityIn(no);
            model.addAttribute("list", list);
            return "seller/updatebatch";
        } else if (btn.equals("일괄삭제")) {
            iService.deleteItemBatch(no);
        }
        return "redirect:/seller/home";
    }

    @PostMapping(value = "/updateitembatch")
    public String updateItemBatchPost(
            @RequestParam(name = "iname") String[] iname,
            @RequestParam(name = "icode") Long[] icode,
            @RequestParam(name = "icontent") String[] icontent,
            @RequestParam(name = "iprice") Long[] iprice,
            @RequestParam(name = "iquantity") Long[] iquantity) {

        List<ItemEntity> list = new ArrayList<>();
        for (int i = 0; i < iname.length; i++) {
            ItemEntity item = new ItemEntity();
            item.setIcode(icode[i]);
            item.setIname(iname[i]);
            item.setIcontent(icontent[i]);
            item.setIprice(iprice[i]);
            item.setIquantity(iquantity[i]);

            list.add(item);
        }

        iService.updateItemBatch(list);
        return "redirect:/seller/home";
    }

    @GetMapping(value = "/insertbatch")
    public String insertBatchGet() {
        return "seller/insertbatch";
    }

    @PostMapping(value = "/insertitembatch")
    public String insertBatchPOST(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "iname") String[] iname,
            @RequestParam(name = "icontent") String[] icontent,
            @RequestParam(name = "iprice") Long[] iprice,
            @RequestParam(name = "iquantity") Long[] iquantity,
            @RequestParam(name = "timage") MultipartFile[] iimage)
            throws IOException {

        List<ItemEntity> list = new ArrayList<>();
        for (int i = 0; i < iname.length; i++) {
            System.out.println(iname[i]);
            System.out.println(icontent[i]);
            System.out.println(iprice[i]);
            System.out.println(iquantity[i]);
            System.out.println(iimage[i].getOriginalFilename());

            ItemEntity item = new ItemEntity();
            item.setIname(iname[i]);
            item.setIcontent(icontent[i]);
            item.setIprice(iprice[i]);
            item.setIquantity(iquantity[i]);

            item.setIimage(iimage[i].getBytes());
            item.setIimagename(iimage[i].getOriginalFilename());
            item.setIimagesize(iimage[i].getSize());
            item.setIimagetype(iimage[i].getContentType());

            MemberEntity member = new MemberEntity();
            member.setUemail(user.getUsername());
            item.setMember(member);

            list.add(item);
        }

        iService.insertItemBatch(list);

        return "redirect:/seller/home";
    }

    @GetMapping(value = { "/", "/home" })
    public String sellerGET(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "txt", defaultValue = "") String txt, Model model,
            @AuthenticationPrincipal User user) {
        if (user != null) {
            List<ItemDTO> list = iMapper.selectItemList(user.getUsername(), txt, page * PAGECNT - (PAGECNT - 1),
                    page * PAGECNT);
            model.addAttribute("list", list);

            long cnt = iMapper.selectItemCount(txt, user.getUsername());
            model.addAttribute("pages", (cnt - 1) / PAGECNT + 1);

            List<Long> list1 = new ArrayList<>();
            for (ItemDTO item : list) {
                list1.add(item.getIcode());
            }
            List<BuyProjection> list2 = buyRepository.findByItem_icodeIn(list1);
            model.addAttribute("list2", list2);

            // List<Long> sellerItem = buyRepository.findByItem_icodeIn(itemCode);
            return "/seller/home";
        }
        return "redirect:/member/login";
    }

    @GetMapping(value = "/insertitem")
    public String insertItemGET() {
        return "/seller/insertitem";
    }

    @PostMapping(value = "/insertitem")
    public String insertItemPOST(@AuthenticationPrincipal User user, @ModelAttribute ItemDTO item,
            @RequestParam(name = "timage") MultipartFile file) throws IOException {

        System.out.println(item.toString());
        System.out.println(file.getOriginalFilename());

        if (user != null) {
            item.setIimage(file.getBytes());
            item.setIimagename(file.getOriginalFilename());
            item.setIimagesize(file.getSize());
            item.setIimagetype(file.getContentType());

            item.setUemail(user.getUsername());
            iMapper.insertItemOne(item);

            return "redirect:/seller/home";
        }

        return "redirect:/member/login";
    }

    @PostMapping(value = "/deleteitem")
    public String deleteItemPOST(@AuthenticationPrincipal User user, @RequestParam(name = "code") long code) {
        if (user != null) {
            int ret = iMapper.deleteItemOne(code, user.getUsername());
            if (ret == 1) {
                return "redirect:/seller/home";
            }
            return "redirect:/seller/home";
        }
        return "redirect:/member/login";
    }

    @GetMapping(value = "/updateitem")
    public String updateItemGET(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam(name = "code") long code) {
        if (user != null) { // 로그인 되었을때
            ItemDTO item = iMapper.selectItemOne(code);
            model.addAttribute("item", item);
            return "seller/updateitem";
        }
        return "redirect:/member/login";
    }

    @PostMapping(value = "/updateitem")
    public String updateItemPOST(Model model, @AuthenticationPrincipal User user,
            @RequestParam(name = "timage") MultipartFile file, @ModelAttribute ItemDTO item) throws IOException {
        if (user != null) {
            if (!file.isEmpty()) {
                item.setIimage(file.getBytes());
                item.setIimagename(file.getOriginalFilename());
                item.setIimagesize(file.getSize());
                item.setIimagetype(file.getContentType());

            }
            item.setUemail(user.getUsername());
            int ret = iMapper.updateItemOne(item);
            if (ret == 1) {
                model.addAttribute("msg", "물품변경완료");
                model.addAttribute("url", "/ROOT/seller/home");

                return "alert";
            } else {
                model.addAttribute("msg", "물품변경 실패");
                model.addAttribute("url", "/ROOT/seller/home");

                return "alert";
            }
        }
        return "redirect:/member/login";
    }

}
