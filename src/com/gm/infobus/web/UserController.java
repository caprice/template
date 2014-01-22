package com.gm.infobus.web;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gm.infobus.entity.User;
import com.gm.infobus.entity.UserDetail;
import com.gm.infobus.entity.validator.UserValidator;
import com.gm.infobus.json.JsonResponse;
import com.gm.infobus.service.UserService;
import com.gm.infobus.util.ConfigManager;
import com.gm.infobus.util.ConstantUtils;
import com.gm.infobus.util.FileManager;
import com.gm.infobus.util.MD5;

/**
 * @Description:
 * @author liuwei
 * @date 2013年11月12日 下午3:28:39
 * 
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;

	@InitBinder
	public void initBinder(DataBinder binder) {
		binder.setValidator(userValidator);
	}

	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 */
	@RequestMapping(value = "register")
	@ResponseBody
	public JsonResponse addNewUser(@Valid User user, Errors validErrors) {
		JsonResponse response = new JsonResponse();
		String passwordType = ConfigManager.getInstance().getConfigItem("password.type", "");
		if (passwordType.equalsIgnoreCase("MD5")) {
			user.setPassword(MD5.encode(user.getPassword()));
		}
		if (!validErrors.hasErrors()) {
			userService.addUser(user);
			response.setResult(ConstantUtils.JSON.RESULT_OK);
			response.setData(user);
			response.setMsg("注册成功!");
		} else {
			response.setResult(ConstantUtils.JSON.RESULT_VALIDATION_FAILED);
			response.setData(validErrors.getAllErrors());
			response.setMsg("Validation failed!");
		}
		return response;
	}

	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 */
	@RequestMapping(value = "verifyAccount")
	@ResponseBody
	public JsonResponse verifyUser(String userName) {
		JsonResponse response = new JsonResponse();
		boolean isAccountNameExisted = userService.isUserNameExisted(userName);
		if (!isAccountNameExisted) {
			response.setResult(ConstantUtils.JSON.RESULT_OK);
			response.setMsg("用户名不存在,可以注册!");
		} else {
			response.setResult(ConstantUtils.JSON.RESULT_FAILED);
			response.setMsg("用户名已经存在!");
		}

		return response;
	}

	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 */
	@RequestMapping(value = "verifyPlate")
	@ResponseBody
	public JsonResponse verifyPlate(String plate) {
		JsonResponse response = new JsonResponse();
		boolean isPlateExisted = userService.isPlateExisted(plate);
		if (!isPlateExisted) {
			response.setResult(ConstantUtils.JSON.RESULT_OK);
		} else {
			response.setResult(ConstantUtils.JSON.RESULT_FAILED);
			response.setMsg("车牌号已经存在!");
		}

		return response;
	}

	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	public JsonResponse login(User userParam) {
		JsonResponse response = new JsonResponse();
		String passwordType = ConfigManager.getInstance().getConfigItem("password.type", "");
		if (passwordType.equalsIgnoreCase("MD5")) {
			userParam.setPassword(MD5.encode(userParam.getPassword()));
		}
		User user = userService.getLoginUser(userParam);
		if (user != null) {
			response.setResult(ConstantUtils.JSON.RESULT_OK);
			Map<String, UserDetail> usersMap = new HashMap<String, UserDetail>();
			UserDetail userDetail = user.getUserDetail();
			userDetail.setEmail(user.getEmail());
			usersMap.put(user.getUserName(), userDetail);
			response.setData(usersMap);
			response.setMsg("登录成功.");
			session.setAttribute("IB_USER", user);
		} else {
			response.setResult(ConstantUtils.JSON.RESULT_FAILED);
			response.setMsg("用户名或者密码错误.");
		}
		return response;
	}

	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 */
	@RequestMapping(value = "getUserByPlate")
	@ResponseBody
	public JsonResponse getUserByPlate(String plate) {
		JsonResponse response = new JsonResponse();
		// userParam.setPassword(MD5.encode(userParam.getPassword(),
		// ConstantUtils.SALT_KEY));
		User user = userService.getUserByPlate(plate);
		if (user != null) {
			response.setResult(ConstantUtils.JSON.RESULT_OK);
			response.setData(user);
			response.setMsg("找到注册的用户信息.");
		} else {
			response.setResult(ConstantUtils.JSON.RESULT_FAILED);
			response.setMsg("车牌没有注册，找不到对应的用户.");
		}

		return response;
	}

	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 */
	@RequestMapping(value = "userDetailModify")
	@ResponseBody
	public JsonResponse updateUserDetail(User user) {
		JsonResponse response = new JsonResponse();
		userService.updateUserDetail(user.getUserDetail());
		response.setData(user.getUserDetail());
		response.setMsg("修改用户签名成功.");

		return response;
	}

	/**
	 * 
	 * @param condition
	 * @return JsonResponse
	 */
	@RequestMapping(value = "getUsersByUserNames")
	@ResponseBody
	public JsonResponse getUsersByUserNames(String[] userName) {
		JsonResponse response = new JsonResponse();
		Map<String, UserDetail> usersMap = userService.getUsersByUserNames(userName);
		if (usersMap != null) {
			response.setResult(ConstantUtils.JSON.RESULT_OK);
			response.setData(usersMap);
			response.setMsg("Success.");
		} else {
			response.setResult(ConstantUtils.JSON.RESULT_FAILED);
			response.setMsg("用户名或者密码错误.");
		}

		return response;
	}

	/**
	 * @Title: doFileUpload
	 * @Description: 上传用户图像
	 * @return:String
	 * @author: liuwei
	 * @date: 2013年11月29日
	 */
	@ResponseBody
	@RequestMapping(value = "/picUpload")
	public JsonResponse doFileUpload(@RequestParam String userName, @RequestParam MultipartFile imgFile) throws IllegalStateException, IOException {
		JsonResponse response = new JsonResponse();
		if (!imgFile.isEmpty()) {
			String path = ConfigManager.getInstance().getConfigItem("upload.path", "");
			String originalFilename = imgFile.getOriginalFilename();
			int extIndex = originalFilename.lastIndexOf(".");
			String extName = "";
			if(extIndex >=0){
				extName = originalFilename.substring(extIndex);
			}
			if (extName.matches(ConfigManager.getInstance().getConfigItem("file.allow.extName", "(.jpeg|.jpg|.gif|.bmp|.png)(?i)"))) {
				Map<String, UserDetail> userMap = userService.getUsersByUserNames(new String[] { userName });
				UserDetail existedUserDetail = userMap.get(userName);
				if (existedUserDetail != null) {
					String lastUserPicName = existedUserDetail.getPhoto();
					if (StringUtils.isNotEmpty(lastUserPicName)) {
						int lastSlashIndex = lastUserPicName.lastIndexOf("/");
						String lastFileName = lastUserPicName.substring(lastSlashIndex + 1);
						FileManager fileManager = new FileManager();
						fileManager.deleteTarget(path + lastFileName);
					}
				}
				String fileName = getFileName(path, userName, extName);
				File newFile = new File(fileName);
				imgFile.transferTo(newFile);
				UserDetail detail = new UserDetail();
				detail.setUserName(userName);
				detail.setPhoto(ConfigManager.getInstance().getConfigItem("content.server.userProfile.url", "") + newFile.getName());
				userService.updateUserDetail(detail);
				response.setResult(ConstantUtils.JSON.RESULT_OK);
				response.setData(detail);
				response.setMsg("upload user photo successfully.");
			} else {
				response.setResult(ConstantUtils.JSON.RESULT_FAILED);
				response.setMsg("上传文件后缀名不正确, 应为.jpeg, jpg, gif, bmp, png中的一种.");
			}
		} else {
			response.setResult(ConstantUtils.JSON.RESULT_FAILED);
			response.setMsg("upload user photo failed.");
		}
		return response;
	}

	private String getFileName(String path, String userName, String extName) {
		String timeStamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
		return path + userName + "_" + timeStamp + extName;
	}

	/**
	 * @Title: doChatVoiceUpload
	 * @Description: 语音消息上传
	 * @return:String
	 * @author: liuwei
	 * @date: 2013年11月29日
	 */
	@ResponseBody
	@RequestMapping(value = "/chatVoiceUpload")
	public JsonResponse doChatVoiceUpload(@RequestParam String userName, @RequestParam MultipartFile voiceFile) throws IllegalStateException, IOException {
		JsonResponse response = new JsonResponse();
		if (!voiceFile.isEmpty()) {
			String path = ConfigManager.getInstance().getConfigItem("upload.voiceMsg.path", "");
			String originalFilename = voiceFile.getOriginalFilename();
			int extIndex = originalFilename.lastIndexOf(".");
			String extName = "";
			if(extIndex >=0){
				extName = originalFilename.substring(extIndex);
			}
			String fileName = getFileName(path, userName, extName);
			File newFile = new File(fileName);
			voiceFile.transferTo(newFile);
			UserDetail detail = new UserDetail();
			detail.setUserName(userName);
			detail.setCurVoiceUrl(ConfigManager.getInstance().getConfigItem("content.server.voiceMsg.url", "") + newFile.getName());
			response.setResult(ConstantUtils.JSON.RESULT_OK);
			response.setData(detail);
			response.setMsg("upload voice message successfully.");
		} else {
			response.setResult(ConstantUtils.JSON.RESULT_FAILED);
			response.setMsg("upload voice message failed.");
		}
		return response;
	}

}
