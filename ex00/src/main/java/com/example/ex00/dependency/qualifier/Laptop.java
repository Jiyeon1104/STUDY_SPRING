package com.example.ex00.dependency.qualifier;

import com.example.ex00.dependency.qualifier.Computer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("laptop")
public class Laptop implements Computer {

    @Override
    public int getScreenWidth() {
        return 1200;
    }
}