package com.ll;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Quotation {
    @Getter @Setter
    int quoteID;
    @Getter @Setter
    String writer;
    @Getter @Setter
    String content;
}