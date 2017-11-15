package fr.april.gps.webapi.common.configurations.security;

import fr.april.gps.webapi.common.configurations.security.services.ICryptoService;
import fr.april.gps.webapi.common.exceptions.AbstractException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * by osow on 15/11/17.
 * for GPS
 */

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationFilter extends GenericFilterBean {

	private final static String LOG_HEADER = "[SECURITY][AUTHENTICATION]";

	private final ICryptoService cryptoService;

	@Value("${april.gps.token.header.name}")
	private String TOKEN_HEADER_NAME;

	@Autowired
	public AuthenticationFilter(ICryptoService cryptoService) {
		this.cryptoService = cryptoService;
	}


	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
					IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		final String token = request.getHeader(TOKEN_HEADER_NAME);
		if (StringUtils.isBlank(token)) {
			log.trace("{} No token found in request header", LOG_HEADER);
			filterChain.doFilter(request, response);
			return;
		}

		try {
//			TokenWrapper tokenWrapper = cryptoService.parseUserProfileFromToken(token);

		} catch (AbstractException ae) {
			log.trace("{} Failed to decode token {}", LOG_HEADER, token);
			filterChain.doFilter(request, response);
		}


	}
}
