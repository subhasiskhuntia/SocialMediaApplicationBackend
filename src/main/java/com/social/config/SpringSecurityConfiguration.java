package com.social.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.social.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService((UserDetailsService) userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		// I will change this later
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable()
				.authorizeRequests().antMatchers("/helloadmin").hasRole("ADMIN")
				.antMatchers(
						HttpMethod.OPTIONS,
						"/hellouser",
						"/sayHello",
						"/server1/**",
						"/app/topic/return-to/{recipient}",
						"/getPreviousContactedPerson/{sender}",
						"/previousMessage/{sender}/{receiver}",
						"/api/user/post",
						"/api/user/userPosts",
						"/api/user/sendFriendRequest",
						"/api/user/friends",
						"/api/user/pendingFriendRequest",
						"/api/user/acceptFriendRequest",
						"/api/user/postForUser",
						"/api/user/likePost",
						"/api/user/getUserLikes",
						"/api/user/removeLike",
						"/api/user/addComment",
						"/api/user/commentOnAPost",
						"/api/user/getUserComments",
						"/api/user/getUsersLikeOnPosts",
						"/api/user/loadDifferentUserById"
						)
						.hasAnyRole("USER", "ADMIN")
						.antMatchers("/authenticate",
						"/api/user/suggestedFriends",
						"/register",
						"/refreshtoken",
						"/api/user/getUserDetails",
						"/api/user/changeUserDetails",
						"/api/user/changePassword",
						"/sendMail",
						"/api/user/checkOtp")
				.permitAll().anyRequest().authenticated()
				.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v3/api-docs/**",
				"/swagger-ui/**",
				"/swagger-resources/**",
				"/configuration/security",
				"/swagger-ui.html",
				"/webjars/**");
	}

}
