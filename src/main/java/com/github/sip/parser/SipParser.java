package com.github.sip.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import java_cup.sym;
import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

public class SipParser {

    public SipParser() {

    }

    public List<String> parse(String input) throws Exception {

        List<String> list = new ArrayList<>();

        StringReader reader = new StringReader(input);

        Symbol symbol;

        SipLexer lexer = new SipLexer(reader);

        do {
            symbol = lexer.next_token();
            System.out.println("token: " + symbol.value);
        } while (symbol.sym != sym.EOF);

        return list;
    }

    public static void main(String[] args) throws Exception {

        List<String> list = new SipParser().parse("aaaaaas1233");
        list.forEach(System.out::println);
    }
}
