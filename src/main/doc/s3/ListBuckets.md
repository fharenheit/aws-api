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

요청에 대한 응답은 다음과 같으며 Location을 회신해야 합니다.

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

## 이슈

* 버킷 생성을 요청하면 bucket.hostname:port로 요청이 발생하므로 앞단에 웹서버가 호스트명을 해석해서 처리할 수 없으므로 무조건 S3 API 서비스로 요청이 들어가도록 처리해야 합니다.
  * helloworld 버킷 ---> http://helloworld.localhost:8080