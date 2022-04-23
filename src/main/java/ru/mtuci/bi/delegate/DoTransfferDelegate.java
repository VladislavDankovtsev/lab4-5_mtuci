package ru.mtuci.bi.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DoTransfferDelegate implements JavaDelegate {

    Logger log = LoggerFactory.getLogger(DoTransfferDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("[DoTransfferDelegate] start");
        delegateExecution.setVariable("doTransffer", true);
    }
}
