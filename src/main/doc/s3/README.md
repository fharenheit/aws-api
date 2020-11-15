# S3 API

* HDFS를 백엔드로 하는 S3 Compatible API
* HDFS는 Isilon HDFS
* 사용자의 버킷, 공유 버킷, 모든 사용자가 접근 가능한 버킷을 지원 

## nginx 설정

S3 API는 API 호출시 HTTP Header의 `host`에 실제로 호출하는 서비스를 포함하여 버킷명을 subdomain으로 사용하므로 실제로 존재하지 않는 서버가 호출이 되어 테스트를 할 수 없게 됩니다.
따라서 이를 위해서 nginx를 설정하도록 합니다.

### nginx 다운로드 및 설치

윈도에서 테스트하는 경우 nginx를 윈도 버전을 다운로드합니다. 윈도 버전은 다운로드하여 Uncompress후 `nginx.exe`를 실행하면 동작합니다. 원활한 작업을 위해서 윈도 커맨드 라인을 작업할 것을 권장합니다.

nginx를 커맨드로 실행하기 위해서 다음과 같이 입력합니다.

```
cd nginx-1.19.4
nginx
```

이미 실행중인 nginx를 재시작하기 위해서 중지하려면 다음과 같이 입력합니다.

```
cd nginx-1.19.4
nginx -s stop
```

### 호스트 파일 설정

S3 API를 서비스하는 서버의 호스트명을 `server`라고 칭하고 다음과 같이 호스트 파일을 변경합니다.

```
192.168.1.1 server
```

### nginx 설정

nginx 설정에서는 proxy를 사용합니다. server, *.server로 들어오는 모든 요청을 Java로 구현한 S3 API 서버로 전달되도록 구성합니다.

```
worker_processes  1;

error_log  logs/error.log;

events {
    worker_connections  1024;
}

http {
    include       mime.types;

    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                    '$status $body_bytes_sent "$http_referer" '
                    '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  logs/access.log main;

    sendfile        on;
    keepalive_timeout  65;
    gzip  on;

    upstream s3_api_servers {
        least_conn;
        #server 192.168.0.1:8000 weight=5;
        #server 192.168.0.1:8001 weight=5;
        server localhost:8080;
    }

    server {
        listen 80;
        server_name  server *.server;
        rewrite_log on;
        location / {
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_redirect off;
            proxy_pass http://localhost:8080/s3;
        }
    }
}
```

### AWS SDK의 Proxy 설정

이제 AWS SDK의 Proxy를 다음과 같이 설정합니다.

```java
// IAM의 Access Key, Secret Key 설정
BasicAWSCredentials awsCreds = new BasicAWSCredentials("admin", "admin123");

// ENDPOINT 설정
AwsClientBuilder.EndpointConfiguration configuration
        = new AwsClientBuilder.EndpointConfiguration("http://server:8080/s3", "korea");

// PROXY 설정 
ClientConfiguration clientConfiguration = new ClientConfiguration();
clientConfiguration.setProxyProtocol(Protocol.HTTP);
clientConfiguration.setProxyHost("server");
clientConfiguration.setProxyPort(80);

// S3 CLIENT 생성
AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();
builder.setCredentials(new AWSStaticCredentialsProvider(awsCreds));
builder.setEndpointConfiguration(configuration);
builder.setClientConfiguration(clientConfiguration);
AmazonS3 s3 = builder.build();

// Bucket 생성을 요청 
CreateBucketRequest request = new CreateBucketRequest("helloworld", "korea");
Bucket bucket = s3.createBucket(request);
```

## S3 API

다음은 S3 API에서 지원하는 전체 기능입니다.

