package com.ssafy.altf4.entity.champion;

public enum LineType {
    TOP("Top"), JUG("Jungle"), MID("Mid"), ADC("ADCarry"), SUP("Support");

    LineType(String line) {

        this.lineType = line;
    }

    private String lineType;
}
