package com.hackathon.springboard.beneficiarycollaborationservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.models.ActivityEntity;
import com.hackathon.springboard.openapi.model.Activity;
import com.hackathon.springboard.openapi.model.ActivityCreationRequest;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

  @Mapping(source = "entityId", target = "id")
  Activity activityEntityToActivity(ActivityEntity activityEntity);

  @Mapping(source = "id", target = "entityId")
  @Mapping(target = "entityType", constant = "Activity")
  ActivityEntity activityToActivityEntity(Activity activity);

  Activity activityCreationRequestToActivity(ActivityCreationRequest activityCreationRequest);
}
