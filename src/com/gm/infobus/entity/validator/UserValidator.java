package com.gm.infobus.entity.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.gm.infobus.entity.User;
import com.gm.infobus.entity.UserDetail;
import com.gm.infobus.service.UserService;

/**
 * @Description:
 * @author liuwei
 * @date 2013年11月13日 下午1:16:04
 * 
 */
@Component
public class UserValidator extends BaseValidator {
	@Autowired
	private UserService userService;
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	/*
	 * (非 Javadoc) <p>Title: validate</p> <p>Description: </p>
	 * 
	 * @param obj
	 * 
	 * @param errors
	 * 
	 * @see
	 * com.gm.infobus.entity.validator.BaseValidator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "userName", "用户名不能为空.");
		User user = (User) obj;
		if(userService.isUserNameExisted(user.getUserName())){
			errors.rejectValue("userName", "用户名已经存在.");
		}
		if (user.getPassword() == null || user.getPassword().length() < 3
				|| user.getPassword().length() > 128) {
			errors.rejectValue("password", "密码长度不能小于3位并且不能大于128位.");
		}else if (!user.getPassword().equals(user.getrPassword())) {
			errors.rejectValue("rPassword", "两次填写密码不一致.");
		}
//		UserDetail detail = user.getUserDetail();
//		if(detail != null){
//			if(userService.isPlateExisted(detail.getPlate())){
//				errors.rejectValue("userDetail.plate", "注册车牌已经存在,请用另一车牌注册！.");
//			}
//		}
//		if (!this.isValidEmail(user.getEmail())) {
//			errors.rejectValue("email", "不是有效的email.");
//		}
//		if (!this.isValidMobileNumber(user.getMobile())) {
//			errors.rejectValue("mobile", "不是一个正确的手机号码.");
//		}
//		ValidationUtils.rejectIfEmpty(errors, "plate", "车牌号码不能为空.");
	}

}
