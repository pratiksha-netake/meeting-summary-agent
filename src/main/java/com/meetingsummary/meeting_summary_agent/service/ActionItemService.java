package com.meetingsummary.meeting_summary_agent.service;


import org.springframework.stereotype.Service;

import com.meetingsummary.meeting_summary_agent.dao.ActionItemRepository;
import com.meetingsummary.meeting_summary_agent.dao.MeetingNotesRepository;
import com.meetingsummary.meeting_summary_agent.dto.ActionItemRequest;
import com.meetingsummary.meeting_summary_agent.dto.ActionItemResponse;
import com.meetingsummary.meeting_summary_agent.model.ActionItem;
import com.meetingsummary.meeting_summary_agent.model.MeetingNotes;



@Service
public class ActionItemService {



    private final ActionItemRepository actionItemRepository;

    private final MeetingNotesRepository meetingNotesRepository;





    public ActionItemService(
            ActionItemRepository actionItemRepository,
            MeetingNotesRepository meetingNotesRepository
    ){

        this.actionItemRepository = actionItemRepository;

        this.meetingNotesRepository = meetingNotesRepository;

    }








    public ActionItemResponse createActionItem(
            ActionItemRequest request
    ){


        // Find meeting

        MeetingNotes meeting =
                meetingNotesRepository.findById(
                        request.getMeetingId()
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Meeting not found"
                        )
                );






        // Create Action Item

        ActionItem actionItem =
                new ActionItem(

                        meeting,

                        request.getTask(),

                        request.getAssignedTo()

                );






        // Save

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