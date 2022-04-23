package ru.mtuci.bi.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mtuci.bi.service.EmailService;


@Component
public class MailDelegate implements JavaDelegate {

    @Autowired
    private EmailService emailService;
    Logger log = LoggerFactory.getLogger(MailDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("[MailDelegate] start");
        String email = String.valueOf(delegateExecution.getVariable("email"));
        String operation = String.valueOf(delegateExecution.getVariable("operation"));
        String valuteOutName = String.valueOf(delegateExecution.getVariable("valuteOutName"));
        String resultValue = String.valueOf(delegateExecution.getVariable("resultValue"));
        String body = "Электронное уведомление о переводе валюты: "+ valuteOutName+" в размере:" + resultValue;
        if(operation.equals("CASH")) {
            body = "Электронное уведомление о получение валюты: "+ valuteOutName+" в размере:" + resultValue;
        }
        emailService.sendSimpleMessage(email,"Тестирование процесса",body);
        log.info("[MailDelegate] end SUCCESS");
    }
}
