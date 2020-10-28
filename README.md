# AWS API

## IAM

### 개요

* AWS 리소스에 대한 액세스를 안전하게 제어할 수 있는 웹 서비스
* IAM의 주요 기능은 인증과 권한 관리
* 처음 생성하는 계정은 모든 리소스에 완전한 접근이 가능한 SSO ID로 시작(ROOT 사용자)
* 루트 사용자의 이메일 주소와 암호로 로그인

### 주요 기능

AWS 계정에 대한 공유 액세스
세분화된 권한
많은 AWS 서비스와의 통합

### API

#### DynamoDB Query

```javascript
{
  "ConsumedCapacity": {
    "CapacityUnits": 1,
    "GlobalSecondaryIndexes": {
      "string": {
        "CapacityUnits": 1,
        "ReadCapacityUnits": 1,
        "WriteCapacityUnits": 1
      }
    },
    "LocalSecondaryIndexes": {
      "string": {
        "CapacityUnits": 1,
        "ReadCapacityUnits": 1,
        "WriteCapacityUnits": 1
      }
    },
    "ReadCapacityUnits": 1,
    "Table": {
      "CapacityUnits": 1,
      "ReadCapacityUnits": 1,
      "WriteCapacityUnits": 1
    },
    "TableName": "string",
    "WriteCapacityUnits": 1
  },
  "Count": 1,
  "Items": [
    {
      "string": {
        "B": "blob",
        "BOOL": true,
        "BS": [
          "blob"
        ],
        "L": [
          "AttributeValue"
        ],
        "M": {
          "string": "AttributeValue"
        },
        "N": "string",
        "NS": [
          "string"
        ],
        "NULL": true,
        "S": "string",
        "SS": [
          "string"
        ]
      }
    }
  ],
  "LastEvaluatedKey": {
    "string": {
      "B": "blob",
      "BOOL": true,
      "BS": [
        "blob"
      ],
      "L": [
        "AttributeValue"
      ],
      "M": {
        "string": "AttributeValue"
      },
      "N": "string",
      "NS": [
        "string"
      ],
      "NULL": true,
      "S": "string",
      "SS": [
        "string"
      ]
    }
  },
  "ScannedCount": 1
}
```

#### IAM ListAccessKey

```xml
<ListAccessKeysResponse xmlns="https://iam.amazonaws.com/doc/2010-05-08/">
  <ListAccessKeysResult>
    <UserName>Bob</UserName>
    <AccessKeyMetadata>
       <member>
          <UserName>Bob</UserName>
          <AccessKeyId>AKIA1234567890EXAMPLE</AccessKeyId>
          <Status>Active</Status> 
	   <CreateDate>2016-12-03T18:53:41Z</CreateDate>
       </member>
       <member>
          <UserName>Susan</UserName>
          <AccessKeyId>AKIA2345678901EXAMPLE</AccessKeyId>
          <Status>Inactive</Status>
	   <CreateDate>2017-03-25T20:38:14Z</CreateDate>
       </member>
    </AccessKeyMetadata>
    <IsTruncated>false</IsTruncated>
  </ListAccessKeysResult>
  <ResponseMetadata>
    <RequestId>7a62c49f-347e-4fc4-9331-6e8eEXAMPLE</RequestId>
  </ResponseMetadata>
</ListAccessKeysResponse>
```

### 참고

API 가이드는 https://docs.aws.amazon.com/IAM/latest/APIReference/welcome.html을 참고

## Reference Documentation

* [Batch Processing Example](https://github.com/spring-guides/gs-batch-processing)
* https://woowabros.github.io/experience/2020/02/05/springbatch-querydsl.html
* https://jojoldu.tistory.com/325
* https://github.com/jojoldu/spring-batch-in-action