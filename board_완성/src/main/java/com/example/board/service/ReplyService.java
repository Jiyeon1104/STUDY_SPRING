package com.example.board.service;

import com.example.board.domain.dao.ReplyDAO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyDAO replyDAO;

    //댓글 추가
    public void register(ReplyVO replyVO){
        log.info("register..... : " + replyVO);
        replyDAO.register(replyVO);
    }

    //댓글 1개 조회
    public ReplyVO read(Long replyNumber){
        log.info("read...... : " + replyNumber);
        return replyDAO.read(replyNumber);
    }

    //댓글 삭제
    public boolean remove(Long replyNumber){
        log.info("remove...... : " + replyNumber);
        return replyDAO.remove(replyNumber);
    }

    //댓글 수정
    public boolean modify(ReplyVO replyVO){
        log.info("modify........ : " + replyVO);
        return replyDAO.modify(replyVO);
    }

    //댓글 목록
    public List<ReplyVO> getList(Criteria criteria, Long boardBno){
        log.info("getList........ : " + criteria);
        log.info("getList........ : " + boardBno);
        return replyDAO.getList(criteria, boardBno);
    }

    //댓글 개수
    public int getTotal(Long boardBno){
        log.info("getTotal........ : " + boardBno);
        return replyDAO.getTotal(boardBno);
    }
}
