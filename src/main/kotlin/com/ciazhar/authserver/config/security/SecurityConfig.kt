package com.ciazhar.authserver.config.security

/**
 * Created by ciazhar on 8/19/17.
 */

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.filter.CompositeFilter

import javax.servlet.Filter
import javax.sql.DataSource
import java.util.ArrayList

/**
 * Konfigurasi Security Secara Umum
 */
@EnableOAuth2Client
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity(debug = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {
    /**
     * Datasource untuk koneksi database
     */
    @Autowired
    private val dataSource: DataSource? = null

    /**
     * Bean untuk mengatur otentifikasi client yang terdaftar
     */
    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    /**
     * Bean untuk password encoding
     * @return
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(
        )
    }


    /**
     * Konfigurasi Otorisasi
     * @param auth
     * @throws Exception
     */
    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION)
                .groupAuthoritiesByUsername(SQL_ROLE)
                .passwordEncoder(passwordEncoder())
    }


    @Autowired
    internal var oauth2ClientContext: OAuth2ClientContext? = null

    /**
     * Resource untuk SSO ke github
     * @return
     */
    @Bean
    @ConfigurationProperties("github")
    fun github(): ClientResources {
        return ClientResources()
    }

    /**
     * Resource untuk SSO ke facebook
     * @return
     */
    @Bean
    @ConfigurationProperties("facebook")
    fun facebook(): ClientResources {
        return ClientResources()
    }


    /**
     * Mendaftarkan Resouce untuk SSO
     * @return
     */
    private fun ssoFilter(): Filter {
        val filter = CompositeFilter()
        val filters = ArrayList<Filter>()

        filters.add(ssoFilter(facebook(), "/login/facebook"))
        filters.add(ssoFilter(github(), "/login/github"))
        filter.setFilters(filters)
        return filter
    }

    /**
     * Implementasi SSO
     * @param client
     * @param path
     * @return
     */
    private fun ssoFilter(client: ClientResources, path: String): Filter {
        val filter = OAuth2ClientAuthenticationProcessingFilter(path)
        val template = OAuth2RestTemplate(client.client, oauth2ClientContext)
        filter.setRestTemplate(template)
        val tokenServices = UserInfoTokenServices(
                client.resource.userInfoUri, client.client.clientId)
        tokenServices.setRestTemplate(template)
        filter.setTokenServices(tokenServices)
        return filter
    }


    /**
     * Bean untuk konfigurasi http
     * @param http
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/login**").permitAll()
                .antMatchers("/css/**", "/js/**").permitAll()
                .antMatchers("/api/user/register").permitAll()
                .antMatchers("/mobile/user/register").permitAll()
                .antMatchers("/activate**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/api/user").permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().ignoringAntMatchers("/mobile/**")
                .and()
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter::class.java)

    }

    /**
     * Bean untuk filter
     * @param filter
     * @return
     */
    @Bean
    fun oauth2ClientFilterRegistration(
            filter: OAuth2ClientContextFilter): FilterRegistrationBean {
        val registration = FilterRegistrationBean()
        registration.filter = filter
        registration.order = -100
        return registration
    }

    /**
     * Class untuk mendefinisikan Resource untuk SSO
     */
    internal inner class ClientResources {

        @NestedConfigurationProperty
        val client = AuthorizationCodeResourceDetails()

        @NestedConfigurationProperty
        val resource = ResourceServerProperties()
    }

    companion object {

        /**
         * SQL Query untuk otorisasi
         */
        private val SQL_LOGIN = "select username, password, enabled " + "from user where username = ?"

        private val SQL_PERMISSION =
                "select u.username as username, p.nama_permission as authority " +
                        "from user u , permission p " +
                        "where u.username = ?"

        private val SQL_ROLE =
                "select r.id_role, r.nama_role, p.nama_permission " +
                        "from role r, permission p, user u " +
                        "where u.username = ?  " +
                        "and r.id_role = u.id_role " +
                        "and r.id_role = p.id_role"
    }

}

