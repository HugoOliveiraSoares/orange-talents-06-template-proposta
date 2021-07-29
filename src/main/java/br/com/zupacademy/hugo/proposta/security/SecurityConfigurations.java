package br.com.zupacademy.hugo.proposta.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.POST, "/**").hasAuthority("ROLE_USER")
                .anyRequest()
                .authenticated()
             .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(getJwtAuthenticationConverter());


//        http.cors()
//             .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/**")
//                .hasAuthority("ROLE_USER")
//                .anyRequest()
//                .authenticated()
//             .and()
//                .oauth2ResourceServer()
//                .jwt()
//                .jwtAuthenticationConverter(getJwtAuthenticationConverter());
    }

    private JwtAuthenticationConverter getJwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthoritiesClaimName("authorities");
        converter.setAuthorityPrefix("");
        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(converter);
        return  authenticationConverter;
    }
}
