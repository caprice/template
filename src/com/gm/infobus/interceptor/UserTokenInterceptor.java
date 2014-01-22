package com.gm.infobus.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gm.infobus.json.JsonResponse;
import com.gm.infobus.service.UserService;
import com.gm.infobus.util.ConstantUtils;
import com.gm.infobus.util.MD5;

public class UserTokenInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String token = request.getParameter("token");
		String accountName = request.getParameter("accountName");
		boolean isAccountNameExisted = userService
				.isUserNameExisted(accountName);
		String serverToken = "";
		if (isAccountNameExisted) {
			serverToken = MD5.encode(ConstantUtils.TOKEN_KEY + accountName);
		}
		if (!serverToken.equals(token)) {
			JsonResponse jsonRes = new JsonResponse();
			jsonRes.setResult(ConstantUtils.JSON.RESULT_FAILED);
			jsonRes.setMsg("无效操作");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println(JSONObject.fromObject(jsonRes).toString());
			pw.flush();
			return false;
		}
		return true;
	}
	
	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		System.out.println("---------test----------");
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("---------test- after---------");
	}

}
