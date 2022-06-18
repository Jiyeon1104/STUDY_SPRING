package com.example.ex02.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper // 마이바티스 연결 등록(xml id 매핑)
public interface TimeMapper {
//    @Select("SELECT SYSDATE FROM DUAL")
    public String getTime();
}
