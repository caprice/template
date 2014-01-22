package com.gm.infobus.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.gm.infobus.json.JsonResponse;
import com.gm.infobus.util.ConstantUtils;

/**
 * @Description:
 * @author liuwei
 * @date 2013年11月12日 下午3:28:34
 * 
 */
public abstract class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected ServletContext servletContext;

	@Autowired
	protected HttpSession session;

	@Autowired
	protected HttpServletRequest request;

	/**
	 * 转JSON格式.
	 * 
	 * @param resultData
	 *            the result data
	 * @return the string
	 */
	protected String toJSON(Object resultData) {
		return toJSON(resultData, null);
	}

	/**
	 * 转JSON格式带参数.
	 * 
	 * @param resultData
	 *            the result data
	 * @param config
	 *            the config
	 * @return the string
	 */
	protected String toJSON(Object resultData, JsonConfig config) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(ConstantUtils.JSON.KEY_RESULT, ConstantUtils.JSON.RESULT_OK);
		jsonObj.put(ConstantUtils.JSON.KEY_DATA, resultData);
		String resData = null;
		if (config != null) {
			resData = JSONObject.fromObject(jsonObj, config).toString();
		} else {
			resData = JSONObject.fromObject(jsonObj).toString();
		}
		logger.debug(resData);
		return resData;
	}

	/**
	 * 转JSON格式（返回错误）.
	 * 
	 * @param errorMessage
	 *            the error message
	 * @return the string
	 */
	protected String toJSONError(String errorMessage) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(ConstantUtils.JSON.KEY_RESULT, ConstantUtils.JSON.RESULT_FAILED);
		jsonObj.put(ConstantUtils.JSON.KEY_MESSAGE, errorMessage);
		String resData = JSONObject.fromObject(jsonObj).toString();
		logger.debug(resData);
		return resData;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public JsonResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
		JsonResponse response = new JsonResponse();
		response.setResult(ConstantUtils.JSON.RESULT_VALIDATION_FAILED);
		response.setData(error.getBindingResult().getAllErrors());
		response.setMsg("Validation failed!");
		return response;
	}

	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public JsonResponse handleException(DataAccessException ex) {
		JsonResponse response = new JsonResponse();
		response.setResult(ConstantUtils.JSON.RESULT_DB_ERROR);
		response.setData(ex.getMessage());
		response.setMsg("DB access Error!");
		return response;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public JsonResponse handleMaxUploadSizeExceededException(Exception ex) {
		JsonResponse response = new JsonResponse();
		response.setResult(ConstantUtils.JSON.RESULT_FAILED);
		response.setData(ex.getMessage());
		if(ex instanceof MaxUploadSizeExceededException){
			response.setMsg("Maximum upload size of "+((MaxUploadSizeExceededException)ex).getMaxUploadSize()+ "bytes exceeded");
		}else{
			response.setMsg(ex.getMessage());
		}

		return response;
	}
}
