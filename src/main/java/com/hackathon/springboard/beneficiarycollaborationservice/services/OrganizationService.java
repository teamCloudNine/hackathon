package com.hackathon.springboard.beneficiarycollaborationservice.services;

import com.hackathon.springboard.beneficiarycollaborationservice.constants.EntityConstants;
import com.hackathon.springboard.beneficiarycollaborationservice.dao.OrganizationDao;
import com.hackathon.springboard.beneficiarycollaborationservice.mappers.OrganizationMapper;
import com.hackathon.springboard.openapi.model.Organization;
import com.hackathon.springboard.openapi.model.OrganizationCreationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrganizationService {

  private final OrganizationDao organizationDao;
  private final OrganizationMapper organizationMapper;

  public Organization retrieveOrganizationById(String id) {
    return organizationMapper.organizationEntityToOrganization(organizationDao.retrieve(id));
  }

  public List<Organization> retrieveOrganizations(String needId) {
    Map<String, AttributeValue> expressionValues = new HashMap<>();
    expressionValues
        .put(":val", AttributeValue
            .builder()
            .s(EntityConstants.ORG_ENTITY_TYPE)
            .build());
    Expression expression = Expression
        .builder()
        .expression("entityType = :val")
        .build();

    List<Expression> listOfExpressions = new ArrayList<>();

    if (StringUtils.isNotBlank(needId)) {
      String needKey = ":nVal";
      expressionValues
          .put(needKey, AttributeValue
              .builder()
              .s(needId)
              .build());
      listOfExpressions.add(Expression
                                .builder()
                                .expression("contains(needs, " + needKey + ")")
                                .build());
    }

    String expressionStatement = "";

    if (listOfExpressions.isEmpty()) {
      expressionStatement = expression.expression();
    }

    for (Expression ex : listOfExpressions) {
      expressionStatement = Expression
          .join(
              expression,
              ex,
              "AND")
          .expression();
    }

    log.info("Organization Expression Statement {}...", expressionStatement);
    log.info("Organization Expression Values {}...", expressionValues);

    Expression finalExpression = Expression
        .builder()
        .expression(expressionStatement)
        .expressionValues(expressionValues)
        .build();

    return organizationDao
        .retrieveList(ScanEnhancedRequest
                          .builder()
                          .filterExpression(finalExpression)
                          .build())
        .stream()
        .map(organizationMapper::organizationEntityToOrganization)
        .peek(item -> log.debug("Retrieved Organization {}", item))
        .collect(Collectors.toList());
  }

  public Organization createOrganization(OrganizationCreationRequest organization) {
    Organization org = organizationMapper.organizationCreationRequestToOrganization(organization);
    String generatedUUID = new StringJoiner("-")
        .add("organization")
        .add(UUID
                 .randomUUID()
                 .toString())
        .toString();
    org.setId(generatedUUID);
    log.debug("Generated org UUID {}...", generatedUUID);
    organizationDao.save(organizationMapper.organizationToOrganizationEntity(org));
    return org;
  }

}
