package com.hackathon.springboard.beneficiarycollaborationservice.services;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.ActivityDao;
import com.hackathon.springboard.beneficiarycollaborationservice.mappers.ActivityMapper;
import com.hackathon.springboard.openapi.model.Activity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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

}
