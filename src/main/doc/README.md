# 개발자 가이드

## 개발도구

* JDK 1.8 (http://www.hadoop-professionals.org/download/OracleJDK/jdk-8u251-windows-x64.exe)
* Anaconda (https://www.anaconda.com/products/individual)
* Eclipse / IntelliJ IDEA
* Pycharm

## Framework & Library

* Spring Boot
* Spring Data JPA
* Swagger
* AWS SDK for Java
* Python 3 & boto3

## Repository

## Maven Repository

Custom Maven Repository를 사용하기 위해서 `<USER_HOME>/.m2/settings.xml` 파일을 다음과 같이 작성합니다.

```xml
<settings   xmlns="http://maven.apache.org/SETTINGS/1.0.0" 
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
            xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <localRepository>/data/www/html/maven</localRepository>
  <mirrors>
    <mirror>
      <id>datadynamics</id>
      <name>Data Dynamics Repo</name>
      <url>http://nexus.hadoop-professionals.org/repository/central/</url>
      <mirrorOf>central</mirrorOf>
    </mirror>
  </mirrors>
</settings>
```

### Pypi Repository

#### Mirroring

Pypi Repo를 미러링하기 위해서 다음의 커맨드를 입력합니다. 이 작업은 자체적으로 Pypi Repository를 서비스하는 경우에 해당합니다.

```
pip3 install python-pypi-mirror
pypi-mirror create -d downloads -m simple
python3 -m http.server
pip3 install -i http://127.0.0.1:8000/simple requests
```

#### Windows

`%APPDATA%\pip\pip.ini` 경로에 Custom Pypi Repository를 설정합니다.

```
[global]
index = http://nexus.hadoop-professionals.org/repository/pypi-central/pypi
index-url = http://nexus.hadoop-professionals.org/repository/pypi-central/simple
trusted-host = nexus.hadoop-professionals.org
```

#### Linux

`/etc/pip.conf` 파일을 다음과 같이 수정하여 Custom Pypi Repository를 설정합니다.

```
[global]
index = http://nexus.hadoop-professionals.org/repository/pypi-central/pypi
index-url = http://nexus.hadoop-professionals.org/repository/pypi-central/simple
trusted-host = nexus.hadoop-professionals.org
```

### YUM Repository

#### CentOS 7 YUM Repository

CentOS 7 또는 RHEL ISO 파일로 자체 Repostory를 구축하기 위해서 다음과 같이 작업합니다.
마운팅은 리부팅시 자동으로 되지 않도록 하려면 서비스 디렉토리에 ISO 파일의 내용을 복사하는 것을 권장합니다.

```
mkdir -p /var/www/html/centos
mount -o loop rhel-server-7.7-x86_64-dvd.iso /var/www/html/centos
yum install createrepo -y
cd /var/www/html/centos
createrepo .
```

`/etc/yum.repos.d/bigdata-centos7.repo` 파일을 다음과 같이 작성합니다.

```
[bigdata-centos7]
name=Big Data CentOS7 YUM Repository
baseurl=http://SERVER/centos/7/x86_64
gpgkey=http://SERVER/centos/RPM-GPG-KEY-EPEL-7
enabled=1
gpgcheck=0
```

#### CentOS 7 EPEL YUM Repository

CentOS 7 EPEL Repository를 미러링하기 위해서 CentOS에서 다음의 커맨드를 입력합니다.
그러면 EPEL Repository의 모든 내용이 리눅스 서버에 다운로드됩니다.

```
rsync -vtr --progress --exclude debug/ rsync://mirror.pnl.gov/epel/7/x86_64/ epel
```

`/etc/yum.repos.d/bigdata-epel.repo` 파일을 다음과 같이 작성합니다.

```
[bigdata-epel]
name=Big Data EPEL YUM Repository
baseurl=http://SERVER/epel/7/x86_64
gpgkey=http://SERVER/epel/RPM-GPG-KEY-EPEL-7
enabled=1
gpgcheck=0
```
