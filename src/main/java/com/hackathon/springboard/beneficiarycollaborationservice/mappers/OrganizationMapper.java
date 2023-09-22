package com.hackathon.springboard.beneficiarycollaborationservice.mappers;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.models.OrganizationEntity;
import com.hackathon.springboard.openapi.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

  @Mapping(source = "entityId", target = "id")
  Organization organizationEntityToOrganization(OrganizationEntity organizationEntity);

}
