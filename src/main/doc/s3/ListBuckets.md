# ListBuckets

* 버킷 목록을 제공합니다.
* 사용자 버킷도 포함시킬지 여부를 결정해야 합니다.

## API Reference

https://docs.aws.amazon.com/AmazonS3/latest/API/API_CreateBucket.html

### Request 정리

요청 메시지는 다음과 같습니다.

```
GET / HTTP/1.1
```

## Response 정리

* Owner는 어떤 Owner인가?
* 어떤 Owner의 버킷 목록을 제공해야 하나?

```
HTTP/1.1 200
<?xml version="1.0" encoding="UTF-8"?>
<ListAllMyBucketsResult>
   <Buckets>
      <Bucket>
         <CreationDate>timestamp</CreationDate>
         <Name>string</Name>
      </Bucket>
   </Buckets>
   <Owner>
      <DisplayName>string</DisplayName>
      <ID>string</ID>
   </Owner>
</ListAllMyBucketsResult>
```

## 언어별 클라이언트 예제

### Java

```java
BasicAWSCredentials awsCreds = new BasicAWSCredentials("admin", "admin123");

AwsClientBuilder.EndpointConfiguration configuration
        = new AwsClientBuilder.EndpointConfiguration("http://localhost:8080/s3", "korea");

AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();
builder.setCredentials(new AWSStaticCredentialsProvider(awsCreds));
builder.setEndpointConfiguration(configuration);

ClientConfiguration clientConfiguration = new ClientConfiguration();
clientConfiguration.setMaxErrorRetry(0); // 0로 하지 않으면 여러번 호출한다.
builder.setClientConfiguration(clientConfiguration);
AmazonS3 s3 = builder.build();

List<Bucket> buckets = s3.listBuckets();
System.out.println(buckets);
```

### Python

```python
import boto3

boto3.set_stream_logger(name='botocore')

session = boto3.session.Session()

s3_client = session.client(
    service_name='s3',
    aws_access_key_id='aaa',
    aws_secret_access_key='bbb',
    endpoint_url='http://localhost:8080/s3',
)

print(s3_client.list_buckets())
```

## 이슈

* 버킷 목록의 경우 자기 자신의 목록을 가져오나 공통 버킷과 자신의 버킷을 가져오는 경우 Owner 설정이 문제