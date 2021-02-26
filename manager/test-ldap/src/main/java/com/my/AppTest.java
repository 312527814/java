package com.my;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Hello world!
 */
@SpringBootApplication
public class AppTest {
    public static void main(String[] args) {
        SpringApplication.run(AppTest.class, args);
    }

    @Bean
    public ApplicationRunner getBean(LdapTemplate ldapTemplate) {
        return args -> {
            System.out.println("dsds");
            ldapAuth(ldapTemplate);
            List<Map<String, String>> search = search(ldapTemplate);



        };
    }

    //登录
    private void ldapAuth(LdapTemplate ldapTemplate) {
        //ldap 账号
        String loginName="";
        String password ="";

        EqualsFilter filter = new EqualsFilter("uid", loginName);
        AbstractContextSource contextSource = (AbstractContextSource) ldapTemplate.getContextSource();
        contextSource.setPassword(password);
        contextSource.setUserDn("uid=" + loginName + ",dc=iKang,dc=com");
        ldapTemplate.authenticate("", filter.toString(), password);
    }

    //查询
    private  List<Map<String, String>>  search(LdapTemplate ldapTemplate){
        LdapQuery query = query()
                .attributes("uid", "displayName", "cn", "sn", "mail")
                .where("displayName").like("吴剑锋");
        List<Map<String, String>> result = ldapTemplate.search(query, new AttributesMapper() {
            @Override
            public Map<String, String> mapFromAttributes(Attributes attributes) throws NamingException {
                attributes.get("displayname").get();
                Map<String, String> map = new HashMap();
                map.put("cn", attributes.get("cn").get().toString());
                map.put("displayname", attributes.get("displayname").get().toString());
                map.put("mail", attributes.get("mail").get().toString());
                map.put("sn", attributes.get("sn").get().toString());
                map.put("uid", attributes.get("uid").get().toString());
                return map;
            }
        });

        return result;
    }

    @Bean
    public LdapContextSource ldapContextSource() {
        LdapContextSource source = new LdapContextSource();
        source.setBase("dc=iKang,dc=com");
        source.setUrl("ldap://ldap.it.ikang.com:389/");
        return source;
    }

    @Bean
    public LdapTemplate ldapTemplate(LdapContextSource ldapContextSource) {
        return new LdapTemplate(ldapContextSource);
    }
}
