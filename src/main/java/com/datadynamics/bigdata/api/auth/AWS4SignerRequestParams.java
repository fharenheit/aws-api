package com.datadynamics.bigdata.api.auth;

import com.amazonaws.SignableRequest;
import com.amazonaws.auth.SdkClock;
import com.amazonaws.auth.internal.SignerConstants;
import com.amazonaws.util.endpoint.DefaultRegionFromEndpointResolver;
import com.amazonaws.util.endpoint.RegionFromEndpointResolver;

import java.util.Date;

/**
 * Parameters that are used for computing a AWS 4 signature for a request.
 */
public final class AWS4SignerRequestParams {

    /**
     * The request for which the signature needs to be computed.
     */
    private final SignableRequest<?> request;

    /**
     * The datetime in milliseconds for which the signature needs to be
     * computed.
     */
    private final long signingDateTimeMilli;

    /**
     * The scope of the signature.
     */
    private final String scope;

    /**
     * The AWS region to be used for computing the signature.
     */
    private final String regionName;

    /**
     * The name of the AWS service.
     */
    private final String serviceName;

    /**
     * UTC formatted version of the signing time stamp.
     */
    private final String formattedSigningDateTime;

    /**
     * UTC Formatted Signing date with time stamp stripped
     */
    private final String formattedSigningDate;

    /**
     * The signing algorithm to be used for computing the signature.
     */
    private final String signingAlgorithm;

    /**
     * Generates an instance of AWS4signerRequestParams that holds the
     * parameters used for computing a AWS 4 signature for a request
     */
    public AWS4SignerRequestParams(SignableRequest<?> request,
                                   Date signingDateOverride, String regionNameOverride,
                                   String serviceName, String signingAlgorithm) {
        this(request, signingDateOverride, regionNameOverride,
                serviceName, signingAlgorithm, null);
    }

    /**
     * Generates an instance of AWS4signerRequestParams that holds the
     * parameters used for computing a AWS 4 signature for a request
     */
    public AWS4SignerRequestParams(SignableRequest<?> request,
                                   Date signingDateOverride, String regionNameOverride,
                                   String serviceName, String signingAlgorithm,
                                   String endpointPrefix) {
        this(request, signingDateOverride, regionNameOverride, serviceName, signingAlgorithm, endpointPrefix, null);
    }

    /**
     * Generates an instance of AWS4signerRequestParams that holds the
     * parameters used for computing a AWS 4 signature for a request
     */
    public AWS4SignerRequestParams(SignableRequest<?> request,
                                   Date signingDateOverride, String regionNameOverride,
                                   String serviceName, String signingAlgorithm,
                                   String endpointPrefix,
                                   RegionFromEndpointResolver regionFromEndpointResolver) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (signingAlgorithm == null) {
            throw new IllegalArgumentException("Signing Algorithm cannot be null");
        }

        this.request = request;
        this.signingDateTimeMilli = signingDateOverride != null ? signingDateOverride.getTime() : getSigningDate(request);
        this.formattedSigningDateTime = request.getHeaders().get("X-Amz-date");
        this.formattedSigningDate = this.formattedSigningDateTime.substring(0, 8);

        this.serviceName = serviceName;

        this.regionName = regionNameOverride != null ? regionNameOverride : resolveRegion(regionFromEndpointResolver, endpointPrefix, this.serviceName);

        this.scope = generateScope(request, formattedSigningDate, this.serviceName, regionName);
        this.signingAlgorithm = signingAlgorithm;
    }

    /*
     * Ideally, we should be using endpoint prefix to parse the region from host.
     *
     * Previously we were using service signing name to parse region. It is possible that
     * endpoint prefix is null if customers are still using older clients. So using
     * service signing name as alternative will prevent any behavior breaking change.
     */
    private String resolveRegion(RegionFromEndpointResolver resolver, String endpointPrefix, String serviceSigningName) {
        if (resolver == null) {
            resolver = new DefaultRegionFromEndpointResolver();
        }
        String host = request.getEndpoint().getHost();
        String region = resolver.guessRegionFromEndpoint(host, endpointPrefix != null ? endpointPrefix : serviceSigningName);
        return region != null ? region : "us-east-1";
    }

    /**
     * Returns the signing date from the request.
     */
    private final long getSigningDate(SignableRequest<?> request) {
        return SdkClock.Instance.get().currentTimeMillis() - request.getTimeOffset() * 1000L;
    }

    /**
     * Returns the scope to be used for the signing.
     */
    private String generateScope(SignableRequest<?> request, String dateStamp,
                                 String serviceName, String regionName) {
        final StringBuilder scopeBuilder = new StringBuilder();
        return scopeBuilder.append(dateStamp).append("/").append(regionName)
                .append("/").append(serviceName).append("/")
                .append(SignerConstants.AWS4_TERMINATOR).toString();
    }

    /**
     * Returns the request for which the signing needs to be done.
     */
    public SignableRequest<?> getRequest() {
        return request;
    }

    /**
     * Returns the scope of the signing.
     */
    public String getScope() {
        return scope;
    }

    /**
     * Returns the formatted date and time of the signing date in UTC zone.
     */
    public String getFormattedSigningDateTime() {
        return formattedSigningDateTime;
    }

    /**
     * Returns the signing date time in millis for which the signature needs to
     * be computed.
     */
    public long getSigningDateTimeMilli() {
        return signingDateTimeMilli;
    }

    /**
     * Returns the AWS region name to be used while computing the signature.
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * Returns the AWS Service name to be used while computing the signature.
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Returns the formatted date in UTC zone of the signing date.
     */
    public String getFormattedSigningDate() {
        return formattedSigningDate;
    }

    /**
     * Returns the signing algorithm used for computing the signature.
     */
    public String getSigningAlgorithm() {
        return signingAlgorithm;
    }
}
