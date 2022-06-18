// Controller는 클라이언트 측 요청을 받아 이를 파악한 후 그 요청에 맞는 데이터를 model에 의뢰하는 역할을 함
package com.example.ex02.controller;

import com.example.ex02.domain.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/ex/*") // 기존 경로를 work로 바꿔주기 * 해당 숙제를 위해서 만든 template의 경로가 ex가 기본이 아니기 때문에
@Slf4j
public class ExampleController {

    // value에 경로를 작성하고 method에 호출할 서블릿 메소드를 설정
    @RequestMapping(value = "/example", method = {RequestMethod.GET, RequestMethod.POST})
    public void ex01() {
        log.info("ex01.................");
    }

    @GetMapping("")
    public void ex02() {
        log.info("ex02..................");
    }

    @GetMapping("/ex03")
    public String ex03(ExampleVO exampleVO) {
        log.info("----------------------");
        log.info(exampleVO.toString());
        log.info("----------------------");
        return "ex03";
    }

    @GetMapping("/ex04")
    public String ex04(TaskVO taskVO) {
        log.info(taskVO.toString());
        return "ex04";
    }

    // 경로 : localhost:10002/ex/login
    // action : ex/login -> 결과 : /ex/ex/login
    // action : /ex/login -> 결과 : /ex/login

    @GetMapping("/login")
    public void main() {
    }

    @PostMapping("/login")
    public String main(Example2VO example2VO) {
        if (example2VO.getId().equals("admin")) {
            return "admin";
        } else {
            return "user";
        }
    }

    // 기본경로는 @RequestMapping("/work/*") 에서 이와 같이 설정함
    @GetMapping("/attendance")
    public void attendance() {

    }

// Calendar를 사용하여 푼 실습 문제
//    @PostMapping("/attendance")
//    public String main(WorkVO workVO){
//        Calendar calendar = Calendar.getInstance(); // 캘린더로 현재 시간 값 가져오기
//        int work_h = calendar.get(Calendar.HOUR_OF_DAY); // 현재 시간의 시각
//        int work_m = calendar.get(Calendar.MINUTE); // 현재 시간의 분
//        String checkTime = workVO.getCheckInfo(); // 출퇴근한 시간, button name
//
//        String work = Integer.toString(work_h) + Integer.toString(work_m); // 정수형으로 더하기 하면 정수결과가 나오니까 문자열로 더하기
//        int workTime = Integer.parseInt(work); // 버튼을 누른 시각은 문자열로 넘어오니까 비교 연산을 위해 다시 int형으로 바꿔줌
//
//        if (checkTime.equals("출근")) { // 만약 button의 value가 출근과 같다면
//            if (workTime > 900) { // workTime이 900보다 크면
//                log.info("---------------");
//                return "/work/late"; // late.html로 넘어가고
//            } else { // 900보다 작다면
//                return "/work/getToWork"; // getToWork로 넘어감
//            }
//        } else { // 만약 button의 value가 출근이 아니라면
//            if (workTime < 1800) { // 1800보다 작으면
//                return "/work/work";  // work로 넘어가고
//            } else { // 그렇지 않다면
//                return "/work/leaveWork"; // leaveWork로 넘어감
//            }
//        }
//    }

    // 실습
    // 이름을 입력하고 출근 또는 퇴근 버튼을 클릭한다
    // 출근 시간은 9:00 이며, 퇴근 시간 18:00
    // 출근 버튼 클릭시 9시가 넘으면 지각으로 처리
    // 퇴근 버튼 클릭 시 18시 전이라면 퇴근이 아닌 업무 시간으로 처리
    // - 모든 html은 work 경로 안에 생성하며 아래와 같이 분기 별로 html문서 한 개씩 작성
    // - getToWork.html
    // - leaveWork.html
    // - late.html
    // - work.html
    // 문자열을 Date 타입으로 변경시키는 방법
    // - SimpleDataFormat 생성자에 전달받은 날짜 형식을 작성
    // - parse() 메소드에 작성한 형식에 맞는 문자열을 전달하면 Date타입으로 변한다
    // - format() 메소드에 Date타입을 전달하면 문자열로 변한다


    // Date로 객체를 생성해서 현재 시간을 구할 수 있고 SimpleDateFormat 클래스를 이용해서 원하는 포맷으로 문자열을 생성할 수 있다!
    // 현재 시간을 구하는 방법 : 1. LocalTime > 시간을 표현하는 클래스 현재 시간을 나타내는 방법 : LocalTime now = LocalTime.now();
    //                       2. LocalDateTime > 날짜와 시간을 표현하는 클래스로 해당 메소드를 사용하면 현재 날짜와 시간을 모두 구할 수 있다! 사용 방법 : LocalDateTime now = LocalDateTime.now();
    //                       3. Java8 이전의 날짜와 시간을 나타내는 Date, Calendar 클래스는 많은 부분이 변경되었기 때문에 사용을 권장하지는 않음 Date > 현재 날짜와 시간을 표현하는 방법 : Date now = new Date;
    //                       4. Calendar > Calendar.getInstance().getTime();

    // SimpleDateFormat을 사용하여 푼 실습 문제
    @PostMapping("/attendance")
    public String main(WorkVO workVO) {
        String checkTime = workVO.getCheckInfo(); // 출퇴근한 시간 button name
        SimpleDateFormat formatter = new SimpleDateFormat("HHMM"); // 원하는 데이터 포맷 지정
        Date now = new Date(); // 현재 날짜와 시간 구하기
        String nowTime = formatter.format(now); // 현재 날짜를 받아와서 지정한 원하는 데이터 포맷으로 변경해주기
        int workTime = Integer.parseInt(nowTime); // 스트링형으로 변경한 "HHMM" 형태의 nowTime을 integer로 형변환 해주기

        if (checkTime.equals("출근")) { // 만약 button의 value가 출근과 같다면
            if (workTime > 900) { // workTime이 900보다 크면
                log.info("---------------");
                return "/work/late"; // late.html로 넘어가고
            } else { // 900보다 작다면
                return "/work/getToWork"; // getToWork로 넘어감
            }
        } else { // 만약 button의 value가 출근이 아니라면
            if (workTime < 1800) { // 1800보다 작으면
                return "/work/work";  // work로 넘어가고
            } else { // 그렇지 않다면
                return "/work/leaveWork"; // leaveWork로 넘어감
            }
        }
    }

    // @GetMapping("/checkIn")
    // public String checkIn(){return "/work/checkIn"}
    // @GetMapping ("/getToWork")
    // public String getToWork(String name, Model model){
    // log.info("------------------------------");
    // log.info(name);
    // log.info("------------------------------");
    // Calendar C = Calendar.getInstance();
    // int hour = c.get(Calendar.HOUR_OF_DAY);
    // int minute = c.get(Calendar.MINUTE);
    // boolean lateCondition = hour >= 9 && minute > 0;
    // model.addAtrribute("name", name);
    // return lateCondition ? "work/late" : "work/getToWork";

    // @GetMapping("/leavework")
    // public String checkIn(){return "/work/checkIn"}
    // @GetMapping ("/getToWork")
    // public String getToWork(String name, Model model){
    // log.info("------------------------------");
    // log.info(name);
    // log.info("------------------------------");
    // SimpleDateFormat sdf = new SimpleDateFormat("aaa HH시 mm분");
    // Calendar C = Calendar.getInstance();
    // int hour = c.get(Calendar.HOUR_OF_DAY);
    // int minute = c.get(Calendar.MINUTE);
    // boolean leaveWorkCondition = hour >= 18 && minute > 0;
    // model.addAtrribute("name", name);
    // model.addAttribute("now", sdf.format(c.getTime()));
    // return leaveWorkCondition ? "work/leavework" : "work/work";

    // 실습
    // 무기를 강화하기 위해서 아래에 있는 강화 주문서를 사용할 수 있다
    // 10% 공격력 주문서 : 공격력 + 80
    // 60% 공격력 주문서 : 공격력 + 40
    // 100% 공격력 주문서 : 공격력  + 10

    // 한 번만 강화할 수 있으며, 10% 확률로 대성공을 한다
    // 대성공 시 해당 주문서 공격력의 5배가 증가한다
    // 강화하기 버튼을 눌렀을 때 알맞는 결과를 출력한다

    // ScrollVO 클래스 선언
    // 10%, 60%, 100% 주문서의 공격력 수치를 저장함
    // 기본 생성자를 호출했을 때에는 위에 작성된 공격력 수치를 기본 값으로 설정하고
    // 만약 새로운 값을 받게 되면 해당 공격력 수치로 변경되도록 생성자를 오버로딩 한다

    @GetMapping("/upgrade")
    public String upgrade(){
        return "upgrade/form";
    }

    @PostMapping("/upgrade")
    public String upgrade(String choice, Model model){
        log.info("*************** choice :::" + choice);
        ScrollVO scrollVO = new ScrollVO();

        int strength = 0;
        boolean check = false;
        switch(Integer.parseInt(choice)){
            case 0: //10%
                check = getChance(10);
                strength = scrollVO.getScroll10();
                break;
            case 1: //60%
                check = getChance(60);
                strength = scrollVO.getScroll60();
                break;
            case 2: //100%
                check = getChance(100);
                strength = scrollVO.getScroll100();
                break;
        }
        if(!check){ return "upgrade/fail";}
        if(getChance(100)){
            strength *= 5;
            model.addAttribute("strength", strength);
            return "upgrade/superSuccess";
        }
        model.addAttribute("strength", strength);
        return "upgrade/success";
    }

    public boolean getChance(int rating){
        Random r = new Random();
        int[] numbers = new int[10];
        int index = r.nextInt(numbers.length);
        for (int i=0; i<rating/10; i++){
            numbers[i] = 1;
        }

        return numbers[index] == 1;
    }

    // 실습
    // 사용자가 입력한 바코드 번호에 알맞는 상품명을 전달한다.

    @GetMapping("/market")
    public String goMarket(){return "product/market";}

    @PostMapping("/check")
    public String check(String barcode, Model model){
        String productName = null;

        switch (barcode){
            case "4383927" :
                productName = "오징어 땅콩";
                break;
            case "0832147":
                productName = "초코 우유";
                break;
            case "9841631":
                productName = "벌꿀 피자";
                break;
            case "5587578":
                productName = "샌드위치";
                break;

        }
        model.addAttribute("productName", productName);
        return "product/cashier";
}

    // 실습
    // 아이디 : apple
    // 비밀번호 : banana
    // 로그인 성공 시 apple님 환영합니다.
    // 로그인 실패 시 로그인 실패

    @GetMapping("/userLogin")
    public String userLogin(){return "login/login";}

    @PostMapping("/userLogin")
    public String userLogin(UserVO userVO){
        if(userVO.getUserId().equals("apple")){
            if(userVO.getUserPw().equals("banana")){
                return "login/success";
            }
        }
        return "login/fail";
    }

    // 노래방 기계 제작
    // 사용자의 점수에 따른 알맞은 메세지 출력

    //    노래방 기계 제작
    //    사용자의 점수에 따른 알맞는 메세지 출력
    @GetMapping("/song")
    public String goSong(){
        return "song/songbox";
    }

    @PostMapping("/song")
    public String sendMessage(String score, Model model){
        String msg = null;
        if(Integer.parseInt(score) > 50){
            msg = "가수의 실력이군요";
        }else {
            msg = "소질이 없어요";
        }
        model.addAttribute("msg", msg);
        return "song/result";
    }

    @GetMapping("/info")
    public void getInfo(@ModelAttribute ("name") String name, @ModelAttribute ("age") Integer age){
        // 전달받은 파라미터를 화면 쪽으로 보낼 때 쉽고 간편하게 사용할 수 있음
        // 여러 개의 데이터를 보낼 때에는 Model 데이터 전달자를 사용하고,
        // 2개 이하의 데이터를 보낼 때에는 @ModelAttribute()를 사용하는 것이 좋음
    }

    @GetMapping("/datas")
    // 동일한 이름의 파라미터가 여러 개 들어올 때에는 배열 또는 List로 매개변수를 설정
    // 이 때 동일한 이름으로 받아야 하기 때문에 @RequestParam("KEY")를 사용해서 전달받을 데이터의 KEY값을 지정해줌
    // KEY 파라미터명이 전달되면 뒤에 있는 매개변수로 들어감
    public void getDatas(@RequestParam ("data") ArrayList<Integer> datas){
        log.info(String.valueOf(datas.size()));
        datas.stream().map(String::valueOf).forEach(log::info);
    }

    @GetMapping("/different")
    // 파라미터 명과 매개변수 명이 다르면 직접 지정해 줌
    public void getData(@RequestParam("data") String name){

    }

}
