# 개발자 가이드

## Repository

## Naveb Repository

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

`/etc/pip.conf` 파일을 다음과 같이 수정하여 Custom Pypi Repository를 설정합니다.

```
[global]
index = http://nexus.hadoop-professionals.org/repository/pypi-central/pypi
index-url = http://SERVER/repository/pypi-central/simple
trusted-host = pypi.python.org
                       pypi.org
                       files.pythonhosted.org
                       nexus.hadoop-professionals.org
```
