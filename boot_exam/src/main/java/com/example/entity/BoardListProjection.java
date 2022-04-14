package com.example.entity;

import java.util.Date;

public interface BoardListProjection {

    Long getNo();

    String getTitle();

    String getWriter();

    Long getHit();

    Date getRegdate();
}
