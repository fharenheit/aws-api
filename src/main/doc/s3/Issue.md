# Issue & TODO

## Issue

* 사용자당 1개의 버킷이 있는데 일반 사용자가 Create Bucket을 허용할 것인지? 허용한다면 어떻게 HDFS 디렉토리를 만들어야 하는지?
  * 이 기능은 관리자 용으로 봐야 하는게 아닌지?
  * 버킷 생성시 권한은?

* 사용자가 버킷 목록을 요청하면 어떤 것들을 리턴해야 하는가?
  * 버킷은 크게 두 종류
    * 개인 버킷 (ISILON의 HDFS PATH; Linux의 HOME DIRECTORY와 같은 개념)
    * 공용 버킷 (모든 사용자가 볼 수 있는 READ ONLY HDFS PATH를 Bucket으로 매핑한 놈)
  * 사용자가 버킷 목록을 요청하면 상기 두 종류를 모두 리턴할지 개인 버킷만 리턴할지 결정이 필요함

## TODO

* 공용 버킷의 매핑 정보를 DB에 넣기
  * 예) A Bucket : `hdfs://${ISILON_IP}/DataLake/A`
* 사용자 버킷의 HDFS의 Base Directory 정해두기
  * 예) User Bucket Base Path : `hdfs://${ISILON_IP}/s3users/${SSO_ID}`
* HDFS 관련 공통 코드 작성하기
