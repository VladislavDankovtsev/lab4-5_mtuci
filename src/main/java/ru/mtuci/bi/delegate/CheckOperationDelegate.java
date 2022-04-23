package ru.mtuci.bi.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CheckOperationDelegate implements JavaDelegate {

    Logger log = LoggerFactory.getLogger(CheckOperationDelegate.class);

    private static List<String> operasionsList = new ArrayList<>();

    static {
        operasionsList.add("CASH");
        operasionsList.add("TRANSFFER");
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("[CheckOperationDelegate] start");
        try {
            String operation = String.valueOf(delegateExecution.getVariable("operation"));
            for (String s : operasionsList) {
                if(s.equals(operation));
            }
            delegateExecution.setVariable("status", true);
            log.info("[CheckOperationDelegate] end SUCCESS");
        }catch (Exception e){
            log.error("Выбранной операции не существует");
            delegateExecution.setVariable("status", false);
            log.info("[CheckOperationDelegate] end FAIL");
        }
    }
}
