package com.meetingsummary.meeting_summary_agent.controller;



import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meetingsummary.meeting_summary_agent.dto.ActionItemRequest;
import com.meetingsummary.meeting_summary_agent.dto.ActionItemResponse;
import com.meetingsummary.meeting_summary_agent.service.ActionItemService;

@RestController
@RequestMapping("/api/action-items")
@CrossOrigin("*")
public class ActionItemController {



    private final ActionItemService service;



    public ActionItemController(
            ActionItemService service){

        this.service=service;

    }



    @PostMapping("/assign")
    public ResponseEntity<ActionItemResponse> assign(

            @Valid @RequestBody ActionItemRequest request){


        return ResponseEntity.ok(
                service.createActionItem(request)
        );

    }



    @PutMapping("/{id}/status")
    public ResponseEntity<ActionItemResponse> updateStatus(

            @PathVariable Long id,

            @RequestParam String status){


        return ResponseEntity.ok(
                service.updateStatus(id,status)
        );

    }

}



