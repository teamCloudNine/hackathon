package com.hackathon.springboard.beneficiarycollaborationservice.mappers;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.models.ActivityEntity;
import com.hackathon.springboard.openapi.model.Activity;
import com.hackathon.springboard.openapi.model.ActivityCreationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

  @Mapping(source = "entityId", target = "id")
  @Mapping(source = "activityStatus", target = "status")
  Activity activityEntityToActivity(ActivityEntity activityEntity);

  @Mapping(source = "id", target = "entityId")
  @Mapping(target = "entityType", constant = "Activity")
  @Mapping(source = "status", target = "activityStatus")
  ActivityEntity activityToActivityEntity(Activity activity);

  Activity activityCreationRequestToActivity(ActivityCreationRequest activityCreationRequest);
}
