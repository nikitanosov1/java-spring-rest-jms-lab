package lab234.jms;

import lab234.model.Logging;
import lab234.repo.LoggingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventReceiver {

    @Autowired
    private LoggingRepository loggingRepository;

    @JmsListener(destination = "event", containerFactory = "myFactory")
    public void receive(Logging event) {
        loggingRepository.save(event);
        log.info("Получен event: {}", event);
    }
}
