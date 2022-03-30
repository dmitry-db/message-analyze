package liga.medical.messageanalyzer.core.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.DeviceIdentificationDto;
import liga.medical.logging.annotation.Loggable;
import liga.medical.messageanalyzer.core.api.MedicalAnalyzerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@Slf4j
@Loggable
public class RabbitMqListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MedicalAnalyzerService medicalAnalyzerService;

    /**
     * Получаем информацию о сообщении.
     *
     * @param message сообщение
     */
    @RabbitListener(queues = "medical")
    public void processRabbitMessage(String message) {

        try {
            DeviceIdentificationDto deviceInfo =
                    objectMapper.readValue(message, DeviceIdentificationDto.class);
            medicalAnalyzerService.analyze(deviceInfo);
            System.out.println(deviceInfo);
        } catch (JsonProcessingException e) {
            log.info("Error while parsing incoming message {}", e.getMessage());
        }

        log.info("Получены данные пациента " + message);
    }
}