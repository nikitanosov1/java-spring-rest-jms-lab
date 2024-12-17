package lab234.aspect;

import lab234.model.Logging;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Topic;
import java.util.Arrays;

@Aspect
@Component
public class AspectLogging {
    private JmsTemplate jmsTemplate;
    private Topic topic;

    @Autowired
    public AspectLogging(JmsTemplate jmsTemplate) throws JMSException {
        this.jmsTemplate = jmsTemplate;

        this.topic = jmsTemplate.getConnectionFactory()
                .createConnection()
                .createSession()
                .createTopic("event");
    }

    @AfterReturning(
            value = "within(lab234.service.*) && @annotation(lab234.aspect.JmsLoggable)",
            returning = "returnValue"
    )
    public void performLogging(JoinPoint joinPoint, Object returnValue) throws JMSException {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        String entity = extractEntityName(className);
        String arguments = Arrays.toString(joinPoint.getArgs());
        String eventType = determineEventType(methodName);

        Logging logging = new Logging();
        logging.setEntity(entity);
        logging.setEventType(eventType);
        logging.setSubstance(arguments);

        jmsTemplate.convertAndSend(topic, logging);
    }

    private String extractEntityName(String className) {
        String[] packageParts = className.split("\\.");
        String lastPart = packageParts[packageParts.length - 1];
        return lastPart.split("Service")[0];
    }

    private String determineEventType(String methodName) {
        return switch (methodName) {
            case "create" -> "CREATE";
            case "update" -> "UPDATE";
            case "delete" -> "DELETE";
            default -> "";
        };
    }

}
