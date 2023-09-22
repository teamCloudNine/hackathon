package com.hackathon.springboard.beneficiarycollaborationservice.dao;

import com.hackathon.springboard.beneficiarycollaborationservice.configs.DynamoDbProperties;
import com.hackathon.springboard.beneficiarycollaborationservice.constants.EntityConstants;
import com.hackathon.springboard.beneficiarycollaborationservice.dao.models.ActivityEntity;
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
public class ActivityDao implements DynamoDbDao<ActivityEntity> {

  private final DynamoDbTable<ActivityEntity> activityEntityDynamoDbTable;

  public ActivityDao(DynamoDbEnhancedClient dynamoDbEnhancedClient, DynamoDbProperties dynamoDbProperties) {
    this.activityEntityDynamoDbTable = dynamoDbEnhancedClient.table(
        dynamoDbProperties.getTableName(),
        TableSchema.fromBean(ActivityEntity.class));
  }

  @Override
  public void save(ActivityEntity objectToSave) {
    activityEntityDynamoDbTable.putItem(objectToSave);
  }

  @Override
  public ActivityEntity retrieve(String id) {
    return activityEntityDynamoDbTable.getItem(Key.builder()
                                                  .partitionValue(EntityConstants.ACTIVITY_ENTITY_TYPE)
                                                  .sortValue(id)
                                                  .build());
  }

  @Override
  public List<ActivityEntity> retrieveList(ScanEnhancedRequest scanEnhancedRequest) {
    return activityEntityDynamoDbTable
        .scan(scanEnhancedRequest)
        .stream()
        .map(Page::items)
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(String id) {
    activityEntityDynamoDbTable.deleteItem(Key.builder()
                                              .partitionValue(EntityConstants.ACTIVITY_ENTITY_TYPE)
                                              .sortValue(id)
                                              .build());
  }

}
