package br.com.charlesedu.demoajax.web.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import br.com.charlesedu.demoajax.domain.Emitter;
import br.com.charlesedu.demoajax.repository.SaleRepository;

@Controller
public class NotificationController {

    @Autowired
    private SaleRepository saleRepository;

    @GetMapping("/sale/notification")
    public SseEmitter sendNotification() throws IOException {
        SseEmitter sseEmitter = new SseEmitter(0L);

        Emitter emitter = new Emitter(sseEmitter, getLastSaleRegisterDate()); 

        emitter.getEmitter().send(emitter.getLastDate());

        return sseEmitter;
    }

    private LocalDateTime getLastSaleRegisterDate() {
        return saleRepository.findSaleWithLastDate();
    }
}
