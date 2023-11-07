package com.ll;

import java.util.Scanner;

public class App {
    public void run() {
        System.out.println("== 명언앱 ==");
        System.out.print("명령) ");
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();

        while (true) {
            if (cmd.equals("종료")) {
                break;
            }
        }
    }
}
