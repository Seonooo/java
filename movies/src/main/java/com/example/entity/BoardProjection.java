package com.example.entity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

public interface BoardProjection {

    Long getbNo();

    Long getbType();

    String getbTitle();

    String getbContent();

    Long getbHit();

    Long getbLike();

    Date getbRegdate();

    @Value("#{target.memberEntity.mId}")
    String getMemberEntitymId();

}
