package com.hackathon.springboard.beneficiarycollaborationservice.dao.models;

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

  private Integer organizationId;

  private Integer beneficiaryId;

  private Integer needId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime startDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime endDate;

  private ActivityStatus status;

  private String comments;

  @DynamoDbPartitionKey
  public String getEntityType() {
    return "Activity";
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
