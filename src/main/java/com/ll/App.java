package com.ll;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class App {

    private Scanner scanner;
    private int quoteID;
    private List<Quotation> quotations;

    public App(){
        scanner = new Scanner(System.in);
        quoteID = 0;
        quotations = new ArrayList<>();
    }

    public void run() {

        System.out.println("== 명언앱 ==");
        readTextFile();

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);

            switch (rq.getAction()){
                case "종료":
                    writeTextFile();
                    return;
                case "등록":
                    write();
                    break;
                case "목록":
                    show();
                    break;
                case "삭제":
                    delete(rq);
                    break;
                case "수정":
                    edit(rq);
            }
        }
    }

    private void write(){
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String writer = scanner.nextLine();
        quoteID++;
        Quotation quotation = new Quotation(quoteID, writer, content);
        quotations.add(quotation);
        System.out.printf("%d번 명언이 등록되었습니다.\n", quoteID);
    }
    private void show(){

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

    private void delete(Rq rq) {
        int QuoteID = rq.getParamAsInt("id", 0);

        if (QuoteID == 0) {
            System.out.println("id를 정확히 입력해주세요.");
        }

        int index = findQuoteIndexById(QuoteID);
        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", QuoteID);
            return;
        }

        quotations.remove(index);
        System.out.printf("%d번 명언을 삭제되었습니다.\n", QuoteID);
    }

    private int findQuoteIndexById(int QuoteID) {
        for (int i = 0; i < quotations.size(); i++) {
            Quotation quotation = quotations.get(i);

            if (quotation.getQuoteID() == QuoteID) {
                return i;
            }
        }
        return -1;
    }

    private void edit(Rq rq){
        int quoteID = rq.getParamAsInt("id", 0);

        if (quoteID == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수를 끝낸다.
        }

        int index = findQuoteIndexById(quoteID);
        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", quoteID);
            return;
        }

        Quotation quotation = quotations.get(index);
        System.out.printf("명언(기존) : %s\n", quotation.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.printf("작가(기존) : %s\n", quotation.getWriter());
        System.out.print("작가 : ");
        String writer = scanner.nextLine();
        quotation.setContent(content);
        quotation.setWriter(writer);
        System.out.printf("%d번 명언을 수정되었습니다.\n", quoteID);
    }

    private void readTextFile() {

        String filePath = "/Users/s2hmuel/Documents/quotation.txt";
        int linesToSkip = 2;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                if (lineCount < linesToSkip) {
                    lineCount++;
                    continue;
                }
                String[] quoteBits = line.split("/");
                if(quoteBits.length == 3){
                    String stirngQuoteID = quoteBits[0].trim();
                    int quoteID = Integer.parseInt(stirngQuoteID);
                    String writer = quoteBits[1];
                    String content = quoteBits[2];
                    fileToQuotation(quoteID, writer, content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeTextFile() {
        String filePath = "/Users/s2hmuel/Documents/quotation.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
             PrintWriter writer = new PrintWriter(bufferedWriter)) {
                writer.println("번호 / 작가 / 명언");
                writer.println("----------------------");
            for (int i = 0; i < quotations.size(); i++){
                Quotation quotation = quotations.get(i);
                writer.printf("%d / %s / %s\n", quotation.getQuoteID(), quotation.getWriter(), quotation.getContent());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fileToQuotation(int quoteID, String writer, String content){
        Quotation quotation = new Quotation(quoteID, writer, content);
        quotations.add(quotation);
    }
}
