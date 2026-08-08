package com.meetingsummary.meeting_summary_agent.service;




import org.springframework.stereotype.Service;

import com.meetingsummary.meeting_summary_agent.dao.ActionItemRepository;

import com.meetingsummary.meeting_summary_agent.dto.ActionItemRequest;
import com.meetingsummary.meeting_summary_agent.dto.ActionItemResponse;
import com.meetingsummary.meeting_summary_agent.model.ActionItem;
import com.meetingsummary.meeting_summary_agent.model.Meeting;




@Service
public class ActionItemService {



    private final ActionItemRepository actionItemRepository;

    private final MeetingService meetingService;





    public ActionItemService(
            ActionItemRepository actionItemRepository,
            MeetingService meetingService
    ){

        this.actionItemRepository = actionItemRepository;

        this.meetingService = meetingService;

    }


    public ActionItemResponse createActionItem(
            ActionItemRequest request
    ){


        // Find meeting

        Meeting meeting =
        		meetingService.getMeeting(
                        request.getMeetingId()
                );
                





      

        ActionItem actionItem =
                new ActionItem(

                        meeting,

                        request.getTask(),

                        request.getAssignedTo()

                );

       

        ActionItem saved =
                actionItemRepository.save(actionItem);

        return new ActionItemResponse(

                saved.getId(),

                saved.getTask(),

                saved.getAssignedTo(),

                saved.getStatus(),

                saved.getCreatedAt()

        );

    }


    public ActionItemResponse updateStatus(
            Long id,
            String status
    ){


        ActionItem actionItem =
                actionItemRepository.findById(id)

                .orElseThrow(() ->
                        new RuntimeException(
                                "Action item not found"
                        )
                );


        actionItem.setStatus(status);

        ActionItem updated =
                actionItemRepository.save(actionItem);


        return new ActionItemResponse(

                updated.getId(),

                updated.getTask(),

                updated.getAssignedTo(),

                updated.getStatus(),

                updated.getCreatedAt()

        );


    }



}