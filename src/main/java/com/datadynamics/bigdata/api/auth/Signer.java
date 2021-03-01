package com.datadynamics.bigdata.api.auth;

import com.amazonaws.SignableRequest;
import com.amazonaws.auth.AWSCredentials;

/**
 * A strategy for applying cryptographic signatures to a request, proving
 * that the request was made by someone in posession of the given set of
 * credentials without transmitting the secret key over the wire.
 */
public interface Signer {

    /**
     * Sign the given request with the given set of credentials. Modifies the
     * passed-in request to apply the signature.
     *
     * @param request     The request to sign.
     * @param credentials The credentials to sign the request with.
     */
    public void sign(SignableRequest<?> request, AWSCredentials credentials, String authorizationHeader);
}
