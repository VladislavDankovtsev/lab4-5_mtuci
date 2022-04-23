package ru.mtuci.bi.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mtuci.bi.dto.CurrencyCodeResponseDto;
import ru.mtuci.bi.service.ValuteSerivceAdapter;

import java.util.List;

@Component
public class CheckDataViktoriaDelegate implements JavaDelegate {

    Logger log = LoggerFactory.getLogger(CheckDataDelegate.class);

    private static Double sum = Double.valueOf(100000000);

    @Autowired
    private ValuteSerivceAdapter adapter;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("[CheckDataDelegate] start");
        String valuteOut = String.valueOf(delegateExecution.getVariable("valuteOut"));
        boolean checkValuteIn = false;
        boolean checkValuteOut = false;
        String valuteIn = String.valueOf(delegateExecution.getVariable("valuteIn"));
        if(valuteIn.equals("RUB")) {
            delegateExecution.setVariable("valuteInName", "Российский рубль");
        }
        List<CurrencyCodeResponseDto> listCodes = adapter.getListCodes();
        for (CurrencyCodeResponseDto code : listCodes) {
            if(!valuteIn.equals("RUB")) {
                if (code.getCode().equals(valuteIn)) {
                    checkValuteIn = true;
                    delegateExecution.setVariable("valuteInName", code.getName());
                }
            }else {
                checkValuteIn = true;
            }
            if (code.getCode().equals(valuteOut)) {
                checkValuteOut = true;
                delegateExecution.setVariable("valuteOutName", code.getName());
            }
        }
        if(valuteOut.equals(valuteIn)){
            delegateExecution.setVariable("checkvalute", "valute");
            delegateExecution.setVariable("check", false);
            delegateExecution.setVariable("status", true);
            return;
        }
        if(checkValuteIn && checkValuteOut){
            delegateExecution.setVariable("status", true);
            delegateExecution.setVariable("checkvalute", "ok");
            log.info("[CheckDataDelegate] end SUCCESS");
            return;
        }
        log.info("[CheckDataDelegate] end FAILD");
        delegateExecution.setVariable("status", false);
        delegateExecution.setVariable("checkvalute", "NO");
        delegateExecution.setVariable("check", false);
    }
}
