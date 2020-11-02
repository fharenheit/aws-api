# CreateBucket

* 새로운 버킷을 생성합니다.
* HDFS의 기준 디렉토리를 설정해야 합니다.
* 버킷 생성을 요청하면 HDFS의 기준 디렉토리 하부에 디렉토리를 생성하고 IAM에 해당 테이블을 추가합니다.
* 사용자는 무조건 하나의 버킷을 가져야 하므로 자신의 버킷에 접근하는 경우를 지원해야 합니다.
* 자신의 버킷인지 시스템 버킷인지 구분을 할 수 있어야 합니다.

## API Reference

https://docs.aws.amazon.com/AmazonS3/latest/API/API_CreateBucket.html

### Request 정리

* ACL 정보를 x-amz-acl 헤더에 추가 (Canned ACL : https://docs.aws.amazon.com/AmazonS3/latest/dev/acl-overview.html#CannedACL)
  * private | public-read | public-read-write | authenticated-read 값 사용 가능
* Permission은 x-amz-grant-read, x-amz-grant-write, x-amz-grant-read-acp, x-amz-grant-write-acp, x-amz-grant-full-control 헤더로 지정 (https://docs.aws.amazon.com/AmazonS3/latest/dev/acl-overview.html)

요청 메시지는 다음과 같습니다. 버킷명은 Host 헤더에 포함이 되어 있습니다.

```
PUT / HTTP/1.1
Host: Bucket.s3.amazonaws.com
x-amz-acl: ACL
x-amz-grant-full-control: GrantFullControl
x-amz-grant-read: GrantRead
x-amz-grant-read-acp: GrantReadACP
x-amz-grant-write: GrantWrite
x-amz-grant-write-acp: GrantWriteACP
x-amz-bucket-object-lock-enabled: ObjectLockEnabledForBucket
<?xml version="1.0" encoding="UTF-8"?>
<CreateBucketConfiguration xmlns="http://s3.amazonaws.com/doc/2006-03-01/">
   <LocationConstraint>string</LocationConstraint>
</CreateBucketConfiguration>
```

## Response 정리

요청에 대한 응답은 다음과 같으며 Location을 회신해야 합니다.

```
HTTP/1.1 200 OK
x-amz-id-2: YgIPIfBiKa2bj0KMg95r/0zo3emzU4dzsD4rcKCHQUAdQkf3ShJTOOpXUueF6QKo
x-amz-request-id: 236A8905248E5A01
Date: Wed, 01 Mar  2006 12:00:00 GMT

Location: /colorpictures
Content-Length: 0
Connection: close
Server: AmazonS3
```

## 이슈

* 버킷 생성을 요청하면 bucket.hostname:port로 요청이 발생하므로 앞단에 웹서버가 호스트명을 해석해서 처리할 수 없으므로 무조건 S3 API 서비스로 요청이 들어가도록 처리해야 합니다.
  * helloworld 버킷 ---> http://helloworld.localhost:8080