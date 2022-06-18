package com.example.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
public class BoardControllerTests {
//    MockMvc의 경우 view를 실제로 실행하지 않기 때문에 가짜로 viewResolver를 만들어주어서 데이터 값이 view에 제대로 전달되는지 정도의 확인이 가능함
//    마치 브라우저에서 URL을 요청한 것처럼 환경을 만들어 준다.
    private MockMvc mockMvc;

    // 서버 환경 및 설정, 요청 등을 처리해주는 WebApplicationContext를 불러온다.
    // 여기서 사용된 Autowired가 무엇인가?
    @Autowired
    private WebApplicationContext webApplicationContext;

//    모든 @Test 실행 전 한 번씩 실행된다.
//    @Test 메소드가 2개라면 두 번 실행된다.
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getListTest() throws Exception{
        log.info(mockMvc.perform(
                MockMvcRequestBuilders.get("/board/list")
                .param("pageNum", "39")
                .param("amount", "10")
        ).andReturn().getModelAndView().getModelMap().toString());
    }

//    @Test
//    public void registerTest() throws Exception {
//        log.info("추가된 게시글 번호 : " + mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
//                .param("boardTitle", "테스트 새 글 제목")
//                .param("boardContent", "테스트 새 글 내용")
//                .param("boardWriter", "Test")
//        ).andReturn().getFlashMap());
//    }

//    @Test
//    public void readTest() throws Exception {
//        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/read").param("boardBno", "3"))
//                .andReturn().getModelAndView().getModelMap().toString());
//    }

//    @Test
//    public void modifyTest() throws Exception {
//        log.info(mockMvc.perform(
//                MockMvcRequestBuilders.post("/board/modify")
//                .param("boardBno", "90")
//                .param("boardTitle", "방금 수정한 제목")
//                .param("boardContent", "방금 수정한 내용")
//                .param("boardWriter", "수정자")
//        ).andReturn().getFlashMap().toString());
//    }

//    @Test
//    public void removeTest() throws Exception{
//        log.info(mockMvc.perform(
//                MockMvcRequestBuilders.get("/board/remove")
//                .param("boardBno", "7")
//        ).andReturn().getModelAndView().getViewName());
//    }
}















