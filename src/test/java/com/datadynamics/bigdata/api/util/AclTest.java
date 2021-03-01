package com.datadynamics.bigdata.api.util;

import com.datadynamics.bigdata.api.util.acl.AccessControlPolicy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class AclTest {

    @Test
    public void read() throws IOException, JAXBException {
        DefaultResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("classpath:/acl.xml");

        XmlMapper xmlMapper = new XmlMapper();

        AccessControlPolicy map = xmlMapper.readValue(resource.getFile(), AccessControlPolicy.class);
        System.out.println(map);

/*
        AccessControlPolicy accessControlPolicy = new AccessControlPolicy();
        if (map.get("Owner") != null) {
            Owner owner = new Owner();
            Map ownerMap = (Map) map.get("Owner");
            owner.setID(StringUtils.hasLength((String) ownerMap.get("ID")) ? (String) ownerMap.get("ID") : "");
            owner.setDisplayName(StringUtils.hasLength((String) ownerMap.get("DisplayName")) ? (String) ownerMap.get("DisplayName") : "");
            accessControlPolicy.setOwner(owner);
        }

        if (map.get("AccessControlList") != null) {
            Map accessControlList = (Map) map.get("AccessControlList");
            System.out.println(map.get("AccessControlList"));
            Map grantMap = (Map) accessControlList.get("Grant");
            System.out.println(grantMap);
        }
*/

    }
}
