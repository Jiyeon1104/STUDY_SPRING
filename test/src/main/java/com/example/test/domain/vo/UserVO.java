package com.example.test.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserVO {
    private int userNumber;
    private String userId;
    private String userPw;
    private String userName;
    private int userAge;
}
