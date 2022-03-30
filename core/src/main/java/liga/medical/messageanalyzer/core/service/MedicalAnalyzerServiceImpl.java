package liga.medical.messageanalyzer.core.service;

import liga.medical.DeviceIdentificationDto;
import liga.medical.MedicalData;
import liga.medical.logging.annotation.Loggable;
import liga.medical.messageanalyzer.core.api.MedicalAnalyzerService;
import liga.medical.messageanalyzer.core.constants.ApplicationConstants;
import liga.medical.messageanalyzer.core.utils.ModelConverter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalAnalyzerServiceImpl implements MedicalAnalyzerService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void analyze(DeviceIdentificationDto deviceInfo) {
        if (deviceInfo == null) return;
        MedicalData medicalData = deviceInfo.getMedicalData();
        boolean patientIll = ModelConverter.isPatientIll(medicalData);
        if (patientIll) {
            String message = ModelConverter.convertToMessage(deviceInfo);
            amqpTemplate.convertAndSend(ApplicationConstants.PATIENT_ALERT.getMedicalQueue(), message);
        }

    }
}
