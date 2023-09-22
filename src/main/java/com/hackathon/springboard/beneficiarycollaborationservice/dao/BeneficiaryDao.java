package com.hackathon.springboard.beneficiarycollaborationservice.dao;

import com.hackathon.springboard.beneficiarycollaborationservice.configs.DynamoDbProperties;
import com.hackathon.springboard.beneficiarycollaborationservice.dao.models.BeneficiaryEntity;
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
public class BeneficiaryDao implements DynamoDbDao<BeneficiaryEntity> {

  private final DynamoDbTable<BeneficiaryEntity> beneficiaryEntityDynamoDbTable;

  public BeneficiaryDao(DynamoDbEnhancedClient dynamoDbEnhancedClient, DynamoDbProperties dynamoDbProperties) {
    this.beneficiaryEntityDynamoDbTable = dynamoDbEnhancedClient.table(
        dynamoDbProperties.getTableName(),
        TableSchema.fromBean(BeneficiaryEntity.class));
  }

  @Override
  public void save(BeneficiaryEntity objectToSave) {
    beneficiaryEntityDynamoDbTable.putItem(objectToSave);
  }

  @Override
  public BeneficiaryEntity retrieve(String id) {
    return beneficiaryEntityDynamoDbTable.getItem(Key.builder()
                                                     .partitionValue("Beneficiary")
                                                     .sortValue(id)
                                                     .build());
  }

  @Override
  public List<BeneficiaryEntity> retrieveList(ScanEnhancedRequest scanEnhancedRequest) {
    return beneficiaryEntityDynamoDbTable
        .scan(scanEnhancedRequest)
        .stream()
        .map(Page::items)
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(String id) {
    beneficiaryEntityDynamoDbTable.deleteItem(Key.builder()
                                                 .partitionValue("Beneficiary")
                                                 .sortValue(id)
                                                 .build());
  }

}
