package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Board {
    private long no = 0L;
    private String title = null;
    private String content = null;
    private String writer = null;
    private int hit = 1;

}
