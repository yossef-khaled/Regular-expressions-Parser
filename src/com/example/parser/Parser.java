package com.example.parser;

public class Parser {
    public static void main(String[] args) {
        abstract class RegEx {

        }

        class RegExParser {
            private String input;

            public RegExParser (String input ){
                this.input = input;
            }

            /* Recursive descent parsing functions */

            private char peek() {
                return input.charAt(0);
            }

            private void eat(char c) {
                if(peek() == c) {
                    this.input = this.input.substring(1);
                }
                else {
                    throw new
                            RuntimeException("Expected " + c + "found " + peek());
                }
            }

            private char next() {
                char c = peek();
                eat(c);
                return c;
            }

            private boolean more() {
                return input.length() > 0;
            }


            /*Regular expression term types*/

            private RegEx regex() {
                RegEx term = term();
                if(more() && peek() == '|') {
                    eat('|');
                    RegEx regex = regex();
                    return new Choice(term, regex);
                }
                else {
                    return term;
                }
            }

            private RegEx term() {
                RegEx factor = RegEx.blank;

                while (more() && peek() != ')' && peek() != '|') {
                    RegEx nextFactor = factor() ;
                    factor = new Sequence(factor,nextFactor) ;
                }

                return factor;
            }

            private RegEx factor() {

            }

            private RegEx base() {

            }

            class Choice extends RegEx {
                private RegEx thisOne;
                private RegEx thatOne;

                public Choice (RegEx thisOne, RegEx thatOne) {
                    this.thisOne = thisOne;
                    this.thatOne = thatOne;
                }
            }

            class Sequence extends RegEx {
                private RegEx first ;
                private RegEx second ;

                public Sequence (RegEx first, RegEx second) {
                    this.first = first ;
                    this.second = second ;
                }
            }

            class Blank extends RegEx {

            }
        }
    }
}
