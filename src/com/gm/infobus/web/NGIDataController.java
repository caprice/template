package com.gm.infobus.web;

//import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gm.infobus.json.JsonResponse;
import com.gm.infobus.service.NGIDataService;
import com.gm.infobus.util.ConstantUtils;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Controller
@RequestMapping(value = "/data")
public class NGIDataController extends BaseController{

	@Autowired
	private NGIDataService service;
	
	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 * @throws IOException 
	 */
	@RequestMapping(value = "upload")
	@ResponseBody
	public JsonResponse uploadData() throws IOException {
		String requestStr = IOUtils.toString(this.request.getInputStream());
		DBObject dbObject = (DBObject)JSON.parse(requestStr);
		JsonResponse response = new JsonResponse();
		service.uploadNGIData(dbObject, "ngidata");
		response.setResult(ConstantUtils.JSON.RESULT_OK);
		return response;
	}
	
	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 * @throws IOException 
	 */
	@RequestMapping(value = "showData")
	public String getNGIData(Map<String, List<DBObject>> map) throws IOException {
		List<DBObject> dbObjs = service.getDBObjects("ngidata");
		map.put("ngidata", dbObjs);
		return "index";
	}
	
	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 * @throws IOException 
	 */
	@RequestMapping(value = "viewDetail")
	@ResponseBody
	public JsonResponse viewDetail(String id) throws IOException {
		JsonResponse response = new JsonResponse();
		DBObject obj =	service.getNGIRecordById(id, "ngidata");
		obj.removeField("_id");
		obj.removeField("_class");
		response.setResult(ConstantUtils.JSON.RESULT_OK);
		response.setData(obj.toString());
		return response;
	}
	
}