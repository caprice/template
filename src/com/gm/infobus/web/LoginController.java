package com.gm.infobus.web;

//import javax.inject.Inject;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController extends BaseController {

	/**
	* @Title: index
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return:String
	* @author: liuwei
	* @date: 2014年10月29日
	* Modification History:
	* Date  Author  Version  Description
	* ---------------------------------------------------------*
	* 2014年10月29日  liuwei v1.0.0   修改原因
	*/
	@RequestMapping(value = "/login")
	public String index() {
		return "login";
	}

	/**
	* @Title: index
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return:String
	* @author: liuwei
	* @date: 2014年10月29日
	* Modification History:
	* Date  Author  Version  Description
	* ---------------------------------------------------------*
	* 2014年10月29日  liuwei v1.0.0   修改原因
	*/
	@RequestMapping(value = "/loginFailed")
	public String logFailed() throws IOException {
		return "loginfailed";
	}

}