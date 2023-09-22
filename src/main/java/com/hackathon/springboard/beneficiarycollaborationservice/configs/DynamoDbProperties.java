package com.hackathon.springboard.beneficiarycollaborationservice.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import software.amazon.awssdk.regions.Region;

@Data
@ConfigurationProperties(prefix = "aws.dynamo-db")
public class DynamoDbProperties {

  private Region region;
  private String endpoint;
  private String tableName;

}
