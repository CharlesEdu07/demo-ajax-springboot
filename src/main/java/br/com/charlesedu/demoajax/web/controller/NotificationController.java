package br.com.charlesedu.demoajax.web.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class NotificationController {

    @GetMapping("/sale/notification")
    public SseEmitter sendNotification() throws IOException {
        SseEmitter emitter = new SseEmitter(0L);

        emitter.send("Olá mundo");

        return emitter;
    }
}
