package com.example.board.controller;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyPageDTO;
import com.example.board.domain.vo.ReplyVO;
import com.example.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

/*
* REST(Representational State Transfer)
*   하나의 URI는 하나의 고유한 리소스(데이터)를 대표하도록 설계된다.
*   예)/board/123 : 게시글 중 123번 게시글의 전체 정보
* */

@RestController
@Slf4j
@RequestMapping("/reply/*")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

//    댓글 등록
//    브라우저에서 JSON타입으로 데이터를 전송하고 서버에서는 댓글의 처리 결과에 따라 문자열로 결과를 리턴한다.
//    consumes : 전달받은 데이터의 타입
//    produces : 콜백함수로 결과를 전달할 때의 타입
//    @RequestBody : 전달받은 데이터를 알맞는 매개변수로 주입할 때 사용
//    ResponseEntity : 서버의 상태코드, 응답 메세지 등을 담을 수 있는 타입
    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> create(@RequestBody ReplyVO replyVO) throws UnsupportedEncodingException {
        log.info("replyVO : " + replyVO);
        replyService.register(replyVO);
        return new ResponseEntity<>(new String("댓글 등록 성공".getBytes(), "UTF-8") ,HttpStatus.OK);
    }

//    댓글 1개 조회
    @GetMapping("/{rno}")
    public ReplyVO read(@PathVariable("rno") Long replyNumber){
        log.info("read........ : " + replyNumber);
        return replyService.read(replyNumber);
    }

//    댓글 전체 목록 조회
    @GetMapping("/list/{bno}/{page}")
    public ReplyPageDTO getList(@PathVariable("page") int pageNum, @PathVariable("bno") Long boardBno){
        return new ReplyPageDTO(replyService.getList(new Criteria(pageNum, 10), boardBno), replyService.getTotal(boardBno));
    }

//    댓글 삭제
    @DeleteMapping("/{rno}")
    public String remove(@PathVariable("rno") Long replyNumber){
        replyService.remove(replyNumber);
        return "댓글 삭제 성공";
    }

//    댓글 수정
//    PUT : 자원의 전체 수정, 자원 내 모든 필드를 전달해야 함, 일부만 전달할 경우 오류
//    PATCH : 자원의 일부 수정, 수정할 필드만 전송(자동 주입이 아닌 부분만 수정하는 쿼리문에서 사용)
    @PatchMapping(value = {"/{rno}/{writer}", "/{rno}"}, consumes = "application/json")
    public String modify(@PathVariable("rno") Long replyNumber, @PathVariable(value = "writer", required = false) String replyWriter, @RequestBody ReplyVO replyVO){
        log.info("modify......... : " + replyVO);
        log.info("modify......... : " + replyNumber);

        if(replyVO.getReplyWriter() == null){ // JSON 검증
            replyVO.setReplyWriter(Optional.ofNullable(replyWriter).orElse("anonymous")); // URI 검증
        }
        replyVO.setReplyNumber(replyNumber);
        replyService.modify(replyVO);
        return "댓글 수정 성공";
    }

    //5개
    //1번 매개변수 없고 리턴은 문자열
    //2번 매개변수 1개 있고 리턴은 문자열
    //3번 매개변수 없고 리턴은 JSON Object
    //4번 매개변수 여러 개 있고 리턴은 JSON Object
    //5번 매개변수 여러 개 있고 리턴은 JSON Array

    //4개(DB 테이블 생성)
    //1번 매개변수 1개 있고 리턴은 문자열
    //2번 매개변수 없고 리턴은 JSON Object
    //3번 매개변수 여러 개 있고 리턴은 JSON Object
    //4번 매개변수 여러 개 있고 리턴은 JSON Array

    //Git 개인 레포지토리 push

}

















