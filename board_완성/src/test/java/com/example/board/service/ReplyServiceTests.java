package com.example.board.service;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyServiceTests {
    @Autowired
    private ReplyService replyService;

    private Long[] arBno = {2098603L, 2098602L, 2098601L, 2098600L, 2098599L};

    @Test
    public void replyServiceTest(){
        log.info(replyService + "");
    }

//    @Test
//    public void registerTest(){
//        // 최근 5개의 게시글에 2개씩 댓글 달기
//        IntStream.rangeClosed(1, 10).forEach(i -> {
//            ReplyVO replyVO = new ReplyVO();
//            replyVO.setBoardBno(arBno[i % 5]);
//            replyVO.setReplyContent("새롭게 추가된 댓글" + i);
//            replyVO.setReplyWriter("user" + i);
//            replyService.register(replyVO);
//        });
//    }

//    @Test
//    public void readTest(){
//        log.info(replyService.read(30L).toString());
//    }

//    @Test
//    public void removeTest(){
//        log.info("DELETE : " + replyService.remove(30L));
//    }

//    @Test
//    public void modifyTest(){
//        ReplyVO replyVO = new ReplyVO();
//        replyVO.setReplyNumber(71L);
//        replyVO.setReplyContent("수정된 댓글");
//        log.info("UPDATE : " + replyService.modify(replyVO));
//    }

//    @Test
//    public void getListTest(){
//        replyService.getList(new Criteria(2, 10), 2098603L)
//                .stream().map(ReplyVO::toString).forEach(log::info);
//    }

    @Test
    public void getTotalTest(){
        log.info("댓글 개수 : " + replyService.getTotal(2098603L));
    }
}
