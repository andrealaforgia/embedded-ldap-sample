package hello.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="ldap.server")
public class LdapServerProperties {
    String url;
    String dnPatterns;
    String baseDn;
    String passwordAttribute;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDnPatterns() {
        return dnPatterns;
    }

    public void setDnPatterns(String dnPatterns) {
        this.dnPatterns = dnPatterns;
    }

    public String getBaseDn() {
        return baseDn;
    }

    public void setBaseDn(String baseDn) {
        this.baseDn = baseDn;
    }

    public String getPasswordAttribute() {
        return passwordAttribute;
    }

    public void setPasswordAttribute(String passwordAttribute) {
        this.passwordAttribute = passwordAttribute;
    }
}
