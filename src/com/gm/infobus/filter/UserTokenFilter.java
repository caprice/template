package com.gm.infobus.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gm.infobus.json.JsonResponse;
import com.gm.infobus.service.UserService;
import com.gm.infobus.util.ConstantUtils;
import com.gm.infobus.util.MD5;

/**
 * @Description: check the token from client. If the token value doesn't equals
 *               to the value of server side, it tells user that his/her request
 *               is not valid.
 * @author liuwei
 * @date 2013年11月14日 上午9:27:03
 * 
 */
@Component
public class UserTokenFilter extends BaseFilter {
	@Autowired
	private UserService userService;

	@Override
	public void processRequest(HttpServletRequest req, HttpServletResponse res) {
		String token = req.getParameter("token");
		String accountName = req.getParameter("accountName");
		boolean isAccountNameExisted = userService.isUserNameExisted(accountName);
		String serverToken = "";
		if (isAccountNameExisted) {
			serverToken = MD5.encode(ConstantUtils.TOKEN_KEY + accountName);
			this.logger.debug("sever token is : " + serverToken);
		}
		if (!serverToken.equals(token)) {
			JsonResponse jsonRes = new JsonResponse();
			jsonRes.setResult(ConstantUtils.JSON.RESULT_FAILED);
			jsonRes.setMsg("无效请求.");
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html");
			this.writeJsonObject(res, jsonRes);
			return;
		}
	}
}
