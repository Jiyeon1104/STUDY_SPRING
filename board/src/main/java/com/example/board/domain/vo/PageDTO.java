package com.example.board.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
// 기본 생성자를 만들고 total, pageCount, Criteria를 매개변수로 받는 생성자를 선언하여
// 인스턴스 변수를 계산하고 넣어서 생성자만 호출하면 페이징 처리 가능
public class PageDTO {
    // 페이지 번호 계산에 필요한 Criteria 클래스의 멤버 변수들에 대한 정보를 가짐
    private Criteria criteria;
    //만약 pageCount가 10이고 currentPageNo가 3이라면 1페이지를 의미
    private int startPage;
    // 만약 pageCount가 10이고 currentPageNo가 3이라면 10페이지를 의미
    private int endPage;
    // 실제 마지막 page 번호를 의미함
    // endPage가 realEnd보다 클 수는 없으므로 이를 통해 연산하여 줌
    private int realEnd;
    // 화면 하단에 출력할 페이지의 사이즈를 의미 1~5, 1~10, 1~20등
    private int pageCount;
    // prev : 이전 페이지 존재 여부를 확인 > 만약 현재 페이지 번호가 3이라면 첫 페이지 번호는 1이 되고 첫 페이지 번호가 1이면 false, 아니면 true가 됨
    // next : endPage가 realEnd보다 작으면 true를 반환
    private boolean prev, next;

    private int total;

    public PageDTO(Criteria criteria, int total) {
        this(criteria, 10, total);
    }

    public PageDTO(Criteria criteria, int pageCount, int total) {
        this.criteria = criteria;
        this.total = total;
        // 현재 페이지를 기준으로 소수점까지 모두 계산하여 보여질 마지막 페이지 번호를 구함
        this.endPage = (int)Math.ceil(criteria.getPageNum() / (double)pageCount) * pageCount;
        this.startPage = this.endPage - pageCount + 1;
        this.realEnd = (int)Math.ceil((double)total / criteria.getAmount());

        if(realEnd < this.endPage){
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }
}
















