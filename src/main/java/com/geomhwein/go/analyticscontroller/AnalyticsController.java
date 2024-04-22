//package com.geomhwein.go.analyticscontroller;
//
//import com.geomhwein.go.googleanalyticsservice.GoogleAnalyticsService;
//import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
//import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//
//@RestController
//@RequestMapping("/analytics")
//public class AnalyticsController {
//
//    private final GoogleAnalyticsService googleAnalyticsService;
//
//    @Autowired
//    public AnalyticsController(GoogleAnalyticsService googleAnalyticsService) {
//        this.googleAnalyticsService = googleAnalyticsService;
//    }
//
//    @GetMapping("/dailyVisitors")
//    public GetReportsResponse getDailyVisitors() {
//        try {
//            // Google Analytics 서비스 초기화
//            AnalyticsReporting service = googleAnalyticsService.initializeAnalyticsReporting();
//
//            // 일일 방문자 수 가져오기
//            return googleAnalyticsService.getDailyVisitorsReport(service);
//        } catch (IOException | GeneralSecurityException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}