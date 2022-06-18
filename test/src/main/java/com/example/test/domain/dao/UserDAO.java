package com.example.test.domain.dao;

import com.example.test.domain.vo.UserVO;
import com.example.test.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserDAO {
    private final UserMapper userMapper;

    // 유저 추가
    public void register(UserVO userVO){
        log.info("register" + userVO);
        userMapper.insert(userVO);
    }

    // 유저 한 명 조회
    public UserVO read(int userNumber){
        log.info("read" + userNumber);
        return userMapper.readUser(userNumber);
    }

    // 유저 삭제
    public boolean remove(int userNumber){
        log.info("remove" + userNumber);
        return userMapper.delete(userNumber)== 1;
    }

    // 회원정보 수정
    public boolean modify(UserVO userVO){
        log.info("modify" + userVO);
        return userMapper.update(userVO) == 1;
    }

}
