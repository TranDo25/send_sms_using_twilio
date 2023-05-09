package service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import model.SmsPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Slf4j
@Service
public class SmsService {

    @Value("${TWILIO_ACCOUNT_SID}")
    String ACCOUNT_SID;
    @Value("${TWILIO_AUTH_TOKEN}")
    String AUTH_TOKEN;
    @Value("${TWILIO_OUTGOING_SMS_NUMBER}")
    String OUTGOING_SMS_NUMBER;
//    private final String ACCOUNT_SID ="AC33e5425c7296546c86c2f7da2b85940d";

//    private final String AUTH_TOKEN = "1dee6543c1164f056ee585bf06d5ea3c";

    //    private final String FROM_NUMBER = "+16206088973";
    public SmsService() {
        log.info("Creating class SMSService");
        log.info("ACCOUNT_SID: " + ACCOUNT_SID);
    }

    //    public void send(SmsPojo sms) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
//                .create();
//        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction
//
//    }
    @PostConstruct
    private void setup() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }

    public String sendSMS(String smsNumber, String smsMessage) {
        Message message = Message.creator(
                new PhoneNumber(smsNumber),
                new PhoneNumber(OUTGOING_SMS_NUMBER),
                smsMessage
        ).create();
        return message.getStatus().toString();
    }
}
