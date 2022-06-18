package com.example.board.mapper;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyMapperTests {
    @Autowired
    private ReplyMapper replyMapper;

    private Long[] arBno = {2098603L, 2098602L, 2098601L, 2098600L, 2098599L};

    @Test
    public void replyMapperTest(){
        log.info(replyMapper + "");
    }

//    @Test
//    public void insertTest(){
//        // 최근 5개의 게시글에 2개씩 댓글 달기
//        IntStream.rangeClosed(1, 10).forEach(i -> {
//            ReplyVO replyVO = new ReplyVO();
//            replyVO.setBoardBno(arBno[i % 5]);
//            replyVO.setReplyContent("새롭게 추가된 댓글" + i);
//            replyVO.setReplyWriter("user" + i);
//            replyMapper.insert(replyVO);
//        });
//    }

//    @Test
//    public void getReplyTest(){
//        log.info(replyMapper.getReply(1L).toString());
//    }

//    @Test
//    public void deleteTest(){
//        log.info("DELETE COUNT : " + replyMapper.delete(2L));
//    }

//    @Test
//    public void updateTest(){
//        ReplyVO replyVO = new ReplyVO();
//        replyVO.setReplyNumber(3L);
//        replyVO.setBoardBno(2098603L);
//        replyVO.setReplyContent("수정된 댓글");
//        log.info("UPDATE COUNT : " + replyMapper.update(replyVO));
//    }

    @Test
    public void getListTest(){
        replyMapper.getList(new Criteria(1, 10), 2098603L)
                .stream().map(ReplyVO::toString).forEach(log::info);
    }

//    @Test
//    public void getTotalTest(){
//        log.info("댓글 개수 : " + replyMapper.getTotal(2098603L));
//    }
}











