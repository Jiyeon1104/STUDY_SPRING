package com.example.ex02.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TaskVO {
    private int num;
    private int kor;
    private int eng;
    private int math;

    public int getTotal(){
        return kor + eng + math;
    }

    public double getAvg(){
        return getTotal()/3;
    }
}
