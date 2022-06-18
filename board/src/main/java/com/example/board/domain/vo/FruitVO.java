package com.example.board.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class FruitVO {
    private Integer price;
    private String name;
    private String store;

    FruitVO(){};
}
