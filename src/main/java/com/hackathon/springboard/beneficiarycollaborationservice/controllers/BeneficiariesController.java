package com.hackathon.springboard.beneficiarycollaborationservice.controllers;

import com.hackathon.springboard.openapi.api.BeneficiariesApi;
import com.hackathon.springboard.openapi.model.Beneficiary;
import com.hackathon.springboard.openapi.model.BeneficiaryCreationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BeneficiariesController implements BeneficiariesApi {

  @Override
  public ResponseEntity<Beneficiary> createBeneficiary(BeneficiaryCreationRequest beneficiaryCreationRequest) {
    return null;
  }

  @Override
  public ResponseEntity<Beneficiary> findBeneficiary(Integer id) {
    return null;
  }

  @Override
  public ResponseEntity<List<Beneficiary>> listBeneficiaries() {
    return null;
  }

}
