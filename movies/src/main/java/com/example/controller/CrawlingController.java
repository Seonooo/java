package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
                String naverHref = naverRankList.get(i).attr("href");
                String naverCode = naverHref.substring(naverHref.lastIndexOf("=") + 1);
                System.out.println(naverCode);

                // 가져온 코드를 통해서 영화한개의 정보가 들어있는 페이지가져오기
                String naverRankUrlOne = "https://movie.naver.com/movie/bi/mi/basic.naver?code=" + naverCode;
                Document naverDocOne = Jsoup.connect(naverRankUrlOne).get();

                // 포스터
                // Elements

                // 영화제목
                Elements movieTitle = naverDocOne.select("h3.h_movie a");
                System.out.println(movieTitle.first().text());

                // 기자 평론가 평점
                Elements movieGpa = naverDocOne.select("div.star_score em");
                String gapsplit[] = movieGpa.text().substring(8, 15).split(" ");
                String gap = "";
                for (String gap2 : gapsplit) {
                    gap += gap2;
                }
                Float l = Float.parseFloat(gap);
                gapList.add(l);
                System.out.println(gapList.get(i));

                // 배우 3명
                String movieActorUrl = "https://movie.naver.com/movie/bi/mi/detail.naver?code=" + naverCode;
                Document naverActorDoc = Jsoup.connect(movieActorUrl).get();
                Elements movieActor = naverActorDoc.select("a.k_name");
                String movieActors = movieActor.get(0).text() + movieActor.get(1).text() + movieActor.get(2).text();
                System.out.println(movieActors);

                // 감독 1명
                String movieDirectorUrl = "https://movie.naver.com/movie/bi/mi/detail.naver?code=" + naverCode;
                Document movieDirectorDoc = Jsoup.connect(movieDirectorUrl).get();
                Elements movieDiretor = movieDirectorDoc.select("div.dir_product a");
                String movieDirector = movieDiretor.first().text();
                System.out.println(movieDirector);

                // 랭킹
                int rank = i + 1;
                System.out.println(rank);

                // 상영시간
                Elements movieTime = naverDocOne.select("dl.info_spec span");
                int movieTimeLength = movieTime.text().indexOf("분");
                String movieTimes = movieTime.text().substring(movieTimeLength - 3,
                        movieTimeLength);

                System.out.println(movieTimes);

                // 개봉일
                Elements movieRelease = naverDocOne.select("dl.info_spec span");
                int movieReleaseLength = movieRelease.text().indexOf("개봉");
                System.out.println(
                        movieRelease.text().substring(movieReleaseLength - 12,
                                movieReleaseLength));

                // 짧은 줄거리
                Elements movieShot = naverDocOne.select("h5.h_tx_story ");
                System.out.println(movieShot.text());

                // 긴 줄거리
                Elements movieLong = naverDocOne.select("p.con_tx");
                System.out.println(movieLong.first().text());

                System.out.println("----------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
