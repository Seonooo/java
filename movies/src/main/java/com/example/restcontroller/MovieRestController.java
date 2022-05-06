package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.MovieCategoryEntity;
import com.example.entity.MovieEntity;
import com.example.entity.PosterEntity;
import com.example.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/movie")
public class MovieRestController {

    @Autowired
    MovieService movieService;

    // 영화 한개 등록
    // http://127.0.0.1:9090/ROOT/api/movie/insertOne
    @RequestMapping(value = "/insertOne", method = { RequestMethod.POST }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> insertMovie(
            @RequestBody MovieEntity movie, @RequestParam(name = "ncode") String ncode,
            @RequestParam(name = "fcode") Long fcode, @RequestParam(name = "gcode") Long gcode,
            @RequestParam(name = "mscode") Long mscode) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            if (gcode == null) {
                gcode = 0L;
            }
            int ret = movieService.insertMovie(movie, ncode, fcode, gcode, mscode);
            if (ret == 1) {
                map.put("status", 200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 영화 한개 삭제
    // http://127.0.0.1:9090/ROOT/api/movie/deleteOne
    @RequestMapping(value = "/deleteOne", method = { RequestMethod.DELETE }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> deleteMovie(
            @RequestParam(name = "mcode") Long mcode) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            int ret = movieService.deleteMovie(mcode);
            if (ret == 1) {
                map.put("status", 200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 영화 한개 수정
    // http://127.0.0.1:9090/ROOT/api/movie/updateOne
    @RequestMapping(value = "/updateOne", method = { RequestMethod.PUT }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> updateMovie(
            @RequestBody MovieEntity movie, @RequestParam(name = "mscode") Long mscode) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            int ret = movieService.updateMovie(movie, mscode);
            if (ret == 1) {
                map.put("status", 200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 영화 한개 상세페이지
    // http://127.0.0.1:9090/ROOT/api/movie/selectOne
    @RequestMapping(value = "/selectOne", method = { RequestMethod.GET }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> selectMovie(
            @RequestParam(name = "mcode") Long mcode) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            MovieEntity movie = movieService.selectMovie(mcode);
            if (movie != null) {
                map.put("status", 200);
                map.put("movie", movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 영화 리스트
    // http://127.0.0.1:9090/ROOT/api/movie/select
    @RequestMapping(value = "/select", method = { RequestMethod.GET }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> selectMovies(
            // page받아오기, 페이지에 영화5개씩
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "1", required = false) int sort,
            @RequestParam(name = "genre", defaultValue = "0", required = false) long genre,
            @RequestParam(name = "mscode", defaultValue = "1", required = false) long mscode,
            @RequestParam(name = "title", defaultValue = "", required = false) String title) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            // genre = 0 : 장르선택x
            // sort == 1 : 장르별
            // sort == 2 : 상태별
            // mscode => 0 : 개봉전, 1 : 상영중, 2 : 상영마감
            if (genre > 0) {
                // 장르별 리스트
                if (sort == 1) {
                    Page<MovieCategoryEntity> movies = movieService.selectMovieGenre(page - 1, size, genre);
                    List<MovieCategoryEntity> genreOfMoives = movies.getContent();
                    int total = movies.getTotalPages();

                    if (movies != null) {
                        map.put("status", 200);
                        map.put("movies", genreOfMoives);
                        map.put("total", total);

                    }
                }
            }
            // size 지정하기
            else if (genre == 0) {
                // 상영 상태별 리스트
                if (sort == 2) {
                    Page<MovieEntity> movies = movieService.selectMovieState(page - 1, size, mscode);
                    List<MovieEntity> stateOfMovies = movies.getContent();
                    int total = movies.getTotalPages();
                    if (movies != null) {
                        map.put("status", 200);
                        map.put("movies", stateOfMovies);
                        map.put("total", total);
                    }
                }
                // 제목 검색리스트
                else if (title.length() >= 1) {
                    Page<MovieEntity> movies = movieService.selectMovieTitle(page - 1, size, title);
                    List<MovieEntity> titleOfMovies = movies.getContent();
                    int total = movies.getTotalPages();
                    if (movies != null) {
                        map.put("status", 200);
                        map.put("movies", titleOfMovies);
                        map.put("total", total);
                    }
                }
                // 전체 리스트
                else {
                    Page<MovieEntity> movies = movieService.selectMovies(page - 1, size);
                    List<MovieEntity> moviesInPage = movies.getContent();
                    int total = movies.getTotalPages();
                    if (moviesInPage != null) {
                        map.put("status", 200);
                        map.put("movies", moviesInPage);
                        map.put("total", total);
                        map.put("pages", (total - 1) / size + 1);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 영화 한개 좋아요수 증가
    // http://127.0.0.1:9090/ROOT/api/movie/updateHit
    @RequestMapping(value = "/updateHit", method = { RequestMethod.PUT }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> updateHitMovie(
            @RequestParam(name = "mcode") Long mcode) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            int ret = movieService.updateHit(mcode);
            if (ret == 1) {
                map.put("status", 200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 영화 포스터 등록하기
    // http://127.0.0.1:9090/ROOT/api/movie/poster
    @RequestMapping(value = "/poster", method = { RequestMethod.POST }, consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public Map<String, Object> insertMoviePosters(@ModelAttribute PosterEntity[] poster,
            @RequestHeader(name = "RTOKEN") String rtoken, @RequestHeader(name = "TOKEN") String token,
            @RequestParam(name = "mcode", required = true) Long mcode,
            @RequestParam(name = "file") MultipartFile[] files) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 권한 확인후 실행
            if (!rtoken.equals("ADMIN") && !token.isEmpty()) {
                int ret = movieService.insertMoviePoster(files, mcode);
                if (ret == 1) {
                    map.put("status", 200);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 대표 이미지 설정하기(Phead값 변경)
    // http://127.0.0.1:9090/ROOT/api/movie/main_poster
    @RequestMapping(value = "/main_poster", method = { RequestMethod.PUT }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> updateMainPoster(@RequestHeader(name = "RTOKEN") String rtoken,
            @RequestHeader(name = "TOKEN") String token, @RequestParam(name = "pcode") Long pcode) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 권한 확인후 실행
            if (!rtoken.equals("ADMIN") && !token.isEmpty()) {
                int ret = movieService.updateMovieMainPoster(pcode);
                if (ret == 1) {
                    map.put("status", 200);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 영화 포스터 이미지 가져오기
    // http://127.0.0.1:9090/ROOT/api/movie/poster
    // @RequestMapping(value = "/main_poster", method = { RequestMethod.GET },
    // consumes = {
    // MediaType.ALL_VALUE }, produces = {
    // MediaType.APPLICATION_JSON_VALUE })
    // public Map<String, Object> moviePosterGet(
    // @RequestParam(name = "mcode") Long mcode, @RequestParam(name = "page")
    // Integer page,
    // @RequestParam(name = "size") Integer size) {
    // Map<String, Object> map = new HashMap<>();
    // try {
    // Page<PosterEntity> postersPage = movieService.selectMoviePosters(mcode, page,
    // size);
    // List<PosterEntity> posters = postersPage.getContent();
    // int pages = postersPage.getTotalPages();
    // for (PosterEntity post : posters) {
    // HttpHeaders headers = new HttpHeaders();
    // if (post.getPimagetype().equals("image/jpeg")) {
    // headers.setContentType(MediaType.IMAGE_JPEG);
    // } else if (post.getPimagetype().equals("image/png")) {
    // headers.setContentType(MediaType.IMAGE_PNG);
    // } else if (post.getPimagetype().equals("image/gif")) {
    // headers.setContentType(MediaType.IMAGE_GIF);
    // } else {

    // }
    // }
    // map.put("status", 200);

    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return map;
    // }

    // 영화 포스터 삭제하기
    @RequestMapping(value = "/main_poster", method = { RequestMethod.GET }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> deleteMoviePoster(
            @RequestParam(name = "mcode") Long mcode, @RequestParam(name = "pcode") Long pcode) {
        Map<String, Object> map = new HashMap<>();
        try {

            map.put("status", 200);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 영화 포스터 변경하기

}
