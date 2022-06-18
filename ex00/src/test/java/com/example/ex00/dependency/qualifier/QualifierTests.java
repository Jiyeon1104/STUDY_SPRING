package com.example.ex00.dependency.qualifier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class QualifierTests {

    @Autowired
    @Qualifier("desktop")
    private Computer computer;

    public void qualifierTest(){
        log.info("----------------------------");
        log.info("computer: " + computer.getScreenWidth());
        log.info("----------------------------");
    }

}
