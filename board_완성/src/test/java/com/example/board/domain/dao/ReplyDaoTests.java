package com.example.board.domain.dao;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyDaoTests {
    @Autowired
    private ReplyDAO replyDAO;

    private Long[] arBno = {2098603L, 2098602L, 2098601L, 2098600L, 2098599L};

    @Test
    public void replyDAOTest(){
        log.info(replyDAO + "");
    }

//    @Test
//    public void registerTest(){
//        // 최근 5개의 게시글에 2개씩 댓글 달기
//        IntStream.rangeClosed(1, 10).forEach(i -> {
//            ReplyVO replyVO = new ReplyVO();
//            replyVO.setBoardBno(arBno[i % 5]);
//            replyVO.setReplyContent("새롭게 추가된 댓글" + i);
//            replyVO.setReplyWriter("user" + i);
//            replyDAO.register(replyVO);
//        });
//    }

//    @Test
//    public void readTest(){
//        log.info(replyDAO.read(27L).toString());
//    }

//    @Test
//    public void removeTest(){
//        log.info("DELETE : " + replyDAO.remove(27L));
//    }

//    @Test
//    public void modifyTest(){
//        ReplyVO replyVO = new ReplyVO();
//        replyVO.setReplyNumber(30L);
//        replyVO.setReplyContent("수정된 댓글");
//        log.info("UPDATE : " + replyDAO.modify(replyVO));
//    }

//    @Test
//    public void getListTest(){
//        replyDAO.getList(new Criteria(2, 10), 2098603L)
//                .stream().map(ReplyVO::toString).forEach(log::info);
//    }

    @Test
    public void getTotalTest(){
        log.info("댓글 개수 : " + replyDAO.getTotal(2098603L));
    }
}
