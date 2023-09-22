package com.hackathon.springboard.beneficiarycollaborationservice.dao.models;

import com.hackathon.springboard.beneficiarycollaborationservice.constants.EntityConstants;
import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@Data
@DynamoDbBean
public class NeedEntity {

  private String entityType;
  private String entityId;
  private String name;
  private String description;
  private String alternativeName;

  @DynamoDbPartitionKey
  public String getEntityType() {
    return EntityConstants.NEED_ENTITY_TYPE;
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
