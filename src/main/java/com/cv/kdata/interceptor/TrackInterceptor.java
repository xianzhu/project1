package com.cv.kdata.interceptor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.util.StringUtil;
import com.cv.kdata.util.TimeUtil;
import com.kdata.defined.model.TrackModel;
import com.kdata.track.KafkaProducerHelper;

public class TrackInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			String uri = request.getRequestURI();

			// logout 操作的时候会清除session信息，所以在preHandle里面记录日志
			if (!StringUtil.isNullOrEmpty(uri) && uri.contains("logout")) {

				TrackModel model = new TrackModel();
				model.setUri(request.getRequestURI());
				if (request.getSession() != null) {
					model.setSession((String) request.getSession().getAttribute(RDDWebConst.TOKEN));
					model.setUserName((String) request.getSession().getAttribute(RDDWebConst.USERNAME));
				}
				model.addHeader(request);
				model.setParas(request.getParameterMap());
				model.setTime(TimeUtil.getCurrentTime());
				model.setStatus(Integer.toString(response.getStatus()));
				String time = Calendar.getInstance().getTime().toString();

				KafkaProducerHelper.getInstance(RDDWebConst.KAFKA_HOST).send(RDDWebConst.TRACKTOPIC, time,
						JSON.toJSONString(model));
			}
		} catch (Exception e) {

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
		try {
			String uri = request.getRequestURI();

			// logout的时候已经在prehandle里面记录了日志信息
			if (!StringUtil.isNullOrEmpty(uri) && uri.contains("logout")) {
				return;
			}

			TrackModel model = new TrackModel();
			model.setUri(request.getRequestURI());
			if (request.getSession() != null) {
				model.setSession((String) request.getSession().getAttribute(RDDWebConst.TOKEN));
				model.setUserName((String) request.getSession().getAttribute(RDDWebConst.USERNAME));
			}
			model.addHeader(request);
			model.setParas(request.getParameterMap());
			model.setTime(TimeUtil.getCurrentTime());
			model.setStatus(Integer.toString(response.getStatus()));
			String time = Calendar.getInstance().getTime().toString();

			KafkaProducerHelper.getInstance(RDDWebConst.KAFKA_HOST).send(RDDWebConst.TRACKTOPIC, time,
					JSON.toJSONString(model));
		} catch (Exception e) {

		}
	}
}
