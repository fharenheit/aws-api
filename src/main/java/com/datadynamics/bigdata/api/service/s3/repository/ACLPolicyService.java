package com.datadynamics.bigdata.api.service.s3.repository;

import com.datadynamics.bigdata.api.service.s3.model.acl.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ACLPolicyService {

    @Autowired
    private ACLPolicyRepository policyRepository;

    @Autowired
    private ACLPolicyDetailRepository policyDetailRepository;

    private XmlMapper mapper;

    public String getACL(String username, String bucketName, String key, String ownerName) {
        Optional<ACLPolicy> aclPolicy = policyRepository.findByBucketNameAndKeyAndOwnerName(bucketName, StringUtils.hasLength(key) ? key : "", ownerName);

        if (aclPolicy.isPresent()) {
            throw new IllegalArgumentException("이미 ACL이 적용되어 있습니다.");
        }

        return aclPolicy.get().getPolicyDocument();
    }

    public String putACL(String username, String bucketName, String key, String aclXml) {
        if (!StringUtils.hasLength(aclXml)) {
            throw new IllegalArgumentException("ACL XML이 필요합니다.");
        }

        try {
            AccessControlPolicy accessControlPolicy = mapper.readValue(aclXml, AccessControlPolicy.class);

            // Username은 요청자이지만 OwnerName은 실제 호출한 소유자가 된다.
            // 따라서 OwnerName은 실제 소유자의 Username이라고 봐도 무방하다.
            String ownerName = accessControlPolicy.getOwner().getID();

            Optional<ACLPolicy> aclPolicy = policyRepository.findByBucketNameAndKeyAndOwnerName(bucketName, key, ownerName);
            if (aclPolicy.isPresent()) {
                throw new IllegalArgumentException("이미 ACL이 적용되어 있습니다.");
            }

            String uuid = UUID.randomUUID().toString();
            policyRepository.save(ACLPolicy.builder()
                    .uuid(uuid)
                    .policyDocument(aclXml)
                    .bucketName(bucketName)
                    .username(username)
                    .key(StringUtils.hasLength(key) ? key : "") // PutBucketAcl
                    .ownerName(ownerName)
                    .build());

            AccessControlList accessControlList = accessControlPolicy.getAccessControlList();
            List<Grant> grants = accessControlList.getGrant();
            for (Grant grant : grants) {

                Map grantee = grant.getGrantee();
                ACLPolicyDetail detail = ACLPolicyDetail.builder()
                        .uuid(uuid)
                        .displayName(get(grantee, "DisplayName", ""))
                        .emailAddress(get(grantee, "EmailAddress", ""))
                        .ownerName(ownerName)
                        .permission(grant.getPermission())
                        .type(get(grantee, "type", ""))
                        .uri(get(grantee, "URK", ""))
                        .build();
                policyDetailRepository.save(detail);
            }

            return uuid;
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("ACL XML의 형식이 잘못되었습니다.");
        }
    }

    public String get(Map map, String key, String defaultValue) {
        return map.get(key) != null ? (String) map.get(key) : defaultValue;
    }

    @PostConstruct
    public void postConstruct() {
        this.mapper = new XmlMapper();
    }

}
