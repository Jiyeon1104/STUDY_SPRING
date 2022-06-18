package com.example.board.domain.dao;

import com.example.board.domain.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileDaoTests {

    @Autowired
    private FileDAO fileDAO;

//    @Test
//    public void insertTest(){
//        FileVO fileVO = new FileVO();
//        fileVO.setFileName("day03.txt");
//        fileVO.setUuid("3");
//        fileVO.setUploadPath("2022/06/10");
//        fileVO.setImage(false);
//        fileVO.setBoardBno(2098603L);
//
//        fileDAO.register(fileVO);
//    }

//    @Test
//    public void deleteTest(){
//        fileDAO.remove("3");
//    }

//    @Test
//    public void findByBoardBnoTest(){
//        fileDAO.findByBoardBno(2098603L).stream().map(FileVO::toString).forEach(log::info);
//    }
}
