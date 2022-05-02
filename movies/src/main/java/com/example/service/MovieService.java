package com.example.service;

import java.util.List;
import com.example.entity.MovieEntity;

import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    // 영화 크롤링
    public int insertMovies(String naverRankUrl);

    // 영화 한개 등록
    public int insertMovie(MovieEntity movie, String nation, Long filmRating, Long gpa, Long mscode);

    // 영화 좋아요수 업데이트
    public int updateHit(Long code);

    // 영화 업데이트(마감일, )
    public int updateMovie(MovieEntity movie, Long mscode);

    // 영화 한개 삭제
    public int deleteMovie(Long code);

    // 영화 전체 삭제
    public int deleteMovies();

    // 영화 한개 상세페이지
    public MovieEntity selectMovie(Long code);

    // 영화리스트(한 페이지)
    public List<MovieEntity> selectMovies(Integer page, Integer size);

    // 영화 전체 리스트
    public List<MovieEntity> selectMovieAllList();

    // 영화장르별 장르 - 영화 조인
    public List<MovieEntity> selectMovieGenre(Integer page, Integer size, Long mcCode);

    // 영화 상태별(상영여부)
    public List<MovieEntity> selectMovieState(Integer page, Integer size, Long mscode);

}