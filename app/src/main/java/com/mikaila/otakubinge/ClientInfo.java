package com.mikaila.otakubinge;

/**
 * Created by Mikaila on 8/27/2017.
 */
/**
 * <h1>ClientInfo</h1>
 * Holds content of database - information for client and access token
 *
 * @author  Mikaila Smith
 * @version 1.0
 * @since   2017-08-27
 */

public class ClientInfo {
    private String accessToken;
    private Long tokenExpires;
    private String clientID;
    private String clientSecret;

    /**
     * Getter for access token
     * @return Access token
     */
    public String getAccessToken() { return accessToken; }

    /**
     * Getter for token expiration date/time
     * @return Token expiration time
     */
    public Long getTokenExpires() { return tokenExpires; }

    /**
     * Getter for client ID
     * @return Client ID
     */
    public String getClientID() { return clientID; }

    /**
     * Getter for client secret
     * @return Client secret
     */
    public String getClientSecret() { return clientSecret; }

    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
    public void setTokenExpires(Long tokenExpires) { this.tokenExpires = tokenExpires; }
    public void setClientID(String clientID) { this.clientID = clientID; }
    public void setClientSecret(String clientSecret) { this.clientSecret = clientSecret; }
}
