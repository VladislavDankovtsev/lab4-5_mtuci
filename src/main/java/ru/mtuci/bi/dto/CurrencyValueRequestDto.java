package ru.mtuci.bi.dto;

public class CurrencyValueRequestDto {

    private String codeIn;
    private double value;
    private String codeOut;

    public CurrencyValueRequestDto() {
    }

    public String getCodeIn() {
        return codeIn;
    }

    public void setCodeIn(String codeIn) {
        this.codeIn = codeIn;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCodeOut() {
        return codeOut;
    }

    public void setCodeOut(String codeOut) {
        this.codeOut = codeOut;
    }
}
