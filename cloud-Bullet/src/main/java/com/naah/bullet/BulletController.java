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

    private static final Logger logger=LoggerFactory.getLogger(BulletController.class);
    @MessageMapping("/chat")
    @SendTo("/toAll/bulletScreen")
    public String say(BulletMessageDTO clientMessage) {
        //方法用于广播测试
        if (clientMessage!=null){
            if (clientMessage.getMessage()!=null){
                clientMessage.setMessage(clientMessage.getMessage().trim());
            }
        }
        logger.info(clientMessage.getUsername()+":"+clientMessage.getMessage());
        return clientMessage.getMessage();
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

}