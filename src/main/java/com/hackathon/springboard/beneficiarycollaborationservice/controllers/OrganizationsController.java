package com.hackathon.springboard.beneficiarycollaborationservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.springboard.beneficiarycollaborationservice.services.OrganizationService;
import com.hackathon.springboard.openapi.api.OrganizationsApi;
import com.hackathon.springboard.openapi.model.Organization;
import com.hackathon.springboard.openapi.model.OrganizationCreationRequest;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OrganizationsController implements OrganizationsApi {

  private final OrganizationService organizationService;

  @Override
  public ResponseEntity<Organization> createOrganization(OrganizationCreationRequest organization) {
    return ResponseEntity.ok(organizationService.createOrganization(organization));
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
