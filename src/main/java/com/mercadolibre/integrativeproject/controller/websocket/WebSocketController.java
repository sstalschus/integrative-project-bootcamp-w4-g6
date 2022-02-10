package com.mercadolibre.integrativeproject.controller.websocket;

import com.google.gson.Gson;
import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.MessageModel;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.security.Principal;
import java.util.Comparator;
import java.util.Map;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SectorService sectorService;

    @MessageMapping("/warn")
    public void sendMessage(MessageModel message) {
        message = getWarnMesage(message);
        simpMessagingTemplate.convertAndSend("/topic/notify", message);
    }

    private MessageModel getWarnMesage(MessageModel warnMesage) {
        Sector sector = sectorService.getById(warnMesage.getSectorId());
        if (warnMesage.getTemperature() != null) {
            sector.setTemperature(warnMesage.getTemperature());
            sectorService.updateTemperature(sector.getId(), sector.getTemperature());
            Batch batch = sector.getLots().stream().max(Comparator.comparingDouble(Batch::getMinimumTemperature)).orElse(null);
            if (batch != null) {
                warnMesage.setMinimumTemperatureNecessarie(batch.getMinimumTemperature());
                if (batch.getMinimumTemperature() < warnMesage.getTemperature()) {
                    warnMesage.setStatus("WARN");
                }else {
                    warnMesage.setStatus("OK");
                }
            }
        }
        return warnMesage;
    }
}