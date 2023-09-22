package com.hackathon.springboard.beneficiarycollaborationservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.springboard.beneficiarycollaborationservice.services.NeedService;
import com.hackathon.springboard.openapi.api.NeedsApi;
import com.hackathon.springboard.openapi.model.Need;
import com.hackathon.springboard.openapi.model.NeedCreationRequest;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class NeedController implements NeedsApi {

  private final NeedService needService;

  public ResponseEntity<Need> createNeed(NeedCreationRequest needCreationRequest) {
    return ResponseEntity.ok(needService.createNeed(needCreationRequest));
  }

  public ResponseEntity<Need> findNeed(String id) {
    return ResponseEntity.ok(needService.retrieveNeed(String.valueOf(id)));
  }

  public ResponseEntity<List<Need>> listNeeds() {
    return ResponseEntity.ok(needService.retrieveAllNeeds());
  }

}
