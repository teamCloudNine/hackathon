package com.hackathon.springboard.beneficiarycollaborationservice.services;

import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.ActivityDao;
import com.hackathon.springboard.beneficiarycollaborationservice.mappers.ActivityMapper;
import com.hackathon.springboard.openapi.model.Activity;
import com.hackathon.springboard.openapi.model.ActivityCreationRequest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

@Service
@AllArgsConstructor
@Slf4j
public class ActivityService {

  private final ActivityDao activityDao;
  private final ActivityMapper activityMapper;

  public Activity retrieveActivity(String id) {
    return activityMapper.activityEntityToActivity(activityDao.retrieve(id));
  }

  public List<Activity> retrieveAllActivities() {
    return activityDao.retrieveList(ScanEnhancedRequest
                                           .builder().build())
                         .stream()
                         .map(activityMapper::activityEntityToActivity)
                         .collect(Collectors.toList());
  }

  public Activity createActivity(ActivityCreationRequest activityCreationRequest) {
    Activity activity = activityMapper.activityCreationRequestToActivity(activityCreationRequest);
    String generatedUUID = new StringJoiner("-")
        .add("activity")
        .add(UUID
                 .randomUUID()
                 .toString())
        .toString();
    activity.setId(generatedUUID);
    activityDao.save(activityMapper.activityToActivityEntity(activity));
    log.debug("Generated activity UUID {}...", generatedUUID);
    activityDao.save(activityMapper.activityToActivityEntity(activity));
    return activity;
  }

}
