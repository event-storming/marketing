package com.example.template;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    /**
     * 매일 2시에 추천상품저장 로직이 생성된다
     * *           *　　　　　　*　　　　　　*　　　　　　*　　　　　　*
     * 초(0-59)   분(0-59)　　시간(0-23)　　일(1-31)　　월(1-12)　　요일(0-7)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void cronJob1() {
        System.out.println("상품 추천 로직.... " );
    }

    /*
     * 매일 9시에 저장된 상품추천이 (recommendations) 메일로 발송된다.
     */
    @Scheduled(cron = "0 0 9 * * ?")
    public void cronJob2() {
        System.out.println("상품추천 메일 발송 " );
    }

}
