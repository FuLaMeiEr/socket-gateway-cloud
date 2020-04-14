package com.naah.bullet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class BulletController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private static final Logger logger=LoggerFactory.getLogger(BulletController.class);

    @MessageMapping("/chat")
    @SendTo("/toAll/bulletScreen")
    public String say(BulletMessageDTO clientMessage) {
        if (clientMessage!=null){
            if (clientMessage.getMessage()!=null){
                clientMessage.setMessage(clientMessage.getMessage().trim());
            }
        }
        logger.info(clientMessage.getUsername()+":"+clientMessage.getMessage());
        return clientMessage.getMessage();
    }



}