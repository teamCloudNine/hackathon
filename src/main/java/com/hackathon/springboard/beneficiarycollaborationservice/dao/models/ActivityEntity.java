package com.hackathon.springboard.beneficiarycollaborationservice.dao.models;

import com.hackathon.springboard.beneficiarycollaborationservice.constants.EntityConstants;
import com.hackathon.springboard.openapi.model.ActivityStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.time.OffsetDateTime;

@Data
@DynamoDbBean
public class ActivityEntity {

  private String entityType; 
  private String entityId;
  private String organizationId;
  private String beneficiaryId;
  private String needId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime startDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime endDate;

  private ActivityStatus activityStatus;

  private String comments;

  @DynamoDbPartitionKey
  public String getEntityType() {
    return EntityConstants.ACTIVITY_ENTITY_TYPE;
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
