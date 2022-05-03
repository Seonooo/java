package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.NationEntity;
import com.example.repository.NationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Controller
@RequestMapping(value = "/nation")
public class NationController {
    // 저장소
    @Autowired
    NationRepository nRepository;

    // 국가입력
    @GetMapping(value = { "/insertnation" })
    public String insertnationGET() throws IOException {
        final String URL = "https://mu-star.net/upload/20200511/country.json";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URL).build();
        Response response = client.newCall(request).execute();
        String msg[] = response.body().string().split("\"");

        List<String> msg2 = new ArrayList<>();
        for (int i = 0; i < msg.length; i++) {
            msg2.add(msg[i]);
        }

        List<NationEntity> nationlist = new ArrayList<>();

        for (int i = 0; i < 245; i++) {
            NationEntity nation = new NationEntity();
            nation.setNcode(msg[1 + (i * 24)]);
            nation.setNnation(msg[15 + (i * 24)]);
            nationlist.add(nation);
        }

        // System.out.println(nationlist.toString());

        for (int i = 0; i < nationlist.size(); i++) {
            NationEntity nation = new NationEntity();
            nation.setNcode(nationlist.get(i).getNcode());
            nation.setNnation(nationlist.get(i).getNnation());
            nRepository.save(nation);
        }

        return "redirect:/nation/insert";
    }

    // 국가입력 페이지
    @GetMapping(value = { "/insert" })
    public String insertGET() {

        return "/nation/insert";
    }
}
