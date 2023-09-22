package com.hackathon.springboard.beneficiarycollaborationservice.controllers;

import com.hackathon.springboard.beneficiarycollaborationservice.services.ActivityService;
import com.hackathon.springboard.openapi.api.ActivitiesApi;
import com.hackathon.springboard.openapi.model.Activity;
import com.hackathon.springboard.openapi.model.ActivityCreationRequest;
import com.hackathon.springboard.openapi.model.ActivityStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ActivitiesController implements ActivitiesApi {

  private final ActivityService activityService;

  @Override
  public ResponseEntity<Activity> createActivity(ActivityCreationRequest activityCreationRequest) {
    return ResponseEntity.ok(activityService.createActivity(activityCreationRequest));
  }

  @Override
  public ResponseEntity<Activity> findActivity(String id) {
    return ResponseEntity.ok(activityService.retrieveActivity(String.valueOf(id)));
  }

  @Override
  public ResponseEntity<List<Activity>> listActivities(
      ActivityStatus status, String organizationId, String beneficiaryId, String needId
  ) {
    return ResponseEntity.ok(activityService.retrieveAllActivities(status, organizationId, beneficiaryId, needId));
  }

}
