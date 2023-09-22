package com.hackathon.springboard.beneficiarycollaborationservice.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.BeneficiaryDao;
import com.hackathon.springboard.beneficiarycollaborationservice.mappers.BeneficiaryMapper;
import com.hackathon.springboard.openapi.model.Beneficiary;
import com.hackathon.springboard.openapi.model.BeneficiaryCreationRequest;
import com.hackathon.springboard.openapi.model.CreateBeneficiaryOutcomeRequest;
import com.hackathon.springboard.openapi.model.OutcomeEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

@Service
@AllArgsConstructor
@Slf4j
public class BeneficiaryService {

  private final BeneficiaryDao beneficiaryDao;
  private final BeneficiaryMapper beneficiaryMapper;

  public Beneficiary retrieveBeneficiary(String id) {
    return beneficiaryMapper.beneficiaryEntityToBeneficiary(beneficiaryDao.retrieve(id));
  }

  public List<Beneficiary> retrieveAllBeneficiaries() {
    return beneficiaryDao.retrieveList(ScanEnhancedRequest
        .builder().build())
        .stream()
        .map(beneficiaryMapper::beneficiaryEntityToBeneficiary)
        .collect(Collectors.toList());
  }

  public Beneficiary creatBeneficiary(BeneficiaryCreationRequest beneficiaryCreationRequest) {
    Beneficiary beneficiary = beneficiaryMapper.beneficiaryCreationRequestToBeneficiary(beneficiaryCreationRequest);
    String generatedUUID = new StringJoiner("-")
        .add("beneficiary")
        .add(UUID
            .randomUUID()
            .toString())
        .toString();
    beneficiary.setId(generatedUUID);
    beneficiary.setStartDate(OffsetDateTime.now());
    log.debug("Generated beneficiary UUID {}...", generatedUUID);
    beneficiaryDao.save(beneficiaryMapper.beneficiaryToBeneficiaryEntity(beneficiary));
    return beneficiary;
  }

  public Beneficiary createBeneficiaryOutcome(String id, CreateBeneficiaryOutcomeRequest outcomeRequest) {
    Beneficiary beneficiary = beneficiaryMapper.beneficiaryEntityToBeneficiary(beneficiaryDao.retrieve(id));
    beneficiary.setOutcome(outcomeRequest.getOutcomeEvent());
    beneficiary.setOutcomeComment(outcomeRequest.getOutcomeComment());
    beneficiary.setOutcomeDate(outcomeRequest.getOutcomeDate());
    if(outcomeRequest.getOutcomeEvent().equals(OutcomeEvent.DECEASED)){
      beneficiary.setEndDate(OffsetDateTime.now());
    }
    beneficiaryDao.save(beneficiaryMapper.beneficiaryToBeneficiaryEntity(beneficiary));
    return beneficiary;
  }

}
