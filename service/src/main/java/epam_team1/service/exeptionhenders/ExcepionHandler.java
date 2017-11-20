package epam_team1.service.exeptionhenders;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.Exception;

@Aspect
@Component
public class ExcepionHandler {

    @Around("@annotation(Exception)")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = null;

        try {
            proceed = joinPoint.proceed();
        }catch (Exception e){

        }
        return proceed;
    }

}