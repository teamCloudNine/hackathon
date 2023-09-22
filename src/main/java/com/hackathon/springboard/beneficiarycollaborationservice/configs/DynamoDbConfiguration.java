package com.hackathon.springboard.beneficiarycollaborationservice.configs;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.AllArgsConstructor;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
@AllArgsConstructor
public class DynamoDbConfiguration {

  private final DynamoDbProperties dynamoDbProperties;

  @Bean
//  @Profile("!local")
  protected DynamoDbEnhancedClient dynamoDbEnhancedClient() {
    return DynamoDbEnhancedClient.builder()
        .dynamoDbClient(
            DynamoDbClient.builder()
                .region(dynamoDbProperties.getRegion())
                .build()
        )
        .build();
  }

  @Bean
  @Profile("local")
  protected DynamoDbEnhancedClient localDynamoDbEnhancedClient() {
    return DynamoDbEnhancedClient.builder()
                                 .dynamoDbClient(
                                     DynamoDbClient.builder()
                                                   .region(dynamoDbProperties.getRegion())
                                                   .credentialsProvider(ProfileCredentialsProvider.create())
                                                   .endpointOverride(URI.create(dynamoDbProperties.getEndpoint()))
                                                   .build()
                                 )
                                 .build();
  }

}
