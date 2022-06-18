package com.example.test.service;

import com.example.test.domain.dao.UserDAO;
import com.example.test.domain.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserDAO userDAO;

    // 유저 추가
    public void register(UserVO userVO){
        log.info("register: " + userVO);
        userDAO.register(userVO);
    }

    // 유저 한 명 보기
    public UserVO read(int userNumber){
        log.info("read : " + userNumber);
        return userDAO.read(userNumber);
    }

    // 유저 회원정보 수정
    public boolean modify(UserVO userVO){
        log.info("modify" + userVO);
        return userDAO.modify(userVO);
    }

    // 유저 탈퇴
    public boolean remove(int userNumber){
        log.info("delete : " + userNumber);
        return userDAO.remove(userNumber);
    }

}
