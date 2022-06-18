package com.example.board.service;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardServiceTests {
    @Autowired
    private BoardService boardService;

//    @Test
//    public void getListTest(){
//        boardService.getList(new Criteria()).stream().map(BoardVO::toString).forEach(log::info);
//    }

//    @Test
//    public void registerTest(){
//        BoardVO boardVO = new BoardVO();
//        boardVO.setBoardTitle("새로운 게시글 제목3");
//        boardVO.setBoardContent("새로운 게시글 내용3");
//        boardVO.setBoardWriter("hds2222");
//
//        boardService.register(boardVO);
//
//        log.info("게시글 번호 : " + boardVO.getBoardBno());
//    }

//    @Test
//    public void readTest(){
//        Long boardBno = 3L;
//        log.info(boardService.read(boardBno).toString());
//    }

//    @Test
//    public void removeTest(){
//        Long boardBno = 6L;
//        if(boardService.remove(boardBno)){
//            log.info("DELETE SUCCESS");
//            return;
//        }
//
//        log.info("DELETE FAIL");
//    }

//    @Test
//    public void modifyTest(){
//        Long boardBno = 3L;
//        BoardVO boardVO = boardService.read(boardBno);
//        if(boardVO == null) { log.info("NO BOARD"); return;}
//
//        boardVO.setBoardTitle("수정된 게시글 제목3");
//        boardVO.setBoardContent("수정된 게시글 내용3");
//        boardVO.setBoardWriter("한동석");
//
//        if(boardService.modify(boardVO)){
//            log.info("UPDATE SUCCESS");
//        }else{
//            log.info("UPDATE FAIL");
//        }
//    }

    @Test
    public void getTotalTest(){
        log.info("총 개수 : " + boardService.getTotal());
    }
}
