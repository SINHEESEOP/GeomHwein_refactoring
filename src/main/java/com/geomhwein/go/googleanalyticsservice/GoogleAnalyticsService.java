//package com.geomhwein.go.googleanalyticsservice;
//
//
//import com.fasterxml.jackson.core.JsonFactory;
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
//import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
//import com.google.api.services.analyticsreporting.v4.model.DateRange;
//import com.google.api.services.analyticsreporting.v4.model.GetReportsRequest;
//import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
//import com.google.api.services.analyticsreporting.v4.model.Metric;
//import com.google.api.services.analyticsreporting.v4.model.ReportRequest;
//import org.springframework.stereotype.Service;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//@Service
//public class GoogleAnalyticsService {
//
//    private static final String VIEW_ID = "428194101";
//    private static final String KEY_FILE_LOCATION = "digital-hall-418708-2c99ccc44f4a.json";
//    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
//    private static final String APPLICATION_NAME = "Projectgogo";
//    
//    // Add this method to initialize AnalyticsReporting service
//    public AnalyticsReporting initializeAnalyticsReporting() throws GeneralSecurityException, IOException {
//        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//        GoogleCredential credential = GoogleCredential
//                .fromStream(new FileInputStream(KEY_FILE_LOCATION))
//                .createScoped(AnalyticsReportingScopes.all());
//
//        // Construct the Analytics Reporting service object.
//        return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, credential)
//                .setApplicationName(APPLICATION_NAME).build();
//    }
//    
//    
//
//    public GetReportsResponse getDailyVisitorsReport(AnalyticsReporting service) throws IOException {
//        // 오늘 날짜 설정
//        LocalDate today = LocalDate.now().minusDays(1); // 어제
//
//        // 어제 날짜 설정
//        LocalDate yesterday = LocalDate.now().minusDays(2); // 그저께
//
//        // Create the DateRange object with start and end dates
//        DateRange dateRange = new DateRange()
//                .setStartDate(yesterday.toString())
//                .setEndDate(today.toString());
//
//        // Create the Metrics object to get sessions (visitors)
//        Metric sessions = new Metric()
//                .setExpression("ga:sessions")
//                .setAlias("sessions");
//
//        // Create the ReportRequest object
//        ReportRequest request = new ReportRequest()
//                .setViewId(VIEW_ID) // Google Analytics View ID 설정
//                .setDateRanges(Arrays.asList(dateRange))
//                .setMetrics(Arrays.asList(sessions));
//
//        ArrayList<ReportRequest> requests = new ArrayList<>();
//        requests.add(request);
//
//        // Create the GetReportsRequest object
//        GetReportsRequest getReport = new GetReportsRequest()
//                .setReportRequests(requests);
//
//        // Call the batchGet method
//        return service.reports().batchGet(getReport).execute();
//    }
//}