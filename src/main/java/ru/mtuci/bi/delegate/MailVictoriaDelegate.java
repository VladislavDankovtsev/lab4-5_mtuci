package ru.mtuci.bi.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mtuci.bi.service.EmailService;

@Component
public class MailVictoriaDelegate implements JavaDelegate {

    @Autowired
    private EmailService emailService;
    Logger log = LoggerFactory.getLogger(MailDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("[MailDelegate] start");
        boolean check = (boolean) delegateExecution.getVariable("check");
        String email = String.valueOf(delegateExecution.getVariable("email"));
        String valuteOutName = String.valueOf(delegateExecution.getVariable("valuteOutName"));
        double resultValue = (double) delegateExecution.getVariable("resultValue");
        String checkvalute = String.valueOf(delegateExecution.getVariable("checkvalute"));
        String body = "";
        if(check){
            body = "Перевод осуществлен без ошибок "+ valuteOutName+" в размере:" + resultValue;
        }
        else {
            if (checkvalute.equals("No")) {
                body = "Ошибка в названии валюты";
            }
            if (checkvalute.equals("valute")) {
                body = "Информация о текущей валюте: " + valuteOutName;
            }
            if (checkvalute.equals("ok")) {
                body = "Перевод невозможен - сумма меньше 0";
            }
        }
        emailService.sendSimpleMessage(email,"Тестирование процесса",body);
        log.info("[MailDelegate] end SUCCESS");
    }
}
