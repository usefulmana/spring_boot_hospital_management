package rmit.spring.hospital.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(encodePassword());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .authorizeRequests().antMatchers("**/admin/**").authenticated().anyRequest().hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("**/visits/**").authenticated().anyRequest().permitAll()
                .and()
                .authorizeRequests().antMatchers("**/patients/**").authenticated().anyRequest().permitAll()
                .and()
                .authorizeRequests().antMatchers("**/diseases/**").authenticated().anyRequest().permitAll()
                .and()
                .authorizeRequests().antMatchers("**/drugs/**").authenticated().anyRequest().permitAll()
                .and()
                .authorizeRequests().antMatchers("**/prescriptions/**").authenticated().anyRequest().hasAnyRole("ADMIN", "DOCTOR")
                .and()
                .authorizeRequests().antMatchers("**/diagnosis/**").authenticated().anyRequest().hasAnyRole("ADMIN", "DOCTOR")
                .and()
                .authorizeRequests().antMatchers("**/labs/**").authenticated().anyRequest().hasAnyRole("ADMIN", "DOCTOR")
                .and()
                .authorizeRequests().antMatchers("**/tests/**").authenticated().anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();;
    }

    @Bean
    public BCryptPasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }
}
