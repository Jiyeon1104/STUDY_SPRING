package com.example.ex00.dependency;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor // final 또는 @NonNull이 붙은 필드만 초기화 생성자로 선언
public class Coding {
    // 필드 주입
    // 굉장히 편하다 - 장점
    // final을 붙일 수 없기 때문에 다른 곳에서 변형이 가능하다 - 단점
    // 순환 참조 시 오류가 발생하지 않기 때문에 StackOverFlow가 발생한다 (StackOverFlow 예외 : stack or heap이 넘쳤다는 것 - 메모리 손상 가능성 유)
    // 메모리 : OS Kernel > Stack(지역,매개변수) >- -  F >- -  Heap(주소) > Bss(전역 변수 중에서 자동 초기화 되는 변수) > Data 영역 (원하는 값으로 초기화) > text (코드, 상수)
    //@Autowired
    @NonNull
    private final Computer computer; // 화면쪽에서 메서드로만 접근 가능하게끔 만들어주는 것

//    생성자 주입
//    순환 참조 시 컴파일러 인지 가능, 오류 발생
//    메모리에 할당하면서 초기값으로 주입되므로 final 키워드 사용 가능, 다른 곳에서 변형이 불가능 함
//    의존성 주입이 되지 않는다면 객체가 생성되지 않으므로 NullPointerException(NPE)를 방어할 수 있음
//    @Autowired
//    public Coding(Computer computer) {
//        this.computer = computer;
//    }
}
