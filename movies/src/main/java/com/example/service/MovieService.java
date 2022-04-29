package com.example.service;

import com.example.entity.MovieEntity;

import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    // 영화 크롤링
    public int insertMovies(String naverRankUrl);

    // 영화 한개 등록
    public int insertMovie(MovieEntity movie);

    // 영화 좋아요수 업데이트
    public int updateMovie(MovieEntity movie);

    // 영화 여러개 삭제
    public int deleteMovies();
}
