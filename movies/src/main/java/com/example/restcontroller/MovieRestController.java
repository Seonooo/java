package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.MovieEntity;
import com.example.repository.MovieRepository;
import com.example.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            // size 지정하기
            List<MovieEntity> movies = movieService.selectMovies(page, size);
            if (movies != null) {
                map.put("status", 200);
                map.put("movies", movies);
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

}
