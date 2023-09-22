package com.hackathon.springboard.beneficiarycollaborationservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.springboard.beneficiarycollaborationservice.services.BeneficiaryService;
import com.hackathon.springboard.openapi.api.BeneficiariesApi;
import com.hackathon.springboard.openapi.model.Beneficiary;
import com.hackathon.springboard.openapi.model.BeneficiaryCreationRequest;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BeneficiariesController implements BeneficiariesApi {

  private final BeneficiaryService beneficiaryService;

  @Override
  public ResponseEntity<Beneficiary> createBeneficiary(BeneficiaryCreationRequest beneficiaryCreationRequest) {
    return ResponseEntity.ok(beneficiaryService.creatBeneficiary(beneficiaryCreationRequest));
  }

  @Override
  public ResponseEntity<Beneficiary> findBeneficiary(String id) {
    return ResponseEntity.ok(beneficiaryService.retrieveBeneficiary(id));
  }

  @Override
  public ResponseEntity<List<Beneficiary>> listBeneficiaries() {
    return ResponseEntity.ok(beneficiaryService.retrieveAllBeneficiaries());
  }

}
