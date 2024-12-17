package lab234.jms;

import lab234.model.EmailNotification;
import lab234.model.Logging;
import lab234.repo.EmailNotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailReceiver {

    @Autowired
    private EmailNotificationRepository emailNotificationRepository;

    @JmsListener(destination = "event", containerFactory = "myFactory")
    public void receiveAndProcessEvent(Logging event) {
        String message = formatEventMessage(event);
        saveEmailNotification(message);
        logEventReceived(message);
    }

    private String formatEventMessage(Logging event) {
        return String.format("Action performed: %s on entity type: %s",
                event.getEventType(), event.getEntity());
    }

    private void saveEmailNotification(String message) {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.setAddress("admin");
        emailNotification.setContent(message);
        emailNotificationRepository.save(emailNotification);
    }

    private void logEventReceived(String message) {
        log.info("Получено уведомление: {}", message);
    }
}
