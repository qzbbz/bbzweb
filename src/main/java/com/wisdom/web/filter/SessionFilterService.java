package com.wisdom.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wisdom.web.utils.SessionConstant;

public class SessionFilterService extends OncePerRequestFilter {

	private String[] filterUrls;

	public SessionFilterService() {
		filterUrls = new String[] { "/community", "/company", "/accounter",
				"/companyUser" };
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		boolean doFilter = false;
		for (String url : filterUrls) {
			if (uri.indexOf(url) != -1) {
				doFilter = true;
				break;
			}
		}
		if (doFilter) {
			Object userId = request.getSession().getAttribute(
					SessionConstant.SESSION_USER_ID);
			Object userTypeId = request.getSession().getAttribute(
					SessionConstant.SESSION_USER_TYPE);
			Object companyNotFinishRegister = request
					.getSession()
					.getAttribute(
							SessionConstant.SESSION_COMPANY_NOT_FINISH_REGISTER);
			if (null == userId) {
				boolean isAjaxRequest = isAjaxRequest(request);
				if (isAjaxRequest) {
					response.setCharacterEncoding("UTF-8");
					response.sendError(HttpStatus.UNAUTHORIZED.value(),
							"Unauthorized!");
					return;
				}
				response.sendRedirect("/views/webviews/no_login.html");
				return;
			} else if (companyNotFinishRegister != null) {
				response.sendRedirect("/views/webviews/company_detail_register.html");
				return;
			} else {
				boolean noAuth = false;
				int typeId = (Integer) userTypeId;
				switch (typeId) {
				case 1:
					if (uri.indexOf("/company") != -1
							|| uri.indexOf("/companyUser") != -1)
						noAuth = true;
					break;
				case 2:
					if (uri.indexOf("/accounter") != -1
							|| uri.indexOf("/community") != -1)
						noAuth = true;
					break;
				case 5:
					if (uri.indexOf("/accounter") != -1
							|| uri.indexOf("/community") != -1
							|| uri.indexOf("/companyUser") != -1)
						noAuth = true;
					break;
				}
				if (noAuth) {
					response.sendRedirect("/views/webviews/no_auth.html");
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