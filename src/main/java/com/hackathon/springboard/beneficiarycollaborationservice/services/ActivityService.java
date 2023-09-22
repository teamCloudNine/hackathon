package com.hackathon.springboard.beneficiarycollaborationservice.services;

import com.hackathon.springboard.beneficiarycollaborationservice.constants.EntityConstants;
import com.hackathon.springboard.beneficiarycollaborationservice.dao.ActivityDao;
import com.hackathon.springboard.beneficiarycollaborationservice.mappers.ActivityMapper;
import com.hackathon.springboard.openapi.model.Activity;
import com.hackathon.springboard.openapi.model.ActivityCreationRequest;
import com.hackathon.springboard.openapi.model.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ActivityService {

  private final ActivityDao activityDao;
  private final ActivityMapper activityMapper;

  public Activity retrieveActivity(String id) {
    return activityMapper.activityEntityToActivity(activityDao.retrieve(id));
  }

  public List<Activity> retrieveAllActivities(ActivityStatus status, String organizationId, String beneficiaryId, String needId) {
    Map<String, AttributeValue> expressionValues = new HashMap<>();
    expressionValues
        .put(":val", AttributeValue
            .builder()
            .s(EntityConstants.ACTIVITY_ENTITY_TYPE)
            .build());

    return activityDao
        .retrieveList(ScanEnhancedRequest
                          .builder()
                          .filterExpression(
                              Expression
                                  .builder()
                                  .expression("entityType = :val")
                                  .expressionValues(expressionValues)
                                  .build())
                          .build())
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
