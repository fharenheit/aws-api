# S3 API

* HDFS를 백엔드로 하는 S3 Compatible API
* HDFS는 Isilon HDFS
* 사용자 디렉토리(User Directory) 및 공용 데이터 버킷 지원

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