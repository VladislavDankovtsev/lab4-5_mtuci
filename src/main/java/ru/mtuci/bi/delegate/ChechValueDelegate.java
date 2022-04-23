package ru.mtuci.bi.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ChechValueDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        double value = (double) delegateExecution.getVariable("value");
        if(value>0){
            delegateExecution.setVariable("check",true);
        }else {
            delegateExecution.setVariable("check",false);
        }
    }
}
