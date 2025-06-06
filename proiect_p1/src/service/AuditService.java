package service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditService {
    private static final String FILE_NAME = "audit.csv";
    private static AuditService instance = null;

    private AuditService() {}

    public static AuditService getInstance() {
        if (instance == null)
            instance = new AuditService();
        return instance;
    }

    public void log(String actiune) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.append(actiune).append(",").append(LocalDateTime.now().toString()).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
