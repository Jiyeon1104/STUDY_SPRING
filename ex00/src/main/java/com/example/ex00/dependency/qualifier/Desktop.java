package com.example.ex00.dependency.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // desktop에 component를 붙이지 않으면 spring을 인식하지 못하고 @component를 스프링이 해당 component를 인식하여 등록함
@Qualifier("desktop")
public class Desktop implements Computer{
    @Override
    public int getScreenWidth() {
        return 1960;
    }
}
