package ru.mtuci.bi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mtuci.bi.dto.*;

import java.util.List;

public interface ValuteSerivceAdapter {

    List<CurrencyCodeResponseDto> getListCodes() throws Exception;
    CurrencyRUBResponseDto getCurrencyRUB(String code) throws Exception;
    CurrencyConversionResponseDto getCurrencyConversion(String code, double value) throws Exception;
    CurrencyValueResponseDto currencyValue(CurrencyValueRequestDto requestDto) throws Exception;
}
