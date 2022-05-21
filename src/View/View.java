package View;

import Model.Model;

public class View {

    Model model;
    //인원 수를 입력하세요
    //지도 출력
    //(Model.Player.name)의 차례입니다. 쉬실려면 'S', 계속하시려면 'G'를 눌러주세요
    //만약 쉰다?
    //(Model.Player.name)의 차례입니다. 쉬실려면 'S', 계속하시려면 'G'를 눌러주세요
    //계속 한다?
    //주사위 결과: (1~6), 다리 카드 개수: (0~6), 이동 가능 칸수: (0~6)
    //이동 방향을 입력하세요. 왼쪽:L, 오른쪽:R, 위쪽:U, 아래쪽:D 예시: RRRDDD
    //잘못 입력?
    //이동 방향을 입력하세요. 왼쪽:L, 오른쪽:R, 위쪽:U, 아래쪽:D 예시: RRRDDD
    //잘 입력?
    //지도 출력

    public void request_pNum(){
        System.out.println("인원 수를 입력하세요");
    }

    public void request_howTurn(){

    }

    public void print_die(int number){
        System.out.println("주사위 숫자는 ["+number+"] 입니다.");
    }

    public void print_moveableNum(int number){
        System.out.println("이동 가능 횟수는 ["+number+"] 입니다.");
    }
}
