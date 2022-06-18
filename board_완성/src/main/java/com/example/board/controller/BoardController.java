package com.example.board.controller;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.PageDTO;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/*
*   Task        URL                 Method      Parameter       Form        URL 이동
*
*   전체목록     /board/list          GET
*   등록처리     /board/register      POST        모든 항목        필요         /board/list
*   조회        /board/read          GET          bno
*   삭제처리     /board/remove        GET         bno                         /board/list
*   수정처리     /board/modify        POST        모든 항목        필요         /board/list
*
* */

@Controller
@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
//    리턴 타입을 void로 작성해도 되지만,
//    다른 컨트롤러에서 getList()를 호출하기 때문에
//    html 경로를 직접 문자열로 작성해야 한다.
    public String getList(Criteria criteria, Model model){
        log.info("*************");
        log.info("/list");
        log.info("*************");
        model.addAttribute("boardList", boardService.getList(criteria));
        model.addAttribute("pageDTO", new PageDTO(criteria, boardService.getTotal()));
        return "/board/list";
    }

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public RedirectView register(BoardVO boardVO, RedirectAttributes rttr){
        log.info("*************");
        log.info("/register");
        log.info("*************");
        boardService.register(boardVO);

//        redirect 방식으로 전송할 때에는 request scope를 사용할 수 없다.
//        RedirectAttributes 객체는 두 가지 방법을 제공해준다.
//        1. 쿼리 스트링
//          URL 뒤에 전달한 KEY와 VALUE를 쿼리스트링으로 연결해준다.
//        rttr.addAttribute("boardBno", boardVO.getBoardBno());

//        2. Flash 사용
//          세션에 파라미터를 저장하고, request객체가 초기화 된 후 다시 저장해준다.
        rttr.addFlashAttribute("boardBno", boardVO.getBoardBno());

        return new RedirectView("/board/list");
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long boardBno, Criteria criteria, HttpServletRequest request, Model model){
        log.info("*************");
        String requestURL = request.getRequestURI();
        log.info(requestURL.substring(requestURL.lastIndexOf("/")));
        log.info("*************");
        log.info("================================");
        log.info(criteria.toString());
        log.info("================================");
        model.addAttribute("board", boardService.read(boardBno));
    }

//    수정
//    Redirect 방식으로 전송
//    Flash로 데이터 전달 - 수정 성공 시에만 "success" 전달
    @PostMapping("/modify")
    public RedirectView modify(BoardVO boardVO, Criteria criteria, RedirectAttributes rttr){
        log.info("*************");
        log.info("/modify");
        log.info("*************");
        log.info("================================");
        log.info(criteria.toString());
        log.info("================================");
        if(boardService.modify(boardVO)){
//            컨트롤러에서 다른 컨트롤러의 매개변수로 파라미터를 전달할 때에는
//            addAttribute(), 쿼리스트링 방식으로 전달해야 받을 수 있다.
//            Flash방식은 최종 응답 화면에서 사용될 파라미터를 전달할 때에만 사용하도록 한다.
            rttr.addAttribute("boardBno", boardVO.getBoardBno());
            rttr.addAttribute("pageNum", criteria.getPageNum());
            rttr.addAttribute("amount", criteria.getAmount());
        }
        return new RedirectView("/board/read");
    }

    @PostMapping("/remove")
    public String remove(Long boardBno, Criteria criteria, Model model){
        log.info("*************");
        log.info("/remove");
        log.info("*************");
        boardService.remove(boardBno);
        //다른 컨트롤러로 이동하고자 할 때 해당 메소드를 직접 실행한다.
        //만약 필요한 파라미터가 있다면 최초 요청 처리 메소드를 통해 파라미터를 전달해준다.
        return getList(criteria, model);
    }
}












