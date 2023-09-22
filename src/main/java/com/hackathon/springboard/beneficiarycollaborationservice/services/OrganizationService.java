package com.hackathon.springboard.beneficiarycollaborationservice.services;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.OrganizationDao;
import com.hackathon.springboard.beneficiarycollaborationservice.mappers.OrganizationMapper;
import com.hackathon.springboard.openapi.model.Organization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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

}
