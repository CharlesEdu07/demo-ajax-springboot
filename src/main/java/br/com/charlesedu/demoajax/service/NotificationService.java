package br.com.charlesedu.demoajax.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import br.com.charlesedu.demoajax.domain.Emitter;
import br.com.charlesedu.demoajax.repository.SaleRepository;

@EnableScheduling
@Service
public class NotificationService {

    @Autowired
    private SaleRepository saleRepository;

    private CopyOnWriteArrayList<Emitter> emitters = new CopyOnWriteArrayList<>();

    public void onOpen(Emitter emitter) throws IOException {
        emitter.getEmitter().send(SseEmitter.event().data(" ").id(emitter.getId()));
    }

    @Scheduled(fixedRate = 60000)
    public void notifyClient() {
        List<Emitter> emittersErrors = new ArrayList<>();

        this.emitters.forEach(emitter -> {
            try {
                Map<String, Object> map = saleRepository.countAndMaxNewSaleByRegisterDate(emitter.getLastDate());

                long count = (long) map.get("count");

                if (count > 0) {
                    emitter.setLastDate((LocalDateTime) map.get("lastDate"));

                    emitter.getEmitter().send(SseEmitter.event().data(count).id(emitter.getId()));
                }
            } catch (IOException e) {
                emittersErrors.add(emitter);
            }
        });

        this.emitters.removeAll(emittersErrors);
    }

    public void addEmitter(Emitter emitter) {
        this.emitters.add(emitter);
    }

    public void removeEmitter(Emitter emitter) {
        this.emitters.remove(emitter);
    }

    public CopyOnWriteArrayList<Emitter> getEmitters() {
        return emitters;
    }
}
