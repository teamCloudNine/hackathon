package com.hackathon.springboard.beneficiarycollaborationservice.dao;

import com.hackathon.springboard.beneficiarycollaborationservice.configs.DynamoDbProperties;
import com.hackathon.springboard.beneficiarycollaborationservice.dao.models.NeedEntity;
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
public class NeedDao implements DynamoDbDao<NeedEntity> {

  private final DynamoDbTable<NeedEntity> needEntityDynamoDbTable;

  public NeedDao(DynamoDbEnhancedClient dynamoDbEnhancedClient, DynamoDbProperties dynamoDbProperties) {
    this.needEntityDynamoDbTable = dynamoDbEnhancedClient.table(
        dynamoDbProperties.getTableName(),
        TableSchema.fromBean(NeedEntity.class));
  }

  @Override
  public void save(NeedEntity objectToSave) {
    needEntityDynamoDbTable.putItem(objectToSave);
  }

  @Override
  public NeedEntity retrieve(String id) {
    return needEntityDynamoDbTable.getItem(Key.builder()
                                              .partitionValue("Need")
                                              .sortValue(id)
                                              .build());
  }

  @Override
  public List<NeedEntity> retrieveList(ScanEnhancedRequest scanEnhancedRequest) {
    return needEntityDynamoDbTable
        .scan(scanEnhancedRequest)
        .stream()
        .map(Page::items)
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(String id) {
    needEntityDynamoDbTable.deleteItem(Key.builder()
                                          .partitionValue("Need")
                                          .sortValue(id)
                                          .build());
  }

}
