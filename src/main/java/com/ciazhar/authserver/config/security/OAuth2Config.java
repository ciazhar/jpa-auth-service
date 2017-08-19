package com.ciazhar.authserver.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * Created by ciazhar on 8/19/17.
 */

/**
 * Konfoguraso OAuth2
 */
@Configuration
public class OAuth2Config {
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        /**
         * Bean untuk mengatur otentifikasi client yang terdaftar
         */
        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        /**
         * Bean untuk mengatur JWT
         * @return
         */
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            KeyPair keyPair = new KeyStoreKeyFactory(
                    new ClassPathResource("ciazhar-security-keystore.jks"), "ciazhar14045".toCharArray())
                    .getKeyPair("ciazhar-security-keystore");
            converter.setKeyPair(keyPair);
            return converter;
        }

        /**
         * Konfigurasi Authorization Server Endpoint
         * @param endpoints
         * @throws Exception
         */
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .accessTokenConverter(jwtAccessTokenConverter())
                    .authenticationManager(authenticationManager);
        }

        /**
         * Konfigurasi Authorization Server Security
         * @param oauthServer
         * @throws Exception
         */
        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer
                    .checkTokenAccess("hasAuthority('CLIENT')")
                    .tokenKeyAccess("permitAll()");
        }

        /**
         * Daftar Client
         * @param clients
         * @throws Exception
         */
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient("nama-service")
                    .secret("123456")
                    .authorizedGrantTypes("authorization_code","refresh_token")
                    .authorities("CLIENT")
                    .scopes("read","write")
                    .and()
                    .withClient("nama-service-2")
                    .secret("123456")
                    .authorizedGrantTypes("authorization_code","refresh_token")
                    .authorities("CLIENT")
                    .scopes("read","write");
        }

    }
}
