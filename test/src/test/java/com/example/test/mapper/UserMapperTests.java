package com.example.test.mapper;

import com.example.test.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

//    @Test
//    public void insertTest(){
//        UserVO userVO = new UserVO();
//        userVO.setUserNumber(1);
//        userVO.setUserId("jiyeon1104");
//        userVO.setUserPw("1111");
//        userVO.setUserName("김지연");
//        userVO.setUserAge(24);
//        userMapper.insert(userVO);
//
//        log.info("insert" + userVO);
//    }

//    @Test
//    public void readUserTest(){
//        int userNumber = 1;
//        UserVO userVO = userMapper.readUser(userNumber);
//        log.info(userMapper.readUser(userNumber).toString());
//    }

//    @Test
//    public void updateUserTest(){
//        UserVO userVO = new UserVO();
//        userVO.setUserNumber(1);
//        userVO.setUserName("핑공");
//        log.info("UPDATE COUNT: " + userMapper.update(userVO));
//    }

//    @Test
//    public void deleteTest(){
//        int userNumber = 1;
//        log.info("DELETE COUNT: " + userMapper.delete(1));
//    }
}
