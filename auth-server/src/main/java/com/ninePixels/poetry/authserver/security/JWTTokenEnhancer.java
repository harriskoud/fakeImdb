package com.ninePixels.poetry.authserver.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.ninePixels.poetry.authserver.userDetails.User;
import com.ninePixels.poetry.authserver.userDetails.UserRepository;

@Component
public class JWTTokenEnhancer implements TokenEnhancer {

	@Autowired
	private UserRepository userRepository;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		User user = userRepository.findByUsername(authentication.getName());
		if (user == null) {
			throw new UsernameNotFoundException(authentication.getName());
		}

		Map<String, Object> additionalInfo = new HashMap<>();
		additionalInfo.put("username", authentication.getName());
		additionalInfo.put("userId", user.getUserId());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}
}
