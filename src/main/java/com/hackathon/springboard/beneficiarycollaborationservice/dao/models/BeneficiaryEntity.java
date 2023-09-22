package com.hackathon.springboard.beneficiarycollaborationservice.dao.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.hackathon.springboard.beneficiarycollaborationservice.constants.EntityConstants;
import com.hackathon.springboard.openapi.model.Outcome;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
@Data
public class BeneficiaryEntity {

  private String entityType;
  private String entityId;
  private String firstName;
  private String lastName;
  private OffsetDateTime dateOfBirth;
  private String identity;
  private String phoneNumber;
  private BigDecimal cabinNumber;
  private List<Integer> needs;
  private List<Outcome> outcomes;
  private String comments;

  @DynamoDbPartitionKey
  public String getEntityType() {
    return EntityConstants.BENEFICIARY_ENTITY_TYPE;
  }

  public void setEntityType(String entityType) {
    this.entityType = entityType;
  }

  @DynamoDbSortKey
  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

}
