package pl.fhframework.fhPersistence.conversation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.fhframework.aop.services.FhAspectsOrder;
import pl.fhframework.fhPersistence.core.EntityManagerRepository;

/**
 * @author Paweł Ruta
 */
@Aspect
@Component
@Order(FhAspectsOrder.CONVERSATION)
public class ConversationManagerAspect {
    @Autowired
    EntityManagerRepository entityManagerRepository;

    public void startNewConversation(Object owner) {
    }

    public void startSnapshot(Object owner) {
    }

    public void stop(Object owner) {
    }

    public void saveChnages(Object owner) {
        //conversationManager.approve(owner);
    }

    public void cancelChanges(Object owner) {
        //conversationManager.restoreSnapshot(owner);
    }

    @Around("@annotation(pl.fhframework.fhPersistence.anotation.WithoutConversation)")
    public Object withoutConversationCall(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            entityManagerRepository.turnOffConvesation(joinPoint.getTarget());
            return joinPoint.proceed();
        }
        finally {
            entityManagerRepository.turnOnConvesation(joinPoint.getTarget());
        }
    }
}

