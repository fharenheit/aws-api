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
  * 루트 사용자의 이메일 주소와 암호로 로그인하나 이 프로젝트에서는 의미가 없음
* 각 리소스(S3, Dynamo 등)에 대한 권한 관리

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

* Key-Value DB (HashMap이나 Cache와 같다고 보면 됨)
* Key-Value로 데이터 구조가 되어 있어서 Key를 알면 Value를 쉽게 가져올 수 있으나 BETWEEN 조회에 매우 취약(Key를 정확하게 모르기 때문)

## 설계시 고려사항

### 공통

* AWS의 각 서비스 마다 HTTP Body에 들어가는 포맷이 일관적이지 않음 (어떤 것은 XML로 주고 받고, 어떤 것은 JSON으로 주고 받음)
  * JSON인 경우 : Spring Boot에서 기본 사용하는 Jackson을 그대로 사용(Accept: application/json시 기본 동작)
  * XML인 경우
    * Spring Boot에서 기본 사용하는 Jackson의 XML Binding을 사용하거나
    * JAXB 자바 표준을 사용하거나(JAXB Annotation을 Model Class에 추가)
* 각 AWS 서비스의 HTTP Request/Response 형식은 AWS 사이트에 API Reference에 모두 나와 있음 --> 별도 설계가 필요하기 보다는 산출물만 만들면 된다는 것을 의미
* 원래 AWS 서비스는 HTTPS를 사용하며, 만약 HTTPS 통신이 요구사항이라면 Spring Boot의 Tomcat에 HTTPS를 구성하지 않고 Tomcat의 앞쪽에 웹 서버에서 HTTPS를 두어 개발의 영향을 최소화 해야함
* 테스트시 AWS SDK Java를 이용해야 하지만 Python 용으로 Boto3(리눅스에서 `pip install boto3`으로 설치가능)를 이용해서 예제를 같이 작성해야 함
  * 개발 및 테스트 서버에서 Boto3로 테스트를 하는 과정이 반드시 필요하며 이 경우 서버 관리자 또는 운영자에게 설치를 요청해야 함
  * 인터넷 다운로드를 하므로 관리자가 아니면 설치가 불가능

### IAM

* 백엔드가 없음 (DB 기반으로 구현해야 함)
  * 사용자 및 그룹 정보가 LDAP 이나 AD(Active Directory)와 연계를 해야하는지 확인 필요
    * LDAP 연계시 Spring LDAP으로 구현하면 쉽게 구현이 가능함
  * 사용자 정보를 독립적으로 구축하는 경우, 부서와 임직원 정보를 DB에 주기적으로 넣는 Batch 프로그램을 작성해야 함
* 사용자별 Access Key를 AWS에서는 여러 개를 만들 수 있지만 고객사의 경우 1인당 1개만 사용해도 무방한 수준이므로 협의 필요함+
  * 이 경우 Usenname이 Access Key가 될 수 있음
  * Username이 Email의 @ 앞을 사용하는 것인지 별도 SSO ID 체계가 있는 것인지 확인이 필요함
* 사용자 중에서 IS ADMIN이 TRUE 경우에만 사용자를 추가할 수 있도록 해야 함
* AWS의 SECRET KEY가 자동 생성되므로 여기에서도 Credential 클래스의 Secret Key를 랜덤하게 만들 수 있는 법을 설계 해야함
  * Username을 특정한 Seed를 줘서 인코딩 후 Base64로 변환하는 방법 등과 같은 방식
* 복잡한 Role이 필요한지 확인
  * Admin, User만 필요하다면 User의 admin 속성의 True False만 가지고 충분히 해결 --> AWS IAM API 구현이 간단
  * 부서별로 권한을 관리할지 여부를 결정해야 함 (이것 들어가면 복잡해짐)
    * 부서별로 권한을 별도로 관리하는 것이 아니라면 부서정보는 되도록 관리하지 않는 방향으로 가야 함
* AWS API에 기능이 많지만 필요한 것만 구현하도록 함

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

### Dynamo

* 백엔드는 Postgres, Oracle DB
* AWS SDK Dynamo API를 Client가 호출하면 HTTP Body에 SQL에 필요한 Parameter가 전달되고 서버는 이 정보로 SQL을 만들어서 Query를 실행하고 결과를 되돌려 줌
* 전체 서비스 중에서 가장 기능이 없음
* 사용자가 <Schema>.<Table>의 데이터를 SELECT 하는 경우 Postgres와 Oracle DB중 어디에 있는지 정보를 관리할 수 있도록 해야 함
  * IAM의 스키마 및 테이블 관리에서 속성 컬럼을 하나 두어 처리하면 됨
* 백엔드가 DB이므로 테이블을 생성하는 권한은 DB 관리자에게 있으므로 API로 구현해야 하는 것은 SELECT 같은 것만 구현함

다음은 AWS SDK Dynamo API로 테이블을 목록을 가져오는 예제임.

```java
final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();

try {
    TableDescription table_info = ddb.describeTable(table_name).getTable();

    if (table_info != null) {
        System.out.format("Table name  : %s\n", table_info.getTableName());
        System.out.format("Table ARN   : %s\n", table_info.getTableArn());
        System.out.format("Status      : %s\n", table_info.getTableStatus());
        System.out.format("Item count  : %d\n", table_info.getItemCount().longValue());
        System.out.format("Size (bytes): %d\n", table_info.getTableSizeBytes().longValue());

        ProvisionedThroughputDescription throughput_info = table_info.getProvisionedThroughput();
        System.out.println("Throughput");
        System.out.format("  Read Capacity : %d\n", throughput_info.getReadCapacityUnits().longValue());
        System.out.format("  Write Capacity: %d\n", throughput_info.getWriteCapacityUnits().longValue());

        List<AttributeDefinition> attributes = table_info.getAttributeDefinitions();
        System.out.println("Attributes");
        for (AttributeDefinition a : attributes) {
            System.out.format("  %s (%s)\n", a.getAttributeName(), a.getAttributeType());
        }
    }
} catch (AmazonServiceException e) {
    System.err.println(e.getErrorMessage());
    System.exit(1);
}
```

## 참고

API 가이드는 https://docs.aws.amazon.com/IAM/latest/APIReference/welcome.html을 참고

## Reference Documentation

* [Batch Processing Example](https://github.com/spring-guides/gs-batch-processing)
* https://woowabros.github.io/experience/2020/02/05/springbatch-querydsl.html
* https://jojoldu.tistory.com/325
* https://github.com/jojoldu/spring-batch-in-action