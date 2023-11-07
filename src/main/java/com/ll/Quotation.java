package com.ll;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Quotation {
    @Getter @Setter
    private int quoteID;
    @Getter @Setter
    private String writer;
    @Getter @Setter
    private String content;
}