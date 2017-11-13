package epam_team1.service.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// TODO: Сделать нормально

@Aspect
@Component
public class ServiceLogger {
    public static final Logger logger = LoggerFactory.getLogger(ServiceLogger.class);


    @Around("@annotation(epam_team1.service.logger.EnableLogging)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(joinPoint.getSignature() + "-- started");
        Object proceed = joinPoint.proceed();
        logger.info(joinPoint.getSignature() + "-- finished");
        return proceed;
    }
    @AfterThrowing(
            pointcut="@annotation(epam_team1.service.logger.EnableLogging)",
            throwing="excep")
    public void afterThrowing(JoinPoint joinPoint, Throwable excep) throws Throwable {
        logger.error(joinPoint.getSignature() + "-- Exception: ");
        logger.error(excep.getMessage(),excep);
    }

}
