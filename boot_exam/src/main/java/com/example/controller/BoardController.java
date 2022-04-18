package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.entity.Board;
import com.example.entity.BoardListProjection;
import com.example.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    BoardRepository bRepository;

    @Autowired
    ResourceLoader resLoader;

    @PostMapping(value = "/delete")
    public String boardDeletePost(Model model, @RequestParam(name = "bno") Long bno) {

        bRepository.deleteById(bno);
        model.addAttribute("msg", "삭제가 완료되었습니다.");
        model.addAttribute("url", "/board/selectlist");

        return "alert";
    }

    @PostMapping(value = "/update")
    public String boardUpdatePost(@RequestParam(name = "bno") Long bno, @RequestParam(name = "content") String content,
            @RequestParam(name = "title") String title) {
        Board board = bRepository.findById(bno).orElse(null);
        board.setContent(content);
        board.setTitle(title);
        bRepository.save(board);

        return "redirect:/board/selectone?no=" + bno;
    }

    // 글수정 페이지
    @GetMapping(value = "/update")
    public String boardUpdateGet(Model model, @RequestParam(name = "bno") Long bno) {
        Board board = bRepository.findById(bno).orElse(null);
        model.addAttribute("board", board);
        return "/board/update";
    }

    // 글목록페이지
    @GetMapping(value = "/selectlist")
    public String boardGet(Model model, @RequestParam(name = "txt", defaultValue = "") String txt,
            @RequestParam(name = "page", defaultValue = "1") int page) {
        try {
            PageRequest pageRequest = PageRequest.of(page - 1, 15);
            List<Board> list2 = bRepository.findAll();
            List<BoardListProjection> list = bRepository.findByWriterContainingOrderByNoDesc(txt, pageRequest);
            model.addAttribute("list", list);
            // 페이지네이션을 위한 개수 가져오기
            model.addAttribute("pages", (list2.size() - 1) / 15 + 1);
            System.out.println(list2.size());
            return "board/selectlist";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/board/selectlist";
        }
    }

    // 글쓰기페이지
    @GetMapping(value = "/insert")
    public String boardInsertGet() {
        return "/board/insert";
    }

    // 글쓰기 실행
    @PostMapping(value = "/insert")
    public String boardInsertPost(Model model, @ModelAttribute Board board,
            @RequestParam(name = "images") MultipartFile file)
            throws IOException {
        try {
            System.out.println(file);
            if (file != null) {
                board.setImage(file.getBytes());
                board.setImagename(file.getOriginalFilename());
                board.setImagesize(file.getSize());
                board.setImagetype(file.getContentType());
            }

            bRepository.save(board);

            model.addAttribute("msg", "글쓰기가 완료되었습니다.");
            model.addAttribute("url", "/board/selectlist");
            return "alert";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/board/selectlist";
        }

    }

    // 글내용
    @GetMapping(value = "/selectone")
    public String boardSelectoneGet(HttpServletRequest request, Model model, @RequestParam(name = "no") long no) {
        try {
            Board board = bRepository.findById(no).orElse(null);
            board.setImageurl(request.getContextPath() + "/board/image?no=" + no);
            model.addAttribute("board", board);

            // 이전페이지
            BoardListProjection boardPrev = bRepository.findTop1ByNoLessThanOrderByNoDesc(no);
            if (boardPrev != null) {
                System.out.println(boardPrev.toString());
                model.addAttribute("prev", boardPrev.getNo());
            } else {
                model.addAttribute("prev", 0L);
            }

            // 다음페이지
            BoardListProjection boardNext = bRepository.findTop1ByNoGreaterThanOrderByNoAsc(no);
            int max = bRepository.findAll().size();
            if (boardNext != null) {
                System.out.println(boardNext);
                model.addAttribute("next", boardNext.getNo());
                model.addAttribute("max", max);
            } else {
                model.addAttribute("next", board.getNo() + 1L);
                model.addAttribute("max", max);
            }

            return "/board/selectone";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/board/selectlist";
        }
    }

    // 이미지
    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> imageGet(@RequestParam(name = "no") long no) throws IOException {
        Board board = bRepository.findById(no).orElse(null);

        if (board.getImagesize() > 0) {
            HttpHeaders headers = new HttpHeaders();
            if (board.getImagetype().equals("image/jpeg")) {
                headers.setContentType(MediaType.IMAGE_JPEG);
            } else if (board.getImagetype().equals("image/png")) {
                headers.setContentType(MediaType.IMAGE_PNG);
            } else if (board.getImagetype().equals("image/gif")) {
                headers.setContentType(MediaType.IMAGE_GIF);
            }
            ResponseEntity<byte[]> response = new ResponseEntity<>(board.getImage(), headers, HttpStatus.OK);
            return response;
        } else {
            InputStream is = resLoader
                    .getResource("classpath:/static/image/default.jpg")
                    .getInputStream();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            ResponseEntity<byte[]> response = new ResponseEntity<>(is.readAllBytes(), headers, HttpStatus.OK);
            return response;
        }

    }

}
