package br.com.charlesedu.demoajax.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class Emitter {
    private String id = UUID.randomUUID().toString();
    private SseEmitter emitter;
    private LocalDateTime lastDate;

    public Emitter(SseEmitter emitter, LocalDateTime lastDate) {
        this.emitter = emitter;
        this.lastDate = lastDate;
    }

    public String getId() {
        return id;
    }

    public SseEmitter getEmitter() {
        return emitter;
    }

    public void setEmitter(SseEmitter emitter) {
        this.emitter = emitter;
    }

    public LocalDateTime getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDateTime lastDate) {
        this.lastDate = lastDate;
    }
}
