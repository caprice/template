package com.gm.infobus.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.gm.infobus.entity.User;
import com.gm.infobus.json.JsonResponse;
import com.gm.infobus.util.ConstantUtils;

/**
 * @Description: check the token from client. If the token value doesn't equals
 *               to the value of server side, it tells user that his/her request
 *               is not valid.
 * @author liuwei
 * @date 2013年11月14日 上午9:27:03
 * 
 */
@Component
public class UserSessionFilter extends BaseFilter {

	@Override
	public void processRequest(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("IB_USER");
		JsonResponse jsonRes = new JsonResponse();
		if (user == null) {
			jsonRes.setResult(ConstantUtils.JSON.RESULT_FAILED);
			jsonRes.setMsg("请登录系统!");
			 res.setContentType("application/json");
			 this.writeJsonObject(res, jsonRes);
			return;
		}
	}
}
