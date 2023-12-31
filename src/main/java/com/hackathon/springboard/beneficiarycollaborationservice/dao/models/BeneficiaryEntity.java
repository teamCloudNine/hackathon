package com.hackathon.springboard.beneficiarycollaborationservice.dao.models;

import com.hackathon.springboard.beneficiarycollaborationservice.constants.EntityConstants;
import com.hackathon.springboard.openapi.model.OutcomeEvent;
import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

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
  private List<String> needs;
  private OutcomeEvent outcome;
  private String outcomeComment;
  private OffsetDateTime outcomeDate;
  private String comments;
  private OffsetDateTime startDate;
  private OffsetDateTime endDate;

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
