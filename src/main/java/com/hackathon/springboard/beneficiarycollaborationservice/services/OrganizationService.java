package com.hackathon.springboard.beneficiarycollaborationservice.services;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.OrganizationDao;
import com.hackathon.springboard.beneficiarycollaborationservice.mappers.OrganizationMapper;
import com.hackathon.springboard.openapi.model.Organization;
import com.hackathon.springboard.openapi.model.OrganizationCreationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrganizationService {

  private final OrganizationDao organizationDao;
  private final OrganizationMapper organizationMapper;

  public Organization retrieveOrganizationById(String id) {
    return organizationMapper.organizationEntityToOrganization(organizationDao.retrieve(id));
  }

  public List<Organization> retrieveOrganizations() {
    return organizationDao.retrieveList(ScanEnhancedRequest.builder().build())
        .stream()
        .map(organizationMapper::organizationEntityToOrganization)
        .collect(Collectors.toList());
  }

public Organization createOrganization(OrganizationCreationRequest organization) {
  Organization org = organizationMapper.organizationCreationRequestToOrganization(organization);
    String generatedUUID = new StringJoiner("-")
        .add("organization")
        .add(UUID
                 .randomUUID()
                 .toString())
        .toString();
    org.setId(generatedUUID);
    log.debug("Generated org UUID {}...", generatedUUID);
    organizationDao.save(organizationMapper.organizationToOrganizationEntity(org));
    return org;
}

}
