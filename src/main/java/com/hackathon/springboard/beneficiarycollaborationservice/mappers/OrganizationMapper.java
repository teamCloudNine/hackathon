package com.hackathon.springboard.beneficiarycollaborationservice.mappers;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.models.NeedEntity;
import com.hackathon.springboard.beneficiarycollaborationservice.dao.models.OrganizationEntity;
import com.hackathon.springboard.openapi.model.Organization;
import com.hackathon.springboard.openapi.model.OrganizationCreationRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

  @Mapping(source = "entityId", target = "id")
  Organization organizationEntityToOrganization(OrganizationEntity organizationEntity);

  @Mapping(source = "id", target = "entityId")
  @Mapping(target = "entityType", constant = "Organization")
  OrganizationEntity organizationToOrganizationEntity(Organization organization);

  Organization organizationCreationRequestToOrganization(OrganizationCreationRequest organizationCreationRequest);

}
