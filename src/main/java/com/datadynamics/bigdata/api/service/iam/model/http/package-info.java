/**
 * HTTP Request & Response를 위한 Model 패키지.
 * AWS에서 제공하는 서비스에 따라서 Response 형식이 XML 또는 JSON일 수 있으므로
 * JSON인 경우 Spring에서 기본 지원하는 Jackson의 Annotation을 사용하도록 하고
 * XML인 경우 JAXB 표준에 따라서 JAXB Annoation을 사용하도록 한다.
 * 내부 협의를 거쳐 Jackson의 XML Binding 기능을 이용하고자 한다면
 * JAXB 대신 Jackson으로 사용해도 무방하다.
 */
package com.datadynamics.bigdata.api.service.iam.model.http;