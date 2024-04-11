package com.geomhwein.go.securlty;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailure extends SimpleUrlAuthenticationFailureHandler {

	private String redirectURL;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    AuthenticationException exception) throws IOException, ServletException {



		response.sendRedirect(redirectURL);
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
}
