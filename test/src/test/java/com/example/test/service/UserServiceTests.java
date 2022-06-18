package com.example.test.service;

import com.example.test.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserServiceTests {
    @Autowired
    private UserService userService;

    // 유저 추가
//    @Test
//    public void registerTest(){
//        UserVO userVO = new UserVO();
//        userVO.setUserNumber(3);
//        userVO.setUserName("조은솔");
//        userVO.setUserId("eunsol0607");
//        userVO.setUserPw("1111");
//        userVO.setUserAge(22);
//
//        log.info("register : " + userVO);
//        userService.register(userVO);
//    }

//    @Test
//    public void readTest(){
//        log.info(userService.read(3).toString());
//    }

//    @Test
//    public void modifyTest(){
//        UserVO userVO = new UserVO();
//        userVO.setUserName("덤보");
//        userVO.setUserNumber(3);
//        userService.modify(userVO);
//    }

    @Test
    public void removeTest(){
        log.info("DELETE: " + userService.remove(3));
    }
}
