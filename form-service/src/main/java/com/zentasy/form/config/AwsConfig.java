package com.zentasy.form.config;   // 注意：把包名改成 form.config，而不是 form_service.config

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.net.URI;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class AwsConfig {
  @Bean
  public DynamoDbClient dynamoDbClient() {
    return DynamoDbClient.builder()
      // 明确告诉 SDK “只用 URLConnection 这个 HTTP 客户端”
      .httpClientBuilder(UrlConnectionHttpClient.builder())
      .endpointOverride(URI.create("http://localstack:4566"))
      .region(Region.of("us-east-1"))
      .credentialsProvider(
        StaticCredentialsProvider.create(
          AwsBasicCredentials.create("test","test")))
      .build();   // 不再显式设置 httpClientBuilder
  }
}
