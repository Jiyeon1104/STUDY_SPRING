package com.example.board.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
@Slf4j
public class MyBatisConfigTests {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private DataSource dataSource;

    @Test
    public void datasourceTest(){
        try
           (
                   Connection conn = dataSource.getConnection();
           )
        {
            log.info("---------------------------");
            log.info("dataSource connection : " + conn);
            log.info("---------------------------");
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Test
    public void sqlSessionTest(){
        log.info("-----------------------------");
        log.info("sqlSessionFactory : " + sqlSessionFactory);
        log.info("-----------------------------");

        try
                (
                        SqlSession sqlSession = sqlSessionFactory.openSession(true);
                        Connection conn = sqlSession.getConnection();
                )
        {
            log.info("---------------------------");
            log.info("sqlSession : " + sqlSession);
            log.info("sqlSession connection : " + conn);
            log.info("---------------------------");
        } catch (Exception e){
            log.error(e.getMessage());
        }

    }
}













