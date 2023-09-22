package com.hackathon.springboard.beneficiarycollaborationservice.mappers;

import com.hackathon.springboard.beneficiarycollaborationservice.constants.EntityConstants;
import com.hackathon.springboard.beneficiarycollaborationservice.dao.models.NeedEntity;
import com.hackathon.springboard.openapi.model.Need;
import com.hackathon.springboard.openapi.model.NeedCreationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NeedMapper {

  @Mapping(source = "entityId", target = "id")
  Need needEntityToNeed(NeedEntity needEntity);

  @Mapping(source = "id", target = "entityId")
  @Mapping(target = "entityType", constant = EntityConstants.ACTIVITY_ENTITY_TYPE)
  NeedEntity needToNeedEntity(Need need);

  Need needCreationRequestToNeed(NeedCreationRequest needCreationRequest);

}
