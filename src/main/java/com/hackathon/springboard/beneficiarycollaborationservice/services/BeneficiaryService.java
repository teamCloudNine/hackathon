package com.hackathon.springboard.beneficiarycollaborationservice.services;

import com.hackathon.springboard.beneficiarycollaborationservice.constants.EntityConstants;
import com.hackathon.springboard.beneficiarycollaborationservice.dao.BeneficiaryDao;
import com.hackathon.springboard.beneficiarycollaborationservice.mappers.BeneficiaryMapper;
import com.hackathon.springboard.openapi.model.Beneficiary;
import com.hackathon.springboard.openapi.model.BeneficiaryCreationRequest;
import com.hackathon.springboard.openapi.model.CreateBeneficiaryOutcomeRequest;
import com.hackathon.springboard.openapi.model.OutcomeEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    Map<String, AttributeValue> expressionValues = new HashMap<>();
    expressionValues
        .put(":val", AttributeValue
            .builder()
            .s(EntityConstants.BENEFICIARY_ENTITY_TYPE)
            .build());

    Expression finalExpression = Expression
        .builder()
        .expression("entityType = :val")
        .expressionValues(expressionValues)
        .build();

    return beneficiaryDao
        .retrieveList(ScanEnhancedRequest
                          .builder()
                          .filterExpression(finalExpression)
                          .build())
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
    if (outcomeRequest
        .getOutcomeEvent()
        .equals(OutcomeEvent.DECEASED)) {
      beneficiary.setEndDate(OffsetDateTime.now());
    }
    beneficiaryDao.save(beneficiaryMapper.beneficiaryToBeneficiaryEntity(beneficiary));
    return beneficiary;
  }

}
