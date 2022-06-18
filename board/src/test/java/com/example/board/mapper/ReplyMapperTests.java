package com.example.board.mapper;

import com.example.board.domain.vo.BoardVO;
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

    //   @Test
//    public void replyMapperTest(){
//        log.info(replyMapper + "");
//    }

    private Long[] arBno = {10486159L,10486160L,10486161L,10486162L,10486163L};
    @Test
    public void insertTest(){
        // 최근 5개의 게시글에 2개씩 댓글 달기
        IntStream.rangeClosed(1, 10).forEach(i -> {
        ReplyVO replyVO = new ReplyVO();
        replyVO.setBoardBno(arBno[i % 5]);
        replyVO.setReplyContent("새로운 댓글 내용");
        replyVO.setReplyWriter("kjy1111");
        replyMapper.insert(replyVO);

        log.info("댓글 번호" + replyVO.getReplyNumber());
    });
    }

//    @Test
//    public void readReplyTest(){
//        Long replyNumber = 4L;
//        ReplyVO replyVO = replyMapper.readReply(replyNumber);
//          log.info(replyMapper.readReply(replyNumber).toString());
//    }
//
//    @Test
//    public void deleteTest(){
//        log.info("DELETE COUNT : " + replyMapper.delete(4L));
//    }

//    @Test
//    public void updateTest(){
//        ReplyVO replyVO = new ReplyVO();
//        replyVO.setBoardBno(10486159L);
//        replyVO.setReplyContent("수정 댓글");
//        replyVO.setReplyNumber(4L);
//        log.info("UPDATE COUNT" + replyMapper.update(replyVO));
//    }

//    @Test
//    public void getListTest(){
//
//    }

    @Test
    public void getTotalTest(){
        log.info("댓글 개수: " + replyMapper.getTotal(10486161L));
    }
}
