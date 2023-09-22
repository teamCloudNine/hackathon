package com.hackathon.springboard.beneficiarycollaborationservice.services;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.BeneficiaryDao;
import com.hackathon.springboard.beneficiarycollaborationservice.mappers.BeneficiaryMapper;
import com.hackathon.springboard.openapi.model.Beneficiary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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

}
