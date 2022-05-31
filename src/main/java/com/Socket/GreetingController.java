package com.Socket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;


import static com.Socket.SpringWebsocketApplication.val;
import static com.demo.ApirestApplication.knx;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greet(HelloMessage message) throws InterruptedException {
        System.out.println("recu :"+message.getName());
        return new Greeting("Hello, " +
                HtmlUtils.htmlEscape(message.getName()));
    }


    @MessageMapping("/knx")
    @SendTo("/topic/greetings")
    public Greeting test(HelloMessage message) throws InterruptedException {
        System.out.println("recu :"+message.getName());
        return new Greeting("knx, " +
                HtmlUtils.htmlEscape(message.getName()));
    }

    @MessageMapping("/knx/work")
    @SendTo("/topic/greetings")
    public Greeting testons(HelloMessage message) throws InterruptedException {
        //System.out.println("recu :"+message.getName());
        Thread.sleep(10);
        return new Greeting(
                HtmlUtils.htmlEscape(knx.Getstate()));
    }
}

