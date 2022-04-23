package ru.mtuci.bi.dto;

public class CurrencyValueResponseDto {
    private String codeIn;
    private double valueIn;
    private String codeOut;
    private double valueOut;

    public CurrencyValueResponseDto() {
    }

    public String getCodeIn() {
        return codeIn;
    }

    public void setCodeIn(String codeIn) {
        this.codeIn = codeIn;
    }

    public double getValueIn() {
        return valueIn;
    }

    public void setValueIn(double valueIn) {
        this.valueIn = valueIn;
    }

    public String getCodeOut() {
        return codeOut;
    }

    public void setCodeOut(String codeOut) {
        this.codeOut = codeOut;
    }

    public double getValueOut() {
        return valueOut;
    }

    public void setValueOut(double valueOut) {
        this.valueOut = valueOut;
    }
}
