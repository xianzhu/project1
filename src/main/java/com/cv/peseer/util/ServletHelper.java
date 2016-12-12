package com.cv.peseer.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.cv.peseer.response.ResponseObject;
import com.jfinal.kit.JsonKit;


public class ServletHelper {
	public static void writeResponse(HttpServletResponse resp, String callback, Object loginResponse)
			throws IOException {
		String output = null;
		if (callback != null && !callback.isEmpty()) {
			output = String.format("%s(%s)", callback,JsonKit.toJson(loginResponse));
		} else {
			output = JsonKit.toJson(loginResponse);
		}
		resp.getWriter().print(output);
	}
	public static boolean paramenterVerification(String token, ResponseObject response){
	 if (!AuthcationUtil.isTokenValid(token)) {
		 response.setStatus("failure");
		 response.setMessage("未登录或者登录已经失效");
		 return false;
	 }
	 	return true;
	}
}
