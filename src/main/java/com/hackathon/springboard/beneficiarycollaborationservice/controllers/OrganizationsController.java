package com.hackathon.springboard.beneficiarycollaborationservice.controllers;

import com.hackathon.springboard.openapi.api.OrganizationsApi;
import com.hackathon.springboard.openapi.model.Organization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrganizationsController implements OrganizationsApi {

  @Override
  public ResponseEntity<Organization> createOrganization(Organization organization) {
    return null;
  }

  @Override
  public ResponseEntity<Organization> findOrganization(Integer id) {
    return null;
  }

  @Override
  public ResponseEntity<List<Organization>> listOrganizations(Integer needId) {
    return null;
  }

}
