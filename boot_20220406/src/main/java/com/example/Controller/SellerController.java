package com.example.Controller;

import java.io.IOException;
import java.util.List;

import com.example.dto.ItemDTO;
import com.example.mapper.ItemMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    // n개 5개 일때는 1~5, 6~10 10개 1~10, 11~20

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
