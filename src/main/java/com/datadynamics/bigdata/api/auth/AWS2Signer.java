package com.datadynamics.bigdata.api.auth;

import com.amazonaws.SignableRequest;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.SigningAlgorithm;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.internal.RestUtils;
import com.amazonaws.services.s3.internal.ServiceUtils;
import com.amazonaws.util.SdkHttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @see <a href="https://docs.aws.amazon.com/ko_kr/AmazonS3/latest/dev/RESTAuthentication.html#RESTAuthenticationExamples">REST Request Authorization</a>
 */
public class AWS2Signer extends AbstractAWSSigner {

    private static final Log log = LogFactory.getLog(AWS2Signer.class);

    /**
     * The HTTP verb (GET, PUT, HEAD, DELETE)
     */
    private final String httpVerb;

    /**
     * Canonical Resource path : "/", "/<bucket name>/", or "/<bucket name>/<key>"
     */
    private final String resourcePath;

    /**
     * 사용자 정의 Query Parameter명. Canonical Request에 포함되어야 함.
     */
    private final Set<String> additionalQueryParamsToSign;

    public AWS2Signer() {
        this.httpVerb = null;
        this.resourcePath = null;
        this.additionalQueryParamsToSign = null;
    }

    public static AWS2Signer createAWSS2Signer(HttpServletRequest request, String serverName) {
        String key = request.getRequestURI();
        String bucketName = getBucketName(request, serverName);
        return createAWSS2Signer(request, bucketName, key);
    }

    /**
     * Bucket Name을 반환한다.
     *
     * @param request    HTTP Servlet Request
     * @param serverName 서비스를 하는 서버의 호스트명
     * @return Bucket Name
     */
    private static String getBucketName(HttpServletRequest request, String serverName) {
        String hostname = request.getHeader("host");
        return StringUtils.replace(hostname, "." + serverName, "");
    }

    public static AWS2Signer createAWSS2Signer(HttpServletRequest request, final String bucketName, final String key) {
        String resourcePath = "/" + ((bucketName != null) ? bucketName + "/" : "") + ((key != null) ? key : "");
        return new AWS2Signer(request.getMethod(), resourcePath);
    }

    /**
     * @param httpVerb     HTTP verb (GET, PUT, POST, HEAD, DELETE)
     * @param resourcePath Canonical S3 resource path (ex: "/", "/<bucket name>/", or "/<bucket name>/<key>".
     */
    public AWS2Signer(String httpVerb, String resourcePath) {
        this(httpVerb, resourcePath, null);
    }

    /**
     * @param httpVerb                    HTTP verb (GET, PUT, POST, HEAD, DELETE)
     * @param resourcePath                Canonical S3 resource path (ex: "/", "/<bucket name>/", or "/<bucket name>/<key>".
     * @param additionalQueryParamsToSign 사용자 정의 Query Parameter명
     */
    public AWS2Signer(String httpVerb, String resourcePath, Collection<String> additionalQueryParamsToSign) {
        if (resourcePath == null) {
            throw new IllegalArgumentException("Parameter resourcePath is empty");
        }

        this.httpVerb = httpVerb;
        this.resourcePath = resourcePath;
        this.additionalQueryParamsToSign = additionalQueryParamsToSign == null
                ? null
                : Collections.unmodifiableSet(new HashSet<String>(
                additionalQueryParamsToSign));
    }


    @Override
    public void sign(SignableRequest<?> request, AWSCredentials credentials, String authorizationHeader) {
        if (resourcePath == null) {
            throw new UnsupportedOperationException("Cannot sign a request using a dummy S3Signer instance with no resource path");
        }

        if (credentials == null || credentials.getAWSSecretKey() == null) {
            log.debug("Canonical string will not be signed, as no AWS Secret Key was provided");
            return;
        }

        AWSCredentials sanitizedCredentials = sanitizeCredentials(credentials);
        if (sanitizedCredentials instanceof AWSSessionCredentials) {
            addSessionCredentials(request, (AWSSessionCredentials) sanitizedCredentials);
        }

        /*
         * In s3 sigv2, the way slash characters are encoded should be
         * consistent in both the request url and the encoded resource path.
         * Since we have to encode "//" to "/%2F" in the request url to make
         * httpclient works, we need to do the same encoding here for the
         * resource path.
         */
        String encodedResourcePath = SdkHttpUtils.appendUri(request.getEndpoint().getPath(), SdkHttpUtils.urlEncode(resourcePath, true), true);

        int timeOffset = request.getTimeOffset();
        Date date = getSignatureDate(timeOffset);
        request.addHeader(Headers.DATE, ServiceUtils.formatRfc822Date(date));
        String canonicalString = RestUtils.makeS3CanonicalString(httpVerb, encodedResourcePath, request, null, additionalQueryParamsToSign);
        log.debug("Calculated string to sign:\n\"" + canonicalString + "\"");

        String signature = super.signAndBase64Encode(canonicalString, sanitizedCredentials.getAWSSecretKey(), SigningAlgorithm.HmacSHA1);
        request.addHeader("Authorization", "AWS " + sanitizedCredentials.getAWSAccessKeyId() + ":" + signature);

    }

    @Override
    protected void addSessionCredentials(SignableRequest<?> request, AWSSessionCredentials credentials) {
        request.addHeader("x-amz-security-token", credentials.getSessionToken());
    }
}
