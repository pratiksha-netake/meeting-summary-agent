package com.meetingsummary.meeting_summary_agent.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meetingsummary.meeting_summary_agent.dto.DashboardStatisticsResponse;
import com.meetingsummary.meeting_summary_agent.service.DashboardStatisticsService;



@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardStatisticsController {



    private final DashboardStatisticsService service;

    public DashboardStatisticsController(
            DashboardStatisticsService service
    ){

        this.service = service;

    }



    @GetMapping("/statistics")
    public ResponseEntity<DashboardStatisticsResponse> statistics(){


        return ResponseEntity.ok(
                service.getStatistics()
        );

    }

}