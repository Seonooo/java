package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.entity.CategoryEntity;
import com.example.repository.CategoryRepository;
import com.example.service.MovieService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping(value = "/crawling")
public class CrawlingController {

    // 크롤링 url
    private final String naverRankUrl = "https://movie.naver.com/movie/running/current.naver?order=reserve";
    private final String naverGenre = "https://movie.naver.com/movie/sdb/browsing/bmovie_genre.naver";

    // public static void main(String[] args) {
    // try {
    // String naverRankUrl =
    // "https://movie.naver.com/movie/running/current.naver?order=reserve";
    // // 사이트 전체소스를 jsoup로 naverDoc에 가져옴
    // Document naverDoc = Jsoup.connect(naverRankUrl).get();

    // // naverRankList에 naverDoc중에서 dt태그의 클래스명이 tit3인것에서 a태그만 가져오기
    // Elements naverRankList = naverDoc.select("dt.tit a");

    // // 평점리스트
    // List<Float> gapList = new ArrayList<>();

    // // for문을 통해서 네이버영화 코드를 가져옴
    // for (int i = 0; i < 3; i++) {

    // // 영화Entity 생성
    // // MovieEntity mEntity = new MovieEntity();

    // // 포스터Entity 생성 -> 여기서는 주소만
    // // PosterEntity posterEntity = new PosterEntity();

    // String naverHref = naverRankList.get(i).attr("href");
    // String naverCode = naverHref.substring(naverHref.lastIndexOf("=") + 1);
    // // mEntity.setMCode(Long.parseLong(naverCode));

    // System.out.println("영화코드 : " + naverCode);

    // // 가져온 코드를 통해서 영화한개의 정보가 들어있는 페이지가져오기
    // String naverRankUrlOne =
    // "https://movie.naver.com/movie/bi/mi/basic.naver?code=" + naverCode;
    // Document naverDocOne = Jsoup.connect(naverRankUrlOne).get();

    // // 포스터
    // // Elements
    // Element moviePoster = naverDocOne.select("div.poster img").last();
    // String moviePosterUrl = moviePoster.absUrl("src");
    // System.out.println("포스터주소 : " + moviePosterUrl);
    // // posterEntity.setPImageUrl(moviePosterUrl);

    // // 영화제목
    // Elements movieTitle = naverDocOne.select("h3.h_movie a");
    // System.out.println("제목 : " + movieTitle.first().text());
    // // mEntity.setMTitle(movieTitle.first().text());

    // // 기자 평론가 평점
    // Elements movieGpa = naverDocOne.select("div.star_score em");
    // System.out.println(movieGpa.text());
    // String gapsplit[] = movieGpa.text().substring(8, 15).split(" ");
    // String gap = "";
    // for (String gap2 : gapsplit) {
    // gap += gap2;
    // }
    // Float l = Float.parseFloat(gap);
    // gapList.add(l);
    // System.out.println("평론가 평점 : " + gapList.get(i));
    // // mEntity.setMGpa(gapList.get(i));

    // // 배우 3명
    // String movieActorUrl =
    // "https://movie.naver.com/movie/bi/mi/detail.naver?code=" + naverCode;
    // Document naverActorDoc = Jsoup.connect(movieActorUrl).get();
    // Elements movieActor = naverActorDoc.select("a.k_name");
    // String movieActorsList = new String();
    // for (int o = 0; o < 3; o++) {
    // String movieActors = movieActor.get(o).text();
    // if (o < 2) {
    // movieActorsList += movieActors + ",";
    // } else {
    // movieActorsList += movieActors;
    // }

    // }
    // System.out.println("배우3명 : " + movieActorsList.toString());
    // // mEntity.setMActor(movieActorsList.toString());

    // // 감독 1명
    // String movieDirectorUrl =
    // "https://movie.naver.com/movie/bi/mi/detail.naver?code=" + naverCode;
    // Document movieDirectorDoc = Jsoup.connect(movieDirectorUrl).get();
    // Elements movieDiretor = movieDirectorDoc.select("div.dir_product a");
    // String movieDirector = movieDiretor.first().text();
    // System.out.println("감독 1명 : " + movieDirector);
    // // mEntity.setMDirector(movieDirector);

    // // 랭킹
    // int rank = i + 1;
    // System.out.println("순위 : " + rank);
    // // mEntity.setMRank(Long.valueOf(rank));

    // // 상영시간
    // Elements movieTime = naverDocOne.select("dl.info_spec span");
    // int movieTimeLength = movieTime.text().indexOf("분");
    // String movieTimes = movieTime.text().substring(movieTimeLength - 3,
    // movieTimeLength);

    // System.out.println("상영시간 : " + movieTimes);
    // // mEntity.setMTime(movieTimes);