* AbortMultipartUpload
* CompleteMultipartUpload
* CopyObject
* CreateBucket
* CreateMultipartUpload
* DeleteBucket
* DeleteBucketAnalyticsConfiguration
* DeleteBucketCors
* DeleteBucketEncryption
* DeleteBucketInventoryConfiguration
* DeleteBucketLifecycle
* DeleteBucketMetricsConfiguration
* DeleteBucketOwnershipControls
* DeleteBucketPolicy
* DeleteBucketReplication
* DeleteBucketTagging
* DeleteBucketWebsite
* DeleteObject
* DeleteObjects
* DeleteObjectTagging
* DeletePublicAccessBlock
* GetBucketAccelerateConfiguration
* GetBucketAcl
* GetBucketAnalyticsConfiguration
* GetBucketCors
* GetBucketEncryption
* GetBucketInventoryConfiguration
* GetBucketLifecycle
* GetBucketLifecycleConfiguration
* GetBucketLocation
* GetBucketLogging
* GetBucketMetricsConfiguration
* GetBucketNotification
* GetBucketNotificationConfiguration
* GetBucketOwnershipControls
* GetBucketPolicy
* GetBucketPolicyStatus
* GetBucketReplication
* GetBucketRequestPayment
* GetBucketTagging
* GetBucketVersioning
* GetBucketWebsite
* GetObject
* GetObjectAcl
* GetObjectLegalHold
* GetObjectLockConfiguration
* GetObjectRetention
* GetObjectTagging
* GetObjectTorrent
* GetPublicAccessBlock
* HeadBucket
* HeadObject
* ListBucketAnalyticsConfigurations
* ListBucketInventoryConfigurations
* ListBucketMetricsConfigurations
* ListBuckets
* ListMultipartUploads
* ListObjects
* ListObjectsV2
* ListObjectVersions
* ListParts
* PutBucketAccelerateConfiguration
* PutBucketAcl
* PutBucketAnalyticsConfiguration
* PutBucketCors
* PutBucketEncryption
* PutBucketInventoryConfiguration
* PutBucketLifecycle
* PutBucketLifecycleConfiguration
* PutBucketLogging
* PutBucketMetricsConfiguration
* PutBucketNotification
* PutBucketNotificationConfiguration
* PutBucketOwnershipControls
* PutBucketPolicy
* PutBucketReplication
* PutBucketRequestPayment
* PutBucketTagging
* PutBucketVersioning
* PutBucketWebsite
* PutObject
* PutObjectAcl
* PutObjectLegalHold
* PutObjectLockConfiguration
* PutObjectRetention
* PutObjectTagging
* PutPublicAccessBlock
* RestoreObject
* SelectObjectContent
* UploadPart
* UploadPartCopy

## Request & Response Model

S3 API는 XML을 이용하며 S3 API의 XSD 스키마를 제공합니다. 이에 따라서 XSD 스키마로 Request 및 Response의 Model 클래스를 JAXB xjc로 생성할 수 있습니다. 이를 위해서 다음과 같이 Maven Plugin을 사용하도록합니다.

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>jaxb2-maven-plugin</artifactId>
    <version>2.5.0</version>
    <executions>
        <execution>
            <id>xjc</id>
            <goals>
                <goal>xjc</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <packageName>com.datadynamics.bigdata.api.service.s3.model.http</packageName>
        <sources>
            <source>src/main/resources/xsd/s3.xsd</source>
        </sources>
    </configuration>
</plugin>
```

API의 Response를 생성할 때에는 각 Response XML의 최상위 태그에 해당하는 클래스를 찾아서 리턴하면 됩니다.

## 최소 지원 기능

S3 Compatible API가 동작하기 위해서는 최소로 다음의 기능을 먼저 구현해야 합니다.

* CreateBucket (X)
  * 버킷명을 지정하면 호출 호스트명에 버킷명이 추가되는 이슈
* DeleteBucket
* DeleteObject
* DeleteObjects
* GetBucketLocation
* GetObject
* ListBuckets (O)
* ListObjects
* ListObjectsV2
* PutObject

## 참고

### AWS API는 호출 실패시 여러번 호출을 시도합니다.

다음과 같이 MaxErrorRetry를 0로 설정합니다.

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
```

### List Buckets의 경우 SDK에 따라서 호출 URI가 다소 차이가 있습니다.

* AWS SDK Java의 경우 endpoint를 /s3로 지정하는 /s3/로 호출합니다.
* BOTO3의 경우 /s3로 지정하는 경우 /s3로 호출합니다.
