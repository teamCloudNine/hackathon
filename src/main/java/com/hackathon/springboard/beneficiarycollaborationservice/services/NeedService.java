package com.hackathon.springboard.beneficiarycollaborationservice.services;

import com.hackathon.springboard.beneficiarycollaborationservice.dao.NeedDao;
import com.hackathon.springboard.beneficiarycollaborationservice.mappers.NeedMapper;
import com.hackathon.springboard.openapi.model.Need;
import com.hackathon.springboard.openapi.model.NeedCreationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class NeedService {

  private final NeedDao needDao;
  private final NeedMapper needMapper;

  public Need retrieveNeed(String id) {
    return needMapper.needEntityToNeed(needDao.retrieve(id));
  }

  public List<Need> retrieveAllNeeds() {
    Map<String, AttributeValue> expressionValues = new HashMap<>();
    expressionValues
        .put(":val", AttributeValue
            .builder()
            .s("Need")
            .build());

    return needDao
        .retrieveList(ScanEnhancedRequest
                          .builder()
                          .filterExpression(
                              Expression
                                  .builder()
                                  .expression("entityType = :val")
                                  .expressionValues(expressionValues)
                                  .build())
                          .build())
        .stream()
        .map(needMapper::needEntityToNeed)
        .collect(Collectors.toList());
  }

  public Need createNeed(NeedCreationRequest needCreationRequest) {
    Need need = needMapper.needCreationRequestToNeed(needCreationRequest);
    String generatedUUID = new StringJoiner("-")
        .add("need")
        .add(UUID
                 .randomUUID()
                 .toString())
        .toString();
    need.setId(generatedUUID);
    log.debug("Generated need UUID {}...", generatedUUID);
    needDao.save(needMapper.needToNeedEntity(need));
    return need;
  }

}
