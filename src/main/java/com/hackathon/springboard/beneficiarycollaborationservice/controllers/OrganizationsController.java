package com.hackathon.springboard.beneficiarycollaborationservice.controllers;

import com.hackathon.springboard.beneficiarycollaborationservice.services.OrganizationService;
import com.hackathon.springboard.openapi.api.OrganizationsApi;
import com.hackathon.springboard.openapi.model.Organization;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrganizationsController implements OrganizationsApi {

  private final OrganizationService organizationService;

  @Override
  public ResponseEntity<Organization> createOrganization(Organization organization) {
    return null;
  }

  @Override
  public ResponseEntity<Organization> findOrganization(String id) {
    return ResponseEntity.ok(organizationService.retrieveOrganizationById(String.valueOf(id)));
  }

  @Override
  public ResponseEntity<List<Organization>> listOrganizations(String needId) {
    return ResponseEntity.ok(organizationService.retrieveOrganizations());
  }

}
