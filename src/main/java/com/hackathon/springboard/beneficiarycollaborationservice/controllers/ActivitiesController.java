package com.hackathon.springboard.beneficiarycollaborationservice.controllers;

import com.hackathon.springboard.openapi.api.ActivitiesApi;
import com.hackathon.springboard.openapi.model.Activity;
import com.hackathon.springboard.openapi.model.ActivityCreationRequest;
import com.hackathon.springboard.openapi.model.ActivityStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActivitiesController implements ActivitiesApi {

  @Override
  public ResponseEntity<Activity> createActivity(ActivityCreationRequest activityCreationRequest) {
    return null;
  }

  @Override
  public ResponseEntity<Activity> findActivity(Integer id) {
    return null;
  }

  @Override
  public ResponseEntity<List<Activity>> listActivities(
      ActivityStatus status, Integer organizationId, Integer beneficiaryId, Integer needId
  ) {
    return null;
  }

}
