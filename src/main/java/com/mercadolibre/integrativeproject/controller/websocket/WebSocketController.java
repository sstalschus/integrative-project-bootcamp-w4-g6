package com.mercadolibre.integrativeproject.controller.websocket;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.MessageModel;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
/** Controller WebSocket
 *
 * @author Arthur Amorim
 *
 * */
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SectorService sectorService;

    /** Método usado para alterar a temperatura de um Setor
     * e publicar esta alteracao no topico de notificacao.
     *
     * @param message - Mensagem recebida por um cliente via WebSocket
     *
     * @author Arthur Amorim
     *
     *
     * */
    @MessageMapping("/warn")
    public void sendMessage(MessageModel message) {
        message = updateTemperature(message);
        simpMessagingTemplate.convertAndSend("/topic/notify", message);
    }

    /** Método usado para alterar a temperatura de um Setor
     * e publicar esta alteracao no topico de notificacao.
     *
     * @param warnMesage - Mensagem recebida por um cliente via WebSocket
     *
     * @author Arthur Amorim
     *
     *
     * */
    private MessageModel updateTemperature(MessageModel warnMesage) {
        Sector sector = sectorService.getById(warnMesage.getSectorId());
        if (warnMesage.getTemperature() != null) {
            updateTemperatureOnSector(warnMesage, sector);
            Batch batch = sector.getLots().stream().max(Comparator.comparingDouble(Batch::getMinimumTemperature)).orElse(null);
            setStatusMessage(warnMesage, batch);
        }
        return warnMesage;
    }

    /** Método usado para alterar a temperatura de um Setor
     * e publicar esta alteracao no topico de notificacao.
     *
     * @param warnMesage - Mensagem com informacoes sobre a atualizacao
     *
     * @author Arthur Amorim
     *
     *
     * */
    private void updateTemperatureOnSector(MessageModel warnMesage, Sector sector) {
        sector.setTemperature(warnMesage.getTemperature());
        sectorService.updateTemperature(sector.getId(), sector.getTemperature());
    }

    /** Método usado para verificar se a nova temperatura ira manter os lotes
     * preservados
     *
     * @param warnMesage - Mensagem com informacoes sobre a atualizacao.
     * @param batch - Loque com a menor temperatura do respectivo setor
     *
     * @author Arthur Amorim
     *
     *
     * */
    private void setStatusMessage(MessageModel warnMesage, Batch batch) {
        if (batch != null) {
            warnMesage.setMinimumTemperatureNecessarie(batch.getMinimumTemperature());
            if (batch.getMinimumTemperature() < warnMesage.getTemperature()) {
                warnMesage.setStatus("WARN");
            }else {
                warnMesage.setStatus("OK");
            }
        }
    }
}