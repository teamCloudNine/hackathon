package com.hackathon.springboard.beneficiarycollaborationservice.dao;

import com.hackathon.springboard.beneficiarycollaborationservice.configs.DynamoDbProperties;
import com.hackathon.springboard.beneficiarycollaborationservice.constants.EntityConstants;
import com.hackathon.springboard.beneficiarycollaborationservice.dao.models.OrganizationEntity;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganizationDao implements DynamoDbDao<OrganizationEntity> {

  private final DynamoDbTable<OrganizationEntity> organizationEntityDynamoDbTable;

  public OrganizationDao(DynamoDbEnhancedClient dynamoDbEnhancedClient, DynamoDbProperties dynamoDbProperties) {
    this.organizationEntityDynamoDbTable = dynamoDbEnhancedClient.table(
        dynamoDbProperties.getTableName(),
        TableSchema.fromBean(OrganizationEntity.class));
  }

  @Override
  public void save(OrganizationEntity objectToSave) {
    organizationEntityDynamoDbTable.putItem(objectToSave);
  }

  @Override
  public OrganizationEntity retrieve(String id) {
    return organizationEntityDynamoDbTable.getItem(Key.builder()
                                                       .partitionValue(EntityConstants.ORG_ENTITY_TYPE)
                                                       .sortValue(id)
                                                       .build());
  }

  @Override
  public List<OrganizationEntity> retrieveList(ScanEnhancedRequest scanEnhancedRequest) {
    return organizationEntityDynamoDbTable.scan(scanEnhancedRequest)
        .stream()
        .map(Page::items)
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(String id) {
    organizationEntityDynamoDbTable.deleteItem(Key.builder()
                                                         .partitionValue(EntityConstants.ORG_ENTITY_TYPE)
                                                         .sortValue(id)
                                                         .build());
  }

}
