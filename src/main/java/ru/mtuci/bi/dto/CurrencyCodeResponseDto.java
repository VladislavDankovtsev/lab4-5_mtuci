package ru.mtuci.bi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyCodeResponseDto {

    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private String code;

    public CurrencyCodeResponseDto() {
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
}
