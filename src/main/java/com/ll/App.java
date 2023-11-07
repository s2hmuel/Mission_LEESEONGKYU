package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    Scanner scanner;
    int quoteID;
    List<Quotation> quotations;

    App(){
        scanner = new Scanner(System.in);
        quoteID = 0;
        quotations = new ArrayList<>();

    }

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
                    break;
                case "목록":
                    show();
                    break;
            }
        }
    }

    void write(){
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String writer = scanner.nextLine();
        quoteID++;
        Quotation quotation = new Quotation(quoteID, writer, content);
        quotations.add(quotation);
        System.out.printf("%d번 명언이 등록되었습니다.\n", quoteID);
    }
    void show(){

        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");


        if(quotations.isEmpty()){
            System.out.println("등록된 명언이 없습니다!");
        }

        for (int i = 0; i < quotations.size(); i++) {
            Quotation quotation = quotations.get(i);
            System.out.printf("%d / %s / %s\n", quotation.getQuoteID(), quotation.getWriter(), quotation.getContent());
        }
    }
}
