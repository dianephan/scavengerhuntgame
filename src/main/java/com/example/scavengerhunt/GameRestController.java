package com.example.scavengerhunt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class GameRestController {
    // NOTE TO SELF: YOU HAVE TO GO TO THIS URL ENDPOINT IN ORDER FOR THE TEXT TO SEND
    @PostMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS() {
        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));
        String TWILIO_PHONE = System.getenv("TWILIO_PHONE");
        String YOUR_PHONE_NUMBER = System.getenv("YOUR_PHONE_NUMBER");
        Message.creator(
                new com.twilio.type.PhoneNumber(YOUR_PHONE_NUMBER),
                new com.twilio.type.PhoneNumber(TWILIO_PHONE),
                "We have a winner!").create();
        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }

    @PostMapping(value="/sms", produces = "application/xml")
//    public String sendTwiML(@RequestParam("Body") String msg) {
    public ResponseEntity<String> sendTwiML(@RequestParam("Body") String msg, @RequestParam(value = "From", required = false) String incomingCallerNumber) {
        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));
        String TWILIO_PHONE = System.getenv("TWILIO_PHONE");
        String responseMsg = "https://XXXXXXXX.ngrok.io/game";
        String RECIPIENT_NUM = incomingCallerNumber;
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(RECIPIENT_NUM),
                        new com.twilio.type.PhoneNumber(TWILIO_PHONE),
                        responseMsg)
                .create();
        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}
