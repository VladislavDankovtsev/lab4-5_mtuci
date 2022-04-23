package ru.mtuci.bi.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mtuci.bi.dto.CurrencyConversionResponseDto;
import ru.mtuci.bi.dto.CurrencyValueRequestDto;
import ru.mtuci.bi.dto.CurrencyValueResponseDto;
import ru.mtuci.bi.service.ValuteSerivceAdapter;

@Component
public class ConvertionDelegate implements JavaDelegate {

    Logger log = LoggerFactory.getLogger(ConvertionDelegate.class);

    @Autowired
    private ValuteSerivceAdapter adapter;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("[ConvertionDelegate] start");
        String valuteIn = String.valueOf(delegateExecution.getVariable("valuteIn"));
        String valuteOut = String.valueOf(delegateExecution.getVariable("valuteOut"));
        double value = (double) delegateExecution.getVariable("value");
        double resultValue = 0;
        log.info("[ConvertionDelegate] valuteIn: {}; valuteOut: {};value: {}",valuteIn,valuteOut,value);
        if(valuteIn.equals("RUB")){
            CurrencyConversionResponseDto conversionResponseDto = adapter.getCurrencyConversion(valuteOut,value);
            resultValue = conversionResponseDto.getValue();
        } else {
            CurrencyValueRequestDto requestDto = new CurrencyValueRequestDto();
            requestDto.setCodeIn(valuteIn);
            requestDto.setCodeOut(valuteOut);
            requestDto.setValue(value);
            CurrencyValueResponseDto currencyValue = adapter.currencyValue(requestDto);
            resultValue = currencyValue.getValueOut();
        }
        log.info("[ConvertionDelegate] resultValue: {}",resultValue);
        delegateExecution.setVariable("resultValue", resultValue);
        log.info("[ConvertionDelegate] end SUCCESS");
        delegateExecution.setVariable("status", true);

    }
}
