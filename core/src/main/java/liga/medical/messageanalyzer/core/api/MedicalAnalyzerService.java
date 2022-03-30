package liga.medical.messageanalyzer.core.api;

import liga.medical.DeviceIdentificationDto;

public interface MedicalAnalyzerService {

    /**
     * Анализ пациента и информирование ою ухудшении состояния.
     *
     * @param deviceInfo модель с данными о пациенте
     */
    void analyze(DeviceIdentificationDto deviceInfo);
}