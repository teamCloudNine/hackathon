package com.hackathon.springboard.beneficiarycollaborationservice.dao.models;

import com.hackathon.springboard.beneficiarycollaborationservice.constants.EntityConstants;
import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;

@Data
@DynamoDbBean
public class OrganizationEntity {

  private String entityType;
  private String entityId;
  private String name;
  private String streetAddress;
  private String state;
  private String zipcode;
  private String phoneNumber;
  private String description;
  private List<String> needs;

  @DynamoDbPartitionKey
  public String getEntityType() {
    return EntityConstants.ORG_ENTITY_TYPE;
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
