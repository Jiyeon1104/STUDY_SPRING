package com.example.board.domain.dao;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardDaoTests {
    @Autowired
    private BoardDAO boardDAO;

//    @Test
//    public void getListTest(){
//        boardDAO.getList(new Criteria()).stream().map(BoardVO::toString).forEach(log::info);
//    }

//    @Test
//    public void registerTest(){
//        BoardVO boardVO = new BoardVO();
//        boardVO.setBoardTitle("새로운 게시글 제목3");
//        boardVO.setBoardContent("새로운 게시글 내용3");
//        boardVO.setBoardWriter("hds2222");
//
//        boardDAO.register(boardVO);
//
//        log.info("게시글 번호 : " + boardVO.getBoardBno());
//    }

//    @Test
//    public void readTest(){
//        Long boardBno = 3L;
//        log.info(boardDAO.read(boardBno).toString());
//    }

//    @Test
//    public void removeTest(){
//        Long boardBno = 5L;
//        if(boardDAO.remove(boardBno)){
//            log.info("DELETE SUCCESS");
//            return;
//        }
//
//        log.info("DELETE FAIL");
//    }

//    @Test
//    public void modifyTest(){
//        Long boardBno = 3L;
//        BoardVO boardVO = boardDAO.read(boardBno);
//        if(boardVO == null) { log.info("NO BOARD"); return;}
//
//        boardVO.setBoardTitle("수정된 게시글 제목2");
//        boardVO.setBoardContent("수정된 게시글 내용2");
//        boardVO.setBoardWriter("한동석");
//
//        if(boardDAO.modify(boardVO)){
//            log.info("UPDATE SUCCESS");
//        }else{
//            log.info("UPDATE FAIL");
//        }
//    }

    @Test
    public void getTotalTest(){
        log.info("총 개수 : " + boardDAO.getTotal());
    }

}
