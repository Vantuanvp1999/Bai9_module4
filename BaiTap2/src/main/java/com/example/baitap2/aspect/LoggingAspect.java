package com.example.baitap2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class LoggingAspect {
    private final AtomicInteger counter = new AtomicInteger(0);

    @Before("execution(* com.example.baitap2.controller..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        int count = counter.incrementAndGet();
        System.out.println("Lượt ghé thăm#"+count+" lúc"+ LocalDateTime.now());
        System.out.println("Đã truy cập vào: "+joinPoint.getSignature().getName());
    }
    @After("execution(* com.example.baitap2.service.BookService.borrowBook(..)) ||"+
    "execution(* com.example.baitap2.service.BookService.returnBook(..))")
    public void logBookChange(JoinPoint joinPoint){
        String action = joinPoint.getSignature().getName().equals("borrowBook") ? "Mượn" : "Trả";
        System.out.println("Thực hiện hành động " + action);
        System.out.println("Thông tin: " +joinPoint.getSignature());
    }
    @AfterThrowing(pointcut = "execution(* com.example.baitap2.controller..*(..))",throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.err.println("Lỗi tại"+joinPoint.getSignature());
        System.err.println("thông điệp lỗi " + ex.getMessage());

    }
}
