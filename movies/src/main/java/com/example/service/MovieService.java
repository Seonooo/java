package com.example.service;

import java.util.List;

import com.example.entity.MovieCategoryEntity;
import com.example.entity.MovieEntity;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    // 영화 크롤링
    public int insertMovies(String naverRankUrl);

    // 영화 한개 등록
    public int insertMovie(MovieEntity movie, String nation, Long filmRating, Long gpa, Long msCode);

    // 영화 좋아요수 업데이트
    public int updateHit(Long code);

    // 영화 업데이트(마감일)
    public int updateMovie(MovieEntity movie, Long msCode);

    // 영화 한개 삭제
    public int deleteMovie(Long code);

    // 영화 전체 삭제
    public int deleteMovies();

    // 영화 한개 상세페이지
    public MovieEntity selectMovie(Long code);

    // 영화리스트(한 페이지)
    public Page<MovieEntity> selectMovies(Integer page, Integer size);

    // 영화장르별 장르 - 영화 조인
    public Page<MovieCategoryEntity> selectMovieGenre(Integer page, Integer size, Long mcCode);

    // 영화 상태별(상영여부)
    public Page<MovieEntity> selectMovieState(Integer page, Integer size, Long msCode);

    // 영화 제목별
    public Page<MovieEntity> selectMovieTitle(Integer page, Integer size, String title);

    //
    // public List<MovieEntity> selectMoviesTitle(String title);

    // 영화 포스터 등록
    public int insertMoviePoster();

    // 영화 장르 크롤링
    public int insertGenre();

}