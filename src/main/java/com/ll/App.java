package com.ll;

import java.util.Scanner;

public class App {

        Scanner scanner = new Scanner(System.in);
        int quoteID = 0;
    public void run() {
        System.out.println("== 명언앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            switch (cmd){
                case "종료":
                    return;
                case "등록":
                    write();
            }
        }
    }

    void write(){
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String writer = scanner.nextLine();
        quoteID++;
        System.out.printf("%d번 명언이 등록되었습니다.\n", quoteID);
    }
}
