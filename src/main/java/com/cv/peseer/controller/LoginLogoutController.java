package com.cv.peseer.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cv.peseer.response.LoginLogoutResponse;
import com.cv.peseer.service.LoginLogoutService;

@Controller
public class LoginLogoutController {
	@Autowired
	LoginLogoutService service;
	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public LoginLogoutResponse login(HttpServletRequest request,Model model){
		LoginLogoutResponse response = new LoginLogoutResponse();
		service.login(request, response);
		return response;
	}
	
	@RequestMapping(value="/logout",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public LoginLogoutResponse logout(HttpServletRequest request,Model model){
		LoginLogoutResponse response = new LoginLogoutResponse();
		service.logout(request, response);
		return response;
	}
}
