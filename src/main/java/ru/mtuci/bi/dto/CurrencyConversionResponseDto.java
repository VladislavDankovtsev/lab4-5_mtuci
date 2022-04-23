package ru.mtuci.bi.dto;

public class CurrencyConversionResponseDto {

    private String name;
    private String code;
    private double value;

    public CurrencyConversionResponseDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
