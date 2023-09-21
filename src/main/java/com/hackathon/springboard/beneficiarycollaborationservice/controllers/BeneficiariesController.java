package com.hackathon.springboard.beneficiarycollaborationservice.controllers;

import com.hackathon.springboard.openapi.api.BeneficiariesApi;
import com.hackathon.springboard.openapi.model.Beneficiary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BeneficiariesController implements BeneficiariesApi {

  @Override
  public ResponseEntity<Beneficiary> findBeneficiary(Integer id) {
    return null;
  }

  @Override
  public ResponseEntity<List<Beneficiary>> listBeneficiaries() {
    return null;
  }

}
