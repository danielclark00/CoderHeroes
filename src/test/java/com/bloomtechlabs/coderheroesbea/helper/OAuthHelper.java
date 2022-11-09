package com.bloomtechlabs.coderheroesbea.helper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Component
@EnableAuthorizationServer
public class OAuthHelper extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthorizationServerTokenServices tokenservice;

    @Autowired
    ClientDetailsService clientDetailsService;

    public RequestPostProcessor bearerToken(final String clientid) {
        return mockRequest -> {
            OAuth2AccessToken token = createAccessToken(clientid);
            mockRequest.addHeader("Authorization", "Bearer " + token.getValue());
            return mockRequest;
        };
    }

    OAuth2AccessToken createAccessToken(final String clientId) {
        ClientDetails client = clientDetailsService.loadClientByClientId(clientId);
        Collection<GrantedAuthority> authorities = client.getAuthorities();
        Set<String> resourceIds = client.getResourceIds();
        Set<String> scopes = client.getScope();

        Map<String, String> requestParameters = Collections.emptyMap();
        boolean approved = true;
        String redirectUrl = null;
        Set<String> responseTypes = Collections.emptySet();
        Map<String, Serializable> extensionProperties = Collections.emptyMap();

        OAuth2Request oAuth2Request = new OAuth2Request(requestParameters, clientId, authorities,
                approved, scopes, resourceIds, redirectUrl, responseTypes, extensionProperties);

        User userPrincipal = new User("user", "", true, true, true, true, authorities);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userPrincipal, null, authorities);
        OAuth2Authentication auth = new OAuth2Authentication(oAuth2Request, authenticationToken);

        return tokenservice.createAccessToken(auth);
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("test")
                .authorities("READ");
    }

}
