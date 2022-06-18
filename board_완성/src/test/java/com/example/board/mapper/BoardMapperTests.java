package com.example.board.mapper;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardMapperTests {
    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void getListTest(){
        boardMapper.getList(new Criteria(2, 10)).stream().map(BoardVO::toString).forEach(log::info);
    }

//    @Test
//    public void insertTest(){
//        BoardVO boardVO = new BoardVO();
//        boardVO.setBoardTitle("새로운 게시글 제목2");
//        boardVO.setBoardContent("새로운 게시글 내용2");
//        boardVO.setBoardWriter("hds1111");
//
//        boardMapper.insert(boardVO);
//
//        log.info("게시글 번호 : " + boardVO.getBoardBno());
//    }

//    @Test
//    public void getTest(){
//        Long boardBno = 3L;
//        log.info(boardMapper.get(boardBno).toString());
//    }

//    @Test
//    public void deleteTest(){
//        Long boardBno = 2098604L;
//        log.info("DELETE COUNT : " + boardMapper.delete(boardBno));
//    }

//    @Test
//    public void updateTest(){
//        Long boardBno = 10L;
//        BoardVO boardVO = boardMapper.get(boardBno);
//        if(boardVO == null) { log.info("NO BOARD"); return;}
//
//        boardVO.setBoardTitle("수정된 게시글 제목");
//        boardVO.setBoardContent("수정된 게시글 내용");
//        boardVO.setBoardWriter("한동석");
//
//        log.info("UPDATE COUNT : " + boardMapper.update(boardVO));
//    }

//    @Test
//    public void getTotalTest(){
//        log.info("총 개수 : " + boardMapper.getTotal());
//    }
}














