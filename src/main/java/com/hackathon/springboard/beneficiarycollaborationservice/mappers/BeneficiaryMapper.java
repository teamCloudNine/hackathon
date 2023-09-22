package com.hackathon.springboard.beneficiarycollaborationservice.mappers;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.models.BeneficiaryEntity;
import com.hackathon.springboard.openapi.model.Beneficiary;
import com.hackathon.springboard.openapi.model.BeneficiaryCreationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BeneficiaryMapper {

  @Mapping(source = "entityId", target = "id")
  Beneficiary beneficiaryEntityToBeneficiary(BeneficiaryEntity beneficiaryEntity);


  @Mapping(source = "id", target = "entityId")
  @Mapping(target = "entityType", constant = "Beneficiary")
  BeneficiaryEntity beneficiaryToBeneficiaryEntity(Beneficiary beneficiary);

  Beneficiary beneficiaryCreationRequestToBeneficiary(BeneficiaryCreationRequest beneficiaryCreationRequest);

}
