package com.example.board.controller;
/*
REST(Representational State Transfer - 대표적인 상태 전송)
하나의 URI(identifier)는 하나의 고유한 리소스(데이터)를 대표하도록 설계됨
URL - 가서 찾아봐 저기 있어 URI - 이거야

Ajax - 자바스크립트를 이용해 서버와 브라우저가 데이터를 교환할 수 있게 하는 통신 기능 속성 : url, type, data, dataType 등
JSON - 데이터를 자바 스크립트 객체
예) /board/123 : 게시글 중 123번 게시글의 전체 정보
 */

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.FruitVO;
import com.example.board.domain.vo.ReplyPageDTO;
import com.example.board.domain.vo.ReplyVO;
import com.example.board.service.ReplyService;
import com.sun.nio.sctp.AbstractNotificationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.standard.expression.FragmentSignatureUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// RestController
@RestController
@Slf4j
@RequestMapping("/reply/*")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    // 댓글 등록
    // 브라우저에서 JSON 타입으로 데이터를 전송하고 서버에서는 댓글의 처리 결과에 따라 문자열로 결과를 리턴함
    // consumes : 전달받은 데이터의 타입
    // produces : 콜백함수로 결과를 전달할 때의 타입
    // @RequestBody : 전달받은 데이터를 알맞는 매개변수로 주입할 때 사용
    // ResponseEntity : 서버의 상태 코드, 응답 메세지 등을 담을 수 있는 타입
    @PostMapping(value = "/new", consumes="application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> create(@RequestBody ReplyVO replyVO) throws UnsupportedEncodingException{
        log.info("replyVO" + replyVO);
        replyService.register(replyVO);
        return new ResponseEntity<>(new String("댓글 등록 성공".getBytes(), "UTF-8"),HttpStatus.OK);
    }

    // 댓글 1개 조회
    @GetMapping("/{rno}")
    public ReplyVO read(@PathVariable("rno") Long replyNumber){
        log.info("read.." + replyNumber);
        return replyService.read(replyNumber);
    }

    //    댓글 전체 목록 조회
    @GetMapping("/list/{bno}/{page}")
    public ReplyPageDTO getList(@PathVariable("page") int pageNum, @PathVariable("bno") Long boardBno){
        return new ReplyPageDTO(replyService.getList(new Criteria(pageNum, 10), boardBno), replyService.getTotal(boardBno));
    }

    // 댓글 삭제
    // DELETE 매핑 : 위에서 똑같은 경로로 GetMapping을 썼는데 아래서 똑같은 경로로 다시 GetMapping을 하면 오류 발생 > POST, DELETE 동일한 경로의 다른 메소드를 쓰는 것이 좋음
    @DeleteMapping("/{rno}")
    public String remove(@PathVariable("rno") Long replyNumber){
        replyService.remove(replyNumber);
        return "댓글 삭제 성공";
    }

    // 댓글 수정
    // PUT : 자원의 전체 수정, 자원 내 모든 필드르르 전달해야 함, 일부만 전달할 경우 오류
    // PATCH : 자원의 일부 수정, 수정할 필드만 전송 (자동 주입이 아닌 부분만 수정하는 쿼리문에서 사용)
    // 자동 주입이 불가능하기 때문에 '혹시라도 null이 뜨면 원하는 값으로 채워줄게'가 아님
    // 현재 ReplyMapper.xml에는 수정 가능한 컬럼이 replyNumber와 replyContent밖에 없지만 예시를 위해 replyWriter을 추가하고
    // 만약 replyWriter 전달을 하지 않고 Content만 전달한다면 > 오류 : 부적합한 열 유형
    // 전달받지 않은 null인 친구를 default로 처리
    @PatchMapping(value={"/{rno}/{writer}", "/{rno}"/*null이 들어와도 해당 경로로 들어올 수 있게 설계해놓았기 때문에 /{rno}만 들어와도 저 경로로 안착할 수 있게 중괄호를 써서 두 가지 URI를 알려줌*/}, consumes="application/json")
    public String modify(@PathVariable("rno") Long replyNumber,@PathVariable(value = "writer", required = false/*null도 가능하게 하려고 하는 것*/) String replyWriter, @RequestBody ReplyVO replyVO) {
        log.info("modify.........." + replyVO);
        log.info("modify.........." + replyNumber);
        // 우리가 직접 만약에 replyVO에 작성자를 불러오는데 작성자가 null이라면
        // anonymous 라는 default값을 적용해주는 것
        if (replyVO.getReplyWriter() == null) { //JSON 검증
            replyVO.setReplyWriter(Optional.ofNullable(replyVO.getReplyWriter()).orElse("anonymous"/*부분 수정에서 필요한 default값*/)); // URI 검증
        } // try~catch문을 사용하지 않고도 null을 처리할 수 있게 함 > default값 anonymous를 넣어주게끔 설계함
        replyVO.setReplyNumber(replyNumber);
        replyService.modify(replyVO);
        return "댓글 수정 성공";
    }
        // 굳이 default값을 설정해주면서까지 PATCH를 써야하는가에 대한 의문점
        // 기존에 있는 정보에서 select를 해오고 select된 값 중에 null이라면 기존 select값을 넣어주는 것이 좋음
        // 그렇게 할 바에는 POST방식을 써서 null일 때 select값을 넣어주는 쿼리를 작성하면 됨


    // ajax 연습 5개
    // 1번 매개변수 없고 리턴은 문자열
    // 2번 매개변수 있고 리턴은 문자열
    // 3번 매개변수 없고 리턴은 JSON Object
    // 4번 매개변수 있고 리턴은 JSON Object
    // 5번 매개변수 여러 개 있고 JSON Array

    // 1번
    @GetMapping("/test")
    public String test(){
        return "안녕";
    }

    // 2번
    @GetMapping("/test2/{name}")
    public String test2(@PathVariable("name") String name){
        return name + "성공";
    }

     // 3번
    @GetMapping("/test3")
    public FruitVO test3(){
        log.info("test3...");
        return new FruitVO(30000, "사과", "사과농장");
    }

    // 4번
    @PostMapping("/test4")
    public FruitVO test4(@RequestBody FruitVO fruitVO) {
        log.info("test4.." + fruitVO);
        return fruitVO;
    }

     // 5번
    @GetMapping("/test5/{price}/{name}/{store}")
    public List<String> test5(@PathVariable("price")Integer price, @PathVariable("name")String name, @PathVariable("store")String store){
        log.info("test5..........." + price + name + store);
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(price));
        list.add(name);
        list.add(store);
        return list;
    }
}
