package com.hackathon.springboard.beneficiarycollaborationservice.mappers;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.models.ActivityEntity;
import com.hackathon.springboard.openapi.model.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

  @Mapping(source = "entityId", target = "id")
  Activity activityEntityToActivity(ActivityEntity activityEntity);

}
