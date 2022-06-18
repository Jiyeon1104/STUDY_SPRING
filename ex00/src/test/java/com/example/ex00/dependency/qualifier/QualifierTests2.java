package com.example.ex00.dependency.qualifier;

import com.example.ex00.dependency.qualifier.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class QualifierTests2 {

    @Autowired
    @Qualifier("vips")
    private Restaurant restaurant;

    @Test
    public void QualifiedTest2(){
        log.info("------------------------");
        log.info("sidebar : " + restaurant.checkSidebar());
        log.info("steak : " + Restaurant.steak);
    }
}
