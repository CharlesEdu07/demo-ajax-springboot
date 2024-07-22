package br.com.charlesedu.demoajax.web.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import br.com.charlesedu.demoajax.domain.Emitter;
import br.com.charlesedu.demoajax.repository.SaleRepository;
import br.com.charlesedu.demoajax.service.NotificationService;

@Controller
public class NotificationController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/sale/notification")
    public SseEmitter sendNotification() throws IOException {
        SseEmitter sseEmitter = new SseEmitter(0L);

        Emitter emitter = new Emitter(sseEmitter, getLastSaleRegisterDate());

        notificationService.onOpen(emitter);
        notificationService.addEmitter(emitter);

        emitter.getEmitter().onCompletion(() -> notificationService.removeEmitter(emitter));

        System.out.println("> size after add: " + notificationService.getEmitters().size());

        return emitter.getEmitter();
    }

    private LocalDateTime getLastSaleRegisterDate() {
        return saleRepository.findSaleWithLastDate();
    }
}
