package com.example.board.mapper;

import com.example.board.domain.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileMapperTests {

    @Autowired
    private FileMapper fileMapper;

//    @Test
//    public void insertTest(){
//        FileVO fileVO = new FileVO();
//        fileVO.setFileName("day03.txt");
//        fileVO.setUuid("3");
//        fileVO.setUploadPath("2022/06/10");
//        fileVO.setImage(false);
//        fileVO.setBoardBno(2098603L);
//
//        fileMapper.insert(fileVO);
//    }

//    @Test
//    public void deleteTest(){
//        fileMapper.delete("몰라");
//    }

    @Test
    public void findByBoardBnoTest(){
        fileMapper.findByBoardBno(2098603L).stream().map(FileVO::toString).forEach(log::info);
    }
}
