package com.example.ex00.dependency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DependencyTests {

    @Autowired
    private Coding coding; //필터에는 Autowired

    @Test
    public void dependencyTest(){ // 메서드에서는 new
        log.info("--------------------");
        log.info("coding: " + coding);
        log.info("computer: " + coding.getComputer());
        log.info("--------------------");
    }
}
