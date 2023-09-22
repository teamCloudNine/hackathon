package com.hackathon.springboard.beneficiarycollaborationservice.dao;

import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.util.List;

public interface DynamoDbDao<T> {

  void save(T objectToSave);

  T retrieve(String id);

  List<T> retrieveList(ScanEnhancedRequest scanEnhancedRequest);

  void delete(String id);

}
