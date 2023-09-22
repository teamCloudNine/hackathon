package com.hackathon.springboard.beneficiarycollaborationservice.services;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.BeneficiaryDao;
import com.hackathon.springboard.beneficiarycollaborationservice.mappers.BeneficiaryMapper;
import com.hackathon.springboard.openapi.model.Beneficiary;
import com.hackathon.springboard.openapi.model.BeneficiaryCreationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
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
    return beneficiaryDao.retrieveList(ScanEnhancedRequest
                                    .builder().build())
                  .stream()
                  .map(beneficiaryMapper::beneficiaryEntityToBeneficiary)
                  .collect(Collectors.toList());
  }
  
  public Beneficiary creatBeneficiary(BeneficiaryCreationRequest beneficiaryCreationRequest){
    Beneficiary beneficiary = beneficiaryMapper.beneficiaryCreationRequestToBeneficiary(beneficiaryCreationRequest);
    String generatedUUID = new StringJoiner("-")
        .add("beneficiary")
        .add(UUID
                 .randomUUID()
                 .toString())
        .toString();
    beneficiary.setId(generatedUUID);
    log.debug("Generated beneficiary UUID {}...", generatedUUID);
    beneficiaryDao.save(beneficiaryMapper.beneficiaryToBeneficiaryEntity(beneficiary));
    return beneficiary;
  }

}
