package com.gm.infobus.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	protected HttpServletResponse response;

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
//
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ResponseBody
//	public JsonResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
//		JsonResponse response = new JsonResponse();
//		response.setResult(ConstantUtils.JSON.RESULT_VALIDATION_FAILED);
//		response.setData(error.getBindingResult().getAllErrors());
//		response.setMsg("Validation failed!");
//		return response;
//	}
//
//	@ExceptionHandler(DataAccessException.class)
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	@ResponseBody
//	public JsonResponse handleException(DataAccessException ex) {
//		JsonResponse response = new JsonResponse();
//		response.setResult(ConstantUtils.JSON.RESULT_DB_ERROR);
//		response.setData(ex.getMessage());
//		response.setMsg("DB access Error!");
//		return response;
//	}
//	
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	@ResponseBody
//	public JsonResponse handleMaxUploadSizeExceededException(Exception ex) {
//		JsonResponse response = new JsonResponse();
//		response.setResult(ConstantUtils.JSON.RESULT_FAILED);
//		response.setData(ex.getMessage());
//		if(ex instanceof MaxUploadSizeExceededException){
//			response.setMsg("Maximum upload size of "+((MaxUploadSizeExceededException)ex).getMaxUploadSize()+ "bytes exceeded");
//		}else{
//			response.setMsg(ex.getMessage());
//		}
//
//		return response;
//	}
	
	@ExceptionHandler(Exception.class)
	public String exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {  
	     
	    //这里进行通用处理，如日志记录等...
	     //如果是json格式的ajax请求
	     if (request.getHeader("accept").indexOf("application/json") > -1
	             || (request.getHeader("X-Requested-With")!= null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)) { 
	        response.setStatus(500);
	        response.setContentType("application/json;charset=utf-8");   
    		JsonResponse jrs = new JsonResponse();
    		jrs.setResult(ConstantUtils.JSON.RESULT_FAILED);
    		jrs.setData(ex);
    		jrs.setMsg("error happend!");
    		ObjectMapper mapper = new ObjectMapper();
    		try {
    			mapper.writeValue(response.getOutputStream(), jrs);
    		} catch (JsonGenerationException e) {
    			e.printStackTrace();
    		} catch (JsonMappingException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
	        return null;
	     }
	     else{//如果是普通请求
	        request.setAttribute("exceptionMessage", ex);  
//	        if(e instanceof SQLException) 
//	            return "testerror";   
//	        else
//	            return "error";  
	        return "errors/error";
	    }
	}  
}
