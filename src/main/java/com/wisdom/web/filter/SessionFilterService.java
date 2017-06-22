package com.wisdom.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wisdom.web.api.controller.UserValidateController;
import com.wisdom.web.utils.SessionConstant;

public class SessionFilterService extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory
			.getLogger(SessionFilterService.class);
	
	private String[] filterUrls;

	public SessionFilterService() {
		filterUrls = new String[] { "/admin/", "/company/", "/accounter/",
				"/companyUser/", "/invoiceupload/"};
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		boolean doFilter = false;
		if(uri.indexOf("/files/company") == -1) {
			for (String url : filterUrls) {
				if (uri.indexOf(url) != -1) {
					doFilter = true;
					break;
				}
			}
		}
		if (doFilter) {
			Object userId = request.getSession().getAttribute(
					SessionConstant.SESSION_USER_ID);
			Object userTypeId = request.getSession().getAttribute(
					SessionConstant.SESSION_USER_TYPE);
			if (null == userId) {
				logger.debug("user id is null.");
				boolean isAjaxRequest = isAjaxRequest(request);
				if (isAjaxRequest) {
					response.setCharacterEncoding("UTF-8");
					response.sendError(HttpStatus.UNAUTHORIZED.value(),
							"Unauthorized!");
					return;
				}
				response.sendRedirect("/views/frontviews/no_login.html");
				return;
			} else {
				boolean noAuth = false;
				int typeId = (Integer) userTypeId;
				switch (typeId) {
				case 1:
					if (uri.indexOf("/accounter/") == -1)
						noAuth = true;
					break;
				case 2:
					if (uri.indexOf("/company/") == -1)
						noAuth = true;
					break;
				case 3:
					if (uri.indexOf("/admin/") == -1)
						noAuth = true;
					break;
				case 5:
					if (uri.indexOf("/companyUser/") == -1)
						noAuth = true;
					break;
				case 8:
					if (uri.indexOf("/invoiceupload/") == -1)
						noAuth = true;
					break;
				case 9:
					if (uri.indexOf("/admin/") == -1)
						noAuth = true;
					break;
				default:
					noAuth = true;
				}
				if (noAuth) {
					logger.debug("no auth.");
					response.sendRedirect("/views/frontviews/no_login.html");
					return;
				}
			}
		}
		filterChain.doFilter(request, response);
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}

}