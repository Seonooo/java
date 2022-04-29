package com.example.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.example.entity.CategoryEntity;
import com.example.entity.FilmRatingEntity;
import com.example.entity.MovieCategoryEntity;
import com.example.entity.MovieEntity;
import com.example.entity.NationEntity;
import com.example.entity.PosterEntity;
import com.example.repository.CategoryRepository;
import com.example.repository.FilmRatingRepository;
import com.example.repository.MovieCategoryRepository;
import com.example.repository.MovieRepository;
import com.example.repository.NationRepository;
import com.example.repository.PosterRepository;
import com.example.service.MovieService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    PosterRepository posterRepository;

    @Autowired
    FilmRatingRepository filmRatingRepository;

    @Autowired
    NationRepository nationRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MovieCategoryRepository movieCategoryRepository;

    // 크롤링으로 전체추가
    @Override
    public int insertMovies(String naverRankUrl) {
        try {
            // 사이트 전체소스를 jsoup로 naverDoc에 가져옴
            Document naverDoc = Jsoup.connect(naverRankUrl).get();

            // naverRankList에 naverDoc중에서 dt태그의 클래스명이 tit3인것에서 a태그만 가져오기
            Elements naverRankList = naverDoc.select("dt.tit a");

            // 평점리스트
            List<Float> gapList = new ArrayList<>();

            // for문을 통해서 네이버영화 코드를 가져옴
            for (int i = 0; i < 5; i++) {

                // 영화Entity 생성
                MovieEntity mEntity = new MovieEntity();

                // 포스터Entity 생성 -> 여기서는 주소만
                PosterEntity posterEntity = new PosterEntity();

                String naverHref = naverRankList.get(i).attr("href");
                String naverCode = naverHref.substring(naverHref.lastIndexOf("=") + 1);
                // 영화코드
                Long movieCode = Long.parseLong(naverCode);
                mEntity.setMCode(movieCode);

                System.out.println("영화코드 : " + naverCode);

                // 가져온 코드를 통해서 영화한개의 정보가 들어있는 페이지가져오기
                String naverRankUrlOne = "https://movie.naver.com/movie/bi/mi/basic.naver?code=" + naverCode;
                Document naverDocOne = Jsoup.connect(naverRankUrlOne).get();

                // 포스터
                // Elements
                Element moviePoster = naverDocOne.select("div.poster img").last();
                String moviePosterUrl = moviePoster.absUrl("src");
                System.out.println("포스터주소 : " + moviePosterUrl);
                posterEntity.setPImageUrl(moviePosterUrl);

                // 영화제목
                Elements movieTitle = naverDocOne.select("h3.h_movie a");
                System.out.println("제목 : " + movieTitle.first().text());
                mEntity.setMTitle(movieTitle.first().text());

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
                mEntity.setMGpa(gapList.get(i));

                // 배우 3명
                String movieActorUrl = "https://movie.naver.com/movie/bi/mi/detail.naver?code=" + naverCode;
                Document naverActorDoc = Jsoup.connect(movieActorUrl).get();
                Elements movieActor = naverActorDoc.select("a.k_name");
                String movieActorsList = new String();
                for (int o = 0; o < 3; o++) {
                    String movieActors = movieActor.get(o).text();
                    if (o < 2) {
                        movieActorsList += movieActors + ",";
                    } else {
                        movieActorsList += movieActors;
                    }

                }
                System.out.println("배우3명 : " + movieActorsList.toString());
                mEntity.setMActor(movieActorsList.toString());

                // 감독 1명
                String movieDirectorUrl = "https://movie.naver.com/movie/bi/mi/detail.naver?code=" + naverCode;
                Document movieDirectorDoc = Jsoup.connect(movieDirectorUrl).get();
                Elements movieDiretor = movieDirectorDoc.select("div.dir_product a");
                String movieDirector = movieDiretor.first().text();
                System.out.println("감독 1명 : " + movieDirector);
                mEntity.setMDirector(movieDirector);

                // 랭킹
                int rank = i + 1;
                System.out.println("순위 : " + rank);
                mEntity.setMRank(Long.valueOf(rank));

                // 상영시간
                Elements movieTime = naverDocOne.select("dl.info_spec span");
                int movieTimeLength = movieTime.text().indexOf("분");
                String movieTimes = movieTime.text().substring(movieTimeLength - 3,
                        movieTimeLength);

                System.out.println("상영시간 : " + movieTimes);
                mEntity.setMTime(movieTimes);

                // 개봉일, 마감일
                Elements movieRelease = naverDocOne.select("dl.info_spec span");
                int movieReleaseLength = movieRelease.text().indexOf("개봉");
                String movieOneRelease = movieRelease.text().substring(movieReleaseLength - 12,
                        movieReleaseLength);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy .MM.dd ", Locale.KOREA);
                Calendar cal = Calendar.getInstance();

                // 개봉일
                cal.setTime(formatter.parse(movieOneRelease));
                mEntity.setMRelease(cal.getTime());
                System.out.println("개봉일 : =========>" + cal.getTime().toString());

                // 마감일
                cal.add(Calendar.MONTH, 2);
                mEntity.setMDeadline(cal.getTime());
                System.out.println("마감일 : =========>" + cal.getTime().toString());

                // 짧은 줄거리
                Elements movieShot = naverDocOne.select("h5.h_tx_story ");
                System.out.println("짧은 줄거리 : " + movieShot.text());
                mEntity.setMLong(movieShot.text());

                // 긴 줄거리
                Elements movieLong = naverDocOne.select("p.con_tx");
                System.out.println("긴줄거리 : " + movieLong.first().text());
                mEntity.setMShot(movieLong.text());

                // 등급코드
                String age = naverDocOne.select("p.info_spec span:nth-child(5) a").attr("href");
                String ageCode = age.substring(age.lastIndexOf("=") + 1);
                System.out.println(ageCode);
                FilmRatingEntity filmRating = filmRatingRepository.findById(Long.parseLong(ageCode)).orElse(null);
                mEntity.setFilmratingEntity(filmRating);

                // 국가코드
                String nation = naverDocOne.select("p.info_spec span:nth-child(2) a").attr("href");
                String nationCode = nation.substring(nation.lastIndexOf("=") + 1);
                System.out.println(nationCode);
                NationEntity nationEntity = nationRepository.findById(nationCode).orElse(null);
                mEntity.setNationEntity(nationEntity);

                List<Long> genreList = new ArrayList<>();
                // 장르코드 -> 최대 3개가져오기
                // 장르코드 url
                for (int d = 0; d < 3; d++) {
                    String temp = naverDocOne.select("p.info_spec span:nth-child(1) a:nth-child(" + (d + 1) + ")")
                            .attr("href");
                    String tempCode = temp.substring(temp.lastIndexOf("=") + 1);
                    System.out.println("tempCode ====>" + tempCode + "<======");
                    if (!tempCode.equals("")) {
                        genreList.add(Long.parseLong(tempCode));
                        System.out.println(Long.parseLong(tempCode));
                    }

                }

                // 기본 좋아요수
                mEntity.setMLike(0L);

                // PosterEntity에 movieEntity Join
                posterEntity.setMovieEntity(mEntity);

                // poster, movie, movieGenre save
                movieRepository.save(mEntity);
                posterRepository.save(posterEntity);

                System.out.println(genreList.size());
                for (int g = 0; g < genreList.size(); g++) {
                    MovieCategoryEntity mcEntity = new MovieCategoryEntity();
                    CategoryEntity category = new CategoryEntity();
                    System.out.println(genreList.get(g));
                    category.setCCode(genreList.get(g));
                    mcEntity.setCategoryEntity(category);
                    mcEntity.setMovieEntity(mEntity);
                    movieCategoryRepository.save(mcEntity);
                    System.out.println(mcEntity);
                }

                System.out.println("----------------------------------");
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int insertMovie(MovieEntity movie) {
        try {
            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setMCode(movie.getMCode());
            movieEntity.setMActor(movie.getMActor());
            movieEntity.setMDirector(movie.getMDirector());
            movieEntity.setMGpa(movie.getMGpa());
            movieEntity.setMLike(0L);
            movieEntity.setMTime(movie.getMTime());
            movieEntity.setMTitle(movie.getMTitle());
            movieEntity.setMShot(movie.getMShot());
            movieEntity.setMLong(movie.getMLong());
            movieEntity.setMRelease(movie.getMRelease());
            movieEntity.setMDeadline(movie.getMDeadline());
            movieEntity.setMRank(movie.getMRank());

            movieEntity.setNationEntity(movie.getNationEntity());
            movieEntity.setFilmratingEntity(movie.getFilmratingEntity());
            movieEntity.setGpaEntity(movie.getGpaEntity());

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateMovie(MovieEntity movie) {
        try {
            MovieEntity movieEntity = movieRepository.findById(movie.getMCode());
            movieEntity.setMLike(movie.getMLike() + 1);
            movieRepository.save(movieEntity);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteMovies() {
        try {
            posterRepository.deleteAll();
            movieCategoryRepository.deleteAll();
            movieRepository.deleteAll();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
