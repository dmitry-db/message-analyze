package liga.medical.messageanalyzer.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.MedicalData;

public class ModelConverter {

    private static final double NORMAL_TEMPERATURE = 36.6;

    /**
     * @param data модель с данными для преобразования в сообщение.
     * @return сообщение с данными пациента
     */
    public static String convertToMessage(Object data) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Проверяем болен ли пациент.
     *
     * @param medicalData данные о пациенте
     * @return болен или нет
     */
    public static boolean isPatientIll(MedicalData medicalData) {
        Double temperature = medicalData.getTemperature();
        return NORMAL_TEMPERATURE < temperature;
    }
}
