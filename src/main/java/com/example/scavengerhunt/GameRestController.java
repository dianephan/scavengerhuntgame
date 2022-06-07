package com.example.scavengerhunt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class GameRestController {
    // NOTE TO SELF: YOU HAVE TO GO TO THIS URL ENDPOINT IN ORDER FOR THE TEXT TO SEND
    @PostMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS() {
        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));

        Message.creator(
                new com.twilio.type.PhoneNumber("+to"),    // to
                new com.twilio.type.PhoneNumber("+fro"),    // from
                "We have a winner!").create();

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }

//    @PostMapping(value="/sms", produces="application/xml")
//    public String sendTwiML(@RequestParam("Body") String msg) {
//        String responseMsg = "https://XXXXXXXXX.ngrok.io/game";    // replace with your link here
//        Body body = new Body
//                .Builder(responseMsg)
//                .build();
//        Message sms = new Message
//                .Builder()
//                .body(body)
//                .build();
//        MessagingResponse twiml = new MessagingResponse
//                .Builder()
//                .message(sms)
//                .build();
//        return twiml.toXml();
//    }
}
