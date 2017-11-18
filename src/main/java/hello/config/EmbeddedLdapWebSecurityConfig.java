package hello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.naming.NamingException;

@Profile("embedded-ldap")
@Configuration
public class EmbeddedLdapWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmbeddedLdapAutoConfiguration embeddedLdapAutoConfiguration;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url(getEmbeddedLdapUrl() + "/dc=springframework,dc=org")
                .and()
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("userPassword");
    }

    private String getEmbeddedLdapUrl() throws NamingException {
        return (String)embeddedLdapAutoConfiguration
                .ldapContextSource()
                .getReadOnlyContext()
                .getEnvironment()
                .get("java.naming.provider.url");
    }
}
