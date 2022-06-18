// DB를 사용해 데이터를 조회하거나 조작하는 기능을 전담하도록 만든 객체
// DB에 접근하는 객체
package com.example.ex02.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@Data
public class WorkVO {
    private String name;
    private String checkInfo;
}
