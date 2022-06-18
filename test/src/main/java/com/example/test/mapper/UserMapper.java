package com.example.test.mapper;

import com.example.test.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 유저 추가
    public void insert(UserVO userVO);

    // 유저 한 명 조회
    public UserVO readUser(int userNumber);

    // 유저 삭제
    public int delete(int userNumber);

    // 회원 정보 수정
    public int update(UserVO userVO);

}
