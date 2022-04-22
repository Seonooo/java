package com.example.schedule;

import java.io.IOException;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.repository.ProductCountRepository;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
public class MySchedule {

    @Autowired
    ProductCountRepository pcRepository;

    // @Scheduled(cron = "*/10 * * * * *" )
    public void printData() {
        Date date = new Date();
        System.out.println(date.toString());
    }

    // @Scheduled(cron = "*/10 * * * * *")
    public void printData1() throws IOException {
        final String URL = "https://movie.naver.com/movie/sdb/rank/rmovie.naver";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL).build();

        Response response = client.newCall(request).execute();
        String msg = response.body().string();
        // {"ret":"y", "data":"123"}
        // [{"ret":"y", "data":"123"},{"ret":"y", "data":"123"}]
        System.out.println(msg);

        JSONObject jobj = new JSONObject(msg);
        System.out.println(jobj);

        // ProductCountEntity p =new ProductCountEntity();
        // pcRepository.save(p);
    }

}
