package com.ninePixels.poetry.authserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SigningKeyConfig {
	
	@Value("${signing.key}")
	private String signingkey;
	
	public String getJwtSigningKey() {
		return signingkey;
	}

}
