package com.example.board.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

//Criteria : 검색의 기준
@Component
@Data
@AllArgsConstructor
public class Criteria {
    // 게시판 페이징은 크게 두 개의 로직으로 나누어지는데 Criteria (검색을 위한 기준 데이터) 또 하나는 PageDTO이고
    // PageDTO는 Criteria를 기반함
    private int pageNum; // 현재 페이지의 번호를 의미
    private int amount; // 페이지당 출력할 데이터의 개수
    private String type;
    private String keyword;

    //생성자로 무조건 한 번은 실행되게 함
    // 기본 페이지를 1페이지로 잡고 한 번에 10개씩 보여줌
    public Criteria() {
        this(1, 10);
    }

    public Criteria(int pageNum, int amount){
        this.pageNum = pageNum;
        this.amount = amount;
    }

    // UriComponentsBuilder를 이용해서 링크를 생성
    public String getListLink(){
        // Static Factory Method중에 하나를 이용하여 UriComponentsBuilder객체를 생성
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                // URI 구성 요소 설정
                .queryParam("pageNum", this.pageNum)
                .queryParam("amount", this.amount)
                .queryParam("type", this.type)
                .queryParam("keyword", this.keyword);
        // toUriString() 메소드를 이용해 String 형태의 URI를 얻어냄
        return builder.toUriString();
    }

    public String[] getTypes(){
        // null이면 오류가 뜨기 때문에 null 일 때는 비어있는 배열을 리턴하여 넣어줌
        return type == null ? new String[] {} : type.split("");
    }
}

















