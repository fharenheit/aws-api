# AWS API 호환 Big Data API 서비스

## 프로젝트 개요

* 데이터 분석가 등이 이 API를 호출하여 데이터를 활용할 수 있도록 도와주는 API를 개발함
* 파일 제공, 데이터 제공 등이 목표인 이 API를 처음부터 설계하고 진행하는 것은 많은 설계 비용이 발생하므로 AWS SDK의 API에서 제공하는 규격을 최대한 활용하도록 함
* AWS에서 제공하는 S3, Dynamo, IAM, Athena, Glue를 프로젝트 기간 중에 개발함

```
사용자 --> 데이터 분석 도구 --> AWS API ----(HTTP)----> AWS API 서버 (우리가 개발할 Java로 만든 서버) --> 백엔드 (HDFS, SQL) <-- 데이터 적재
``` 

## 각 서비스 개요

### IAM

* AWS 리소스에 대한 액세스를 안전하게 제어할 수 있는 웹 서비스
* IAM의 주요 기능은 인증과 권한 관리
* 처음 생성하는 계정은 모든 리소스에 완전한 접근이 가능한 SSO ID로 시작(ROOT 사용자)
* 루트 사용자의 이메일 주소와 암호로 로그인

### S3

* AWS의 Object Storage 서비스(S3; Simple Storage Service)
* S3는 파일(오브젝트)을 저장하기 위한 저장소 서비스로서 상대적으로 가격이 싼 파일 시스템임
* Windows, Linux 등의 파일 시스템은 Random Access가 가능하지만 S3는 Random Access를 지원하지 않으므로 파일 시스템이 매우 단순함
* / 기준으로 최상위 폴더를 Bucket이라고 부르며 그 아래에는 디렉토리 및 파일들이 존재
  * 예) master bucket --> /master
  * 예) /master/2020-01-01/master-20200101.csv
* AWS SDK S3 API를 활용하여 Bucket 및 Object를 관리할 수 있음
* AWS IAM 서비스를 통해서 버킷 및 오브젝트에 대한 권한을 관리함
* Java로 작성된 AWS SDK API 예제는 https://github.com/awsdocs/aws-doc-sdk-examples/tree/master/java/example_code/s3/src/main/java/aws/example/s3을 참고하도록 함

### Dynamo

* 



## 설계시 고려사항

### 공통

* AWS의 각 서비스 마다 HTTP Body에 들어가는 포맷이 일관적이지 않음 (어떤 것은 XML로 주고 받고, 어떤 것은 JSON으로 주고 받음)
  * JSON인 경우 : Spring Boot에서 기본 사용하는 Jackson을 그대로 사용(Accept: application/json시 기본 동작)
  * XML인 경우
    * Spring Boot에서 기본 사용하는 Jackson의 XML Binding을 사용하거나
    * JAXB 자바 표준을 사용하거나(JAXB Annotation을 Model Class에 추가)
* 각 AWS 서비스의 HTTP Request/Response 형식은 AWS 사이트에 API Reference에 모두 나와 있음 --> 별도 설계가 필요하기 보다는 산출물만 만들면 된다는 것을 의미
* 원래 AWS 서비스는 HTTPS를 사용하며, 만약 HTTPS 통신이 요구사항이라면 Spring Boot의 Tomcat에 HTTPS를 구성하지 않고 Tomcat의 앞쪽에 웹 서버에서 HTTPS를 두어 개발의 영향을 최소화 해야함

### S3

* 백엔드가 Hadoop HDFS
* HDFS는 Java I/O (Input Stream, Output Stream)으로 모두 제어가 가능하므로 Common I/O 등으로 손쉽게 접근 가능
* Hadoop HDFS에 접근하기 위해서는 Hadoop HDFS API의 `org.apache.hadoop.fs.FileSystem`을 통해서 `InputStream`을 가져오면 기존 파일 및 디렉토리 관리와 모두 동일
* Hadoop은 다수의 서버로 분산 파일 저장소(HDFS)를 구성하므로 S3 API 서버를 개발하고 테스트할 때에는 Hadoop의 모든 서버에 IP와 Port(Any)로 접근이 가능해야 함 (반드시)
** 참고) https://d1jnx9ba8s6j9r.cloudfront.net/blog/wp-content/uploads/2013/05/Apache-Hadoop-HDFS-Architecture-Edureka.png
* S3의 버킷은 hdfs://NAMENODE_SERVER:8020/.../ 어딘가로 매핑하도록 하고, 그 이하에 있는 디렉토리 및 폴더는 S3 Object로 생각하면 됨
* Hadoop Cluster가 구성되면 Hadoop 관리자에게 `core-site.xml`, `hdfs-site.xml` 파일을 달라고 요청해야 함 --> Hadoop API를 쓰려면 이 파일이 있어야 함
* IAM에서는 Bucket에 대해서만 권한을 관리할지, 이 이하 모든 파일에 대해서 권한을 관리할지 결정을 해야함 --> Bucket에 대해서만 권한을 관리하는 것을 추천함

다음은 Hadoop HDFS API로 작성한 디렉토리 생성하는 간단한 예제임

```java
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class SimpleFileSystemTest {

    private static final String CONFIG_PATH = "hdfs/core-site.xml";
    private static final String FILE_PATH = "README.txt";
    
    private final Logger logger = LoggerFactory.getLogger(SimpleFileSytemTest.class);
    
    private Configuration configuration;
    
    @Before
    public void setup() {
        this.configuration = new Configuration();
        this.configuration.addResource(new Path(CONFIG_PATH)); // cores-site.xml, hdfs-site.xml 파일을 여기에서 추가
    }
    
    @Test
    public void testMakeDir() throws IllegalArgumentException, IOException {
        boolean success = FileSystem.get(configuration).mkdirs(new Path("/user/s3/test2")); // hdfs://NAMENODE:8020/user/s3/test2와 같음
        Assert.assertEquals(true, success);
    }
}
```

다음은 AWS SDK S3 API로 Bucket을 새로 생성하는 예제임.

```java
public static Bucket getBucket(String bucket_name) {
    final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
    Bucket named_bucket = null;
    List<Bucket> buckets = s3.listBuckets();
    for (Bucket b : buckets) {
        if (b.getName().equals(bucket_name)) {
            named_bucket = b;
        }
    }
    return named_bucket;
}

public static Bucket createBucket(String bucket_name) {
    final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
    Bucket b = null;
    if (s3.doesBucketExistV2(bucket_name)) {
        System.out.format("Bucket %s already exists.\n", bucket_name);
        b = getBucket(bucket_name);
    } else {
        try {
            b = s3.createBucket(bucket_name);
        } catch (AmazonS3Exception e) {
            System.err.println(e.getErrorMessage());
        }
    }
    return b;
}
```

### 개요



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