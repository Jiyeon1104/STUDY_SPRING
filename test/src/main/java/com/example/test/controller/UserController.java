package com.example.test.controller;

import com.example.test.domain.vo.UserVO;
import com.example.test.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
@Slf4j
@RequestMapping("/test/*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(value="/add", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> register(@RequestBody UserVO userVO) throws UnsupportedEncodingException {
        log.info("userVO" + userVO);
        userService.register(userVO);
        return new ResponseEntity<>(new String("댓글 등록 성공".getBytes(), "UTF-8"), HttpStatus.OK);
    }

    @GetMapping("/{userNumber}")
    public UserVO read(@PathVariable("userNumber") int userNumber){
        log.info("read >>>>>>>>>>>"  + userNumber);
        return userService.read(userNumber);
    }

    @DeleteMapping("/{userNumber}")
    public String remove(@PathVariable("userNumber") int userNumber){
        userService.remove(userNumber);
        return "회원 탈퇴 성공";
    }

    @PatchMapping("/modify/{userNumber}")
    public String modify(@PathVariable("userNumber") int userNumber, @RequestBody UserVO userVO){
        userVO.setUserNumber(userNumber);
        userService.modify(userVO);
        return "회원 정보 수정 완료";
    }


}
