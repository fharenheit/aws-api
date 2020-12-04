package com.datadynamics.bigdata.api.util;

import com.amazonaws.services.identitymanagement.model.CreateUserResult;
import com.amazonaws.services.identitymanagement.model.transform.CreateUserResultStaxUnmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayInputStream;

public class XmlUtils {

    public static StaxUnmarshallerContext staxUnmarshallerContext(String xml) throws XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new ByteArrayInputStream(xml.getBytes()));
        return new StaxUnmarshallerContext(reader);
    }

    public static void main(String[] args) throws Exception {
        CreateUserResultStaxUnmarshaller unmarshaller = new CreateUserResultStaxUnmarshaller();
        StaxUnmarshallerContext context = XmlUtils.staxUnmarshallerContext("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns2:CreateUserResponse xmlns:ns2=\"https://iam.amazonaws.com/doc/2010-05-08/\"><CreateUserResult><User><Arn>arn:aws:iam::1339909887:user/fharenheit1</Arn><Path>/</Path><UserId>fharenheit1</UserId><UserName>fharenheit1</UserName></User></CreateUserResult><ResponseMetadata><RequestId>91748b83-ddf1-4924-be9e-e65a29b5c883</RequestId></ResponseMetadata></ns2:CreateUserResponse>");
        CreateUserResult r = unmarshaller.unmarshall(context);
        System.out.println(r);
    }

}
