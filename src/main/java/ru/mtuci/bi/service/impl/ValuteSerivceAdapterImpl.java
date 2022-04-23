package ru.mtuci.bi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mtuci.bi.delegate.CheckDataDelegate;
import ru.mtuci.bi.dto.*;
import ru.mtuci.bi.service.ValuteSerivceAdapter;

import java.util.List;

@Service
public class ValuteSerivceAdapterImpl implements ValuteSerivceAdapter {

    Logger log = LoggerFactory.getLogger(ValuteSerivceAdapterImpl.class);


    @Autowired
    private RestTemplate restTemplate;
    private static final String VALUTE_ADAPTER_URL = "http://localhost:8090/currency";

    @Override
    public List<CurrencyCodeResponseDto> getListCodes() throws Exception {
        ResponseEntity<List<CurrencyCodeResponseDto>> responseEntity = restTemplate
                .exchange(VALUTE_ADAPTER_URL+"/codes", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<CurrencyCodeResponseDto>>(){});
        if(responseEntity.getStatusCode().value() != 200){
            throw new Exception("Answer: "+responseEntity.getStatusCodeValue());
        }
        return responseEntity.getBody();
    }

    @Override
    public CurrencyRUBResponseDto getCurrencyRUB(String code) throws Exception {
        ResponseEntity<CurrencyRUBResponseDto> responseEntity = restTemplate
                .exchange(String.format("%s/%s/%s", VALUTE_ADAPTER_URL, "RUB", code), HttpMethod.GET, null,
                        new ParameterizedTypeReference<CurrencyRUBResponseDto>(){});
        if(responseEntity.getStatusCode().value() != 200){
            throw new Exception("Answer: "+responseEntity.getStatusCodeValue());
        }
        return responseEntity.getBody();
    }

    @Override
    public CurrencyConversionResponseDto getCurrencyConversion(String code, double value) throws Exception {
        String uri = String.format("%s/%s?code=%s&value=%s", VALUTE_ADAPTER_URL, "conversion", code, value);
        log.info(uri);
        ResponseEntity<CurrencyConversionResponseDto> responseEntity = restTemplate
                .exchange(uri, HttpMethod.GET, null,
                        new ParameterizedTypeReference<CurrencyConversionResponseDto>(){});
        if(responseEntity.getStatusCode().value() != 200){
            throw new Exception("Answer: "+responseEntity.getStatusCodeValue());
        }
        return responseEntity.getBody();
    }

    @Override
    public CurrencyValueResponseDto currencyValue(CurrencyValueRequestDto requestDto) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CurrencyValueRequestDto> requestEntity = new HttpEntity<>(requestDto, headers);
        ResponseEntity<CurrencyValueResponseDto> responseEntity = restTemplate
                .exchange(String.format("%s/%s", VALUTE_ADAPTER_URL, "conversion"), HttpMethod.POST, requestEntity,
                        new ParameterizedTypeReference<CurrencyValueResponseDto>(){});
        if(responseEntity.getStatusCode().value() != 200){
            throw new Exception("Answer: "+responseEntity.getStatusCodeValue());
        }
        return responseEntity.getBody();
    }
}
