package com.cv.kdata.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cv.kdata.cache.LoginInfoCache;
import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.response.ResponseObject;
import com.cv.kdata.util.StringUtil;
import com.jfinal.kit.JsonKit;

public class LoginValidation implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object token = request.getSession().getAttribute(RDDWebConst.TOKEN);
		String path = request.getRequestURI();
		if (!StringUtil.isNullOrEmpty(path) && path.contains("html")) {
			return true;
		}
		ResponseObject basicResponse = new ResponseObject();
		if (token == null) {
			basicResponse.setStatus(RDDWebConst.TIMEOUT);
			basicResponse.setMessage("Please relogin");
			// response.sendRedirect("/index.html");
			response.getWriter().print(JsonKit.toJson(basicResponse));
			return false;
		} else if (StringUtil.isNullOrEmpty(LoginInfoCache.getInstance().getUid((String) token))) {
			basicResponse.setStatus(RDDWebConst.TIMEOUT);
			basicResponse.setMessage("already logout!");
			// response.sendRedirect("/index.html");
			response.getWriter().print(JsonKit.toJson(basicResponse));
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// System.out.println("postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println("afterCompletion");
	}
}
