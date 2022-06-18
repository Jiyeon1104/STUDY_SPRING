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
public class ReplyControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

//    @Test
//    public void insertTest() throws Exception {
//        log.info("추가된 댓글 번호 : " + mockMvc.perform(MockMvcRequestBuilders.post("/reply/insert")
//                .param("boardBno", "10486159L")
//                .param("replyContent", "댓글 새 내용")
//                .param("replyWriter", "Test")
//        ).andReturn().getFlashMap());
//    }

    @Test
    public void readReplyTest() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/reply/readReply").param("replyNumber", "4")
        ).andReturn().getModelAndView().getModelMap().toString());
    }
}
