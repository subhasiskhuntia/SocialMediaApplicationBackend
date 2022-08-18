package com.social.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.social.bean.AuthenticationRequest;
import com.social.bean.AuthenticationResponse;
import com.social.dao.UserDao;
import com.social.entity.User;
import com.social.service.CustomUserDetailsService;

import io.jsonwebtoken.impl.DefaultClaims;

// import com.ecom.bean.AuthenticationRequest;
// import com.ecom.bean.AuthenticationResponse;
// import com.ecom.bean.Login;
// import com.ecom.dao.UserDAO;
// import com.ecom.entity.User;
// import com.ecom.serviceImpl.CustomUserDetailsService;

// import io.jsonwebtoken.impl.DefaultClaims;

@RestController
// @CrossOrigin
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", maxAge = 1800)
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDao userdao;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		System.out.println(authenticationRequest.getPassword() + authenticationRequest.getUsername() + "hi");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			System.out.println("disabled");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			System.out.println("invalid password");
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		System.out.println(userdetails);
		String token = jwtUtil.generateToken(userdetails);
		String userName = userdetails.getUsername();
		User user = userdao.findByUsername(userName);
		return ResponseEntity.ok(new AuthenticationResponse(token, userName, user.getFirstName(), "user"));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
		System.out.println(user);
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	@GetMapping(value = "sayHello")
	public String sayHello() {
		return "say hello";
	}

	@RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
		System.out.println(request.getAttribute("claims"));
		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		return ResponseEntity.ok(AuthenticationResponse.builder().token(token).build());
	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}
}