    // // 개봉일
    // Elements movieRelease = naverDocOne.select("dl.info_spec span");
    // int movieReleaseLength = movieRelease.text().indexOf("개봉");
    // String movieOneRelease = movieRelease.text().substring(movieReleaseLength -
    // 12, movieReleaseLength);
    // SimpleDateFormat formatter = new SimpleDateFormat("yyyy .MM.dd ",
    // Locale.KOREA);
    // Date date = formatter.parse(movieOneRelease);
    // System.out.println("개봉일 : " + date);
    // // mEntity.setMRelease(date);

    // // 짧은 줄거리
    // Elements movieShot = naverDocOne.select("h5.h_tx_story ");
    // System.out.println("짧은 줄거리 : " + movieShot.text());
    // // mEntity.setMLong(movieShot.text());

    // // 긴 줄거리
    // Elements movieLong = naverDocOne.select("p.con_tx");
    // System.out.println("긴줄거리 : " + movieLong.first().text());
    // // mEntity.setMShot(movieLong.text());

    // // 기본 좋아요수 = 0L
    // // mEntity.setMLike(0L);

    // // PosterEntity에 movieEntity Join
    // // posterEntity.setMovieEntity(mEntity);

    // // poster, movie save
    // // movieRepository.save(mEntity);
    // // posterRepository.save(posterEntity);

    // System.out.println("----------------------------------");

    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }

    // // try {

    // // String naverRankUrlOne =
    // // "https://movie.naver.com/movie/bi/mi/basic.naver?code=190695";
    // // Document naverDocOne = Jsoup.connect(naverRankUrlOne).get();

    // // // // 등급코드
    // // // String age = naverDocOne.select("p.info_spec span:nth-child(5)
    // // // a").attr("href");
    // // // String ageCode = age.substring(age.lastIndexOf("=") + 1);
    // // // System.out.println(ageCode);

    // // // // 국가코드
    // // // String nation = naverDocOne.select("p.info_spec span:nth-child(2)
    // // // a").attr("href");
    // // // String nationCode = nation.substring(nation.lastIndexOf("=") + 1);
    // // // System.out.println(nationCode);

    // // // 장르코드 -> 최대 3개가져오기
    // // // 장르코드 url
    // // String temp1 = naverDocOne.select("p.info_spec
    // // span:nth-child(1)a:nth-child(1)").attr("href");
    // // String temp2 = naverDocOne.select("p.info_spec
    // // span:nth-child(1)a:nth-child(2)").attr("href");
    // // String temp3 = naverDocOne.select("p.info_spec
    // // span:nth-child(1)a:nth-child(3)").attr("href");

    // // // 장르코드 url에서 코드만 가져오기
    // // String tempCode1 = temp1.substring(temp1.lastIndexOf("=") + 1);
    // // String tempCode2 = temp2.substring(temp2.lastIndexOf("=") + 1);
    // // String tempCode3 = temp3.substring(temp3.lastIndexOf("=") + 1);

    // // List<String> genreList = new ArrayList<>();
    // // // 장르코드가 2개일땐 19,1 과 같이
    // // // 장르코드가 3개일땐 19,1,2 와 같이
    // // if (tempCode2.equals("")) {
    // // genreList.add(tempCode1);
    // // } else if (tempCode2.equals("")) {
    // // genreList.add(tempCode1);
    // // genreList.add(tempCode2);
    // // } else {
    // // genreList.add(tempCode1);
    // // genreList.add(tempCode2);
    // // genreList.add(tempCode3);
    // // }

    // // System.out.println(genreList);
    // // }

    // // catch (Exception e) {
    // // e.printStackTrace();
    // // }
    // }

    @Autowired
    MovieService movieService;

    // 영화 조회
    @GetMapping(value = "/insert")
    public String insertMovieGet() {
        return "/crawling/insert";
    }

    @PostMapping(value = "/insert")
    public String insertMoviePost() {
        try {
            // 크롤링할 url 주기
            movieService.insertMovies(naverRankUrl);
            return "redirect:/crawling/insert";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/member/home";
        }
    }

    // 영화 1개등록
    @PostMapping(value = "/insertOne")
    public String insertOneMovie() {
        try {
            return "redirect:/crawling/insert";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/member/home";
        }
    }

    // 영화전체삭제
    @PostMapping(value = "/delete")
    public String deleteMoviesPost() {
        try {
            int ret = movieService.deleteMovies();
            if (ret == 1) {
                return "redirect:/crawling/insert";
            }
            return "redirect:/member/home";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/member/home";
        }
    }

    // 장르 등록
    @PostMapping(value = "/genre")
    public String insertGenrePost() {
        try {
            int ret = movieService.insertGenre();
            if (ret == 1) {
                return "redirect:/crawling/insert";
            }
            return "redirect:/member/home";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/member/home";
        }
    }

}
