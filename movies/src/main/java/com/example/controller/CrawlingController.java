package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.entity.MovieEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/crawling")
public class CrawlingController {

    public static void main(String[] args) {
        // 크롤링할 사이트 url주소
        String naverRankUrl = "https://movie.naver.com/movie/running/current.naver?order=reserve";
        try {
            // naverDoc에 전체소스 가져옴
            Document naverDoc = Jsoup.connect(naverRankUrl).get();

            // naverRankList에 naverDoc중에서 dt태그의 클래스명이 tit3인것에서 a태그만 가져오기
            Elements naverRankList = naverDoc.select("dt.tit a");

            List<Float> gapList = new ArrayList<>();
            // for문을 통해서 네이버영화 코드를 가져옴
            for (int i = 0; i < 5; i++) {
                MovieEntity mEntity = new MovieEntity();

                String naverHref = naverRankList.get(i).attr("href");
                String naverCode = naverHref.substring(naverHref.lastIndexOf("=") + 1);
                // mEntity.setMCode(Long.parseLong(naverCode));
                System.out.println("영화코드 : " + naverCode);

                // 가져온 코드를 통해서 영화한개의 정보가 들어있는 페이지가져오기
                String naverRankUrlOne = "https://movie.naver.com/movie/bi/mi/basic.naver?code=" + naverCode;
                Document naverDocOne = Jsoup.connect(naverRankUrlOne).get();

                // 포스터
                // Elements
                Element moviePoster = naverDocOne.select("div.poster img").last();
                String moviePosterUrl = moviePoster.absUrl("src");
                System.out.println("포스터주소 : " + moviePosterUrl);

                // 영화제목
                Elements movieTitle = naverDocOne.select("h3.h_movie a");
                System.out.println("제목 : " + movieTitle.first().text());
                // mEntity.setMTitle(movieTitle.first().text());

                // 기자 평론가 평점
                Elements movieGpa = naverDocOne.select("div.star_score em");
                String gapsplit[] = movieGpa.text().substring(8, 15).split(" ");
                String gap = "";
                for (String gap2 : gapsplit) {
                    gap += gap2;
                }
                Float l = Float.parseFloat(gap);
                gapList.add(l);
                System.out.println("평론가 평점 : " + gapList.get(i));
                // mEntity.setMGpa(gapList.get(i));

                // 배우 3명
                String movieActorUrl = "https://movie.naver.com/movie/bi/mi/detail.naver?code=" + naverCode;
                Document naverActorDoc = Jsoup.connect(movieActorUrl).get();
                Elements movieActor = naverActorDoc.select("a.k_name");
                String movieActors = movieActor.get(0).text() + movieActor.get(1).text() + movieActor.get(2).text();
                System.out.println("배우3명 : " + movieActors);

                // 감독 1명
                String movieDirectorUrl = "https://movie.naver.com/movie/bi/mi/detail.naver?code=" + naverCode;
                Document movieDirectorDoc = Jsoup.connect(movieDirectorUrl).get();
                Elements movieDiretor = movieDirectorDoc.select("div.dir_product a");
                String movieDirector = movieDiretor.first().text();
                System.out.println("감독 1명 : " + movieDirector);
                // mEntity.setMDirector(movieDirector);

                // 랭킹
                int rank = i + 1;
                System.out.println("순위 : " + rank);
                // mEntity.setMRank(Long.valueOf(rank));

                // 상영시간
                Elements movieTime = naverDocOne.select("dl.info_spec span");
                int movieTimeLength = movieTime.text().indexOf("분");
                String movieTimes = movieTime.text().substring(movieTimeLength - 3,
                        movieTimeLength);

                System.out.println("상영시간 : " + movieTimes);
                // mEntity.setMTime(movieTimes);

                // 개봉일
                Elements movieRelease = naverDocOne.select("dl.info_spec span");
                int movieReleaseLength = movieRelease.text().indexOf("개봉");
                String movieOneRelease = movieRelease.text().substring(movieReleaseLength - 12,
                        movieReleaseLength);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy .MM.dd ", Locale.KOREA);
                Date date = formatter.parse(movieOneRelease);
                System.out.println("개봉일 : " + date);
                // mEntity.setMRelease(date);

                // 짧은 줄거리
                Elements movieShot = naverDocOne.select("h5.h_tx_story ");
                System.out.println("짧은 줄거리 : " + movieShot.text());
                // cEntity.setCtLong(movieShot.text());

                // 긴 줄거리
                Elements movieLong = naverDocOne.select("p.con_tx");
                System.out.println("긴줄거리 : " + movieLong.first().text());
                // cEntity.setCtShot(movieLong.text());

                System.out.println("----------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
