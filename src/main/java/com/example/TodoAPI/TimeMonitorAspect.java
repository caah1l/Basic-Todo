package com.example.TodoAPI;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect  // to define that this class implements a logic of the annotation
@Component
public class TimeMonitorAspect {

    @Around("@annotation(TimeMonitor)") //to define on which annotation call does this below method should work
    //above expresion is called advise - to define when we want to execute it - @Around, @Before, @After

    public Object timeLogging(ProceedingJoinPoint joinPoint
    ) {
        System.out.println("Logging in time");
        long start = System.currentTimeMillis();  //start time

        //execution of join point
        Object result = null;
        try {
            result = joinPoint.proceed();  //this will trigger join point i.e, dosomething method in FakeTodoService1 class
        } catch (Throwable e) {
            System.out.println("something went wrong");
        } finally {
            long end = System.currentTimeMillis();
            long totalTime = end - start;
            System.out.println("Total execution time is " + totalTime + " ms..");
        }


        return result;
    }
}
