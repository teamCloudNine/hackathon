package com.hackathon.springboard.beneficiarycollaborationservice.controllers;

import com.hackathon.springboard.openapi.api.OrganizationsApi;
import com.hackathon.springboard.openapi.model.Organization;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrganizationsController implements OrganizationsApi {

  @Override
  public ResponseEntity<Organization> findOrganization(Integer id) {
    return null;
  }

  @Override
  public ResponseEntity<List<Organization>> listOrganizations() {
    return null;
  }

}