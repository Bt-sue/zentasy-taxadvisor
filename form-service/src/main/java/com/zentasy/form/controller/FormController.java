package com.zentasy.form.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FormController {

  private final DynamoDbClient ddb;

  @PostMapping("/forms")
  public Map<String,String> submit(@RequestBody Map<String,String> req) {
    // 1. 生成 formId
    String formId = UUID.randomUUID().toString();

    // 2. 写入 DynamoDB
    ddb.putItem(PutItemRequest.builder()
      .tableName("SellerProfile")
      .item(Map.of(
        "formId",   AttributeValue.fromS(formId),
        "name",     AttributeValue.fromS(req.getOrDefault("name","")),
        "industry", AttributeValue.fromS(req.getOrDefault("industry","")),
        "budget",   AttributeValue.fromS(req.getOrDefault("budget","0"))
      )).build());

    // 3. 返回 formId 给前端
    return Map.of("formId", formId);
  }
}
