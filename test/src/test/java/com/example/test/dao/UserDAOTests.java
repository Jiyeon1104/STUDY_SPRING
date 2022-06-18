package com.example.test.dao;

import com.example.test.domain.dao.UserDAO;
import com.example.test.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserDAOTests {
    @Autowired
    private UserDAO userDAO;

//    @Test
//    public void registerTest(){
//        UserVO userVO = new UserVO();
//        userVO.setUserNumber(1);
//        userVO.setUserId("pinkprincess1");
//        userVO.setUserPw("2222");
//        userVO.setUserName("지연");
//        userVO.setUserAge(25);
//
//        log.info("register : " + userVO);
//        userDAO.register(userVO);
//    }

//    @Test
//    public void readTest(){
//        log.info(userDAO.read(2).toString());
//    }

//    @Test
//    public void modifyTest(){
//        UserVO userVO = new UserVO();
//        userVO.setUserNumber(2);
//        userVO.setUserName("핑공");
//        log.info("modify : " + userVO);
//        userDAO.modify(userVO);
//    }

    @Test
    public void removeTest(){
        log.info("DELETE: " + userDAO.remove(2));
    }
}
