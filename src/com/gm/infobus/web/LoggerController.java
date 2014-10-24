package com.gm.infobus.web;

//import javax.inject.Inject;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gm.infobus.entity.SearchCritera;
import com.gm.infobus.json.JsonResponse;
import com.gm.infobus.service.NGIDataService;
import com.gm.infobus.util.ConstantUtils;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Controller
@RequestMapping(value = "/log")
public class LoggerController extends BaseController {

	@Autowired
	private NGIDataService service;

	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 * @throws IOException
	 */
	@RequestMapping(value = "uploadLogs")
	@ResponseBody
	public JsonResponse uploadLogs() throws IOException {
		long nowTimeMillis = Calendar.getInstance().getTimeInMillis();
		String requestStr = IOUtils.toString(this.request.getInputStream(), "UTF-8");
		DBObject dbObject = (DBObject) JSON.parse(requestStr);
		dbObject.put("serverTime", nowTimeMillis);
		JsonResponse response = new JsonResponse();
		DBObject db_res = service.addIntoCollection("appLogs", dbObject);
		response.setResult(ConstantUtils.JSON.RESULT_OK);
		response.setMsg("upload successfullly.");
		response.setData(db_res);
		return response;
	}
	
	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 * @throws IOException
	 */
	@RequestMapping(value = "clearLogs")
	@ResponseBody
	public JsonResponse clearLogs(SearchCritera critera) throws IOException {
		service.clearLogsByDevice("appLogs", critera);
		JsonResponse response = new JsonResponse();
		response.setResult(ConstantUtils.JSON.RESULT_OK);
		response.setMsg("clear "+ critera.getDevice()+" successfullly.");
		response.setData("");
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
	@ResponseBody
	public JsonResponse getNGIData(SearchCritera critera) throws IOException {
		List<DBObject> dbObjs = service.getLogDBObjects("appLogs", critera);
		for(DBObject dbObj : dbObjs){
			if (dbObj.containsField("exception")) {
				String newVal = ((String)dbObj.get("exception")).replaceAll("\n", "<br>");
				dbObj.put("exception", newVal);
			}
		}
		JsonResponse response = new JsonResponse();
		response.setData(dbObjs);
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
	@RequestMapping(value = "init")
	public String init(Map<String, Object> map) throws IOException {
		List<DBObject> dbObjs = service.getValueByParam("appLogs", new String[] { "device" });
		Set<DBObject> dbObjRes = new HashSet<DBObject>();
		if (dbObjs != null) {
			for (DBObject dbo : dbObjs) {
				dbObjRes.add(dbo);
			}
		}
		List<DBObject> configObjs = service.getDBObjects("logdatatmp");
		if (configObjs != null && !configObjs.isEmpty()) {
			DBObject configObj = configObjs.get(0);
			configObj.removeField("_id");
			Set<String> params = configObj.keySet();
			map.put("params", params);
		}
		map.put("devices", dbObjRes);
		return "logs";
	}

	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 * @throws IOException
	 */
	@RequestMapping(value = "openLogDataTemplateSetting")
	public String openNgiDataTemplateSetting(Map<String, Set<DBObject>> map) throws IOException {
		return "logdatatmp";
	}

	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 * @throws IOException
	 */
	@RequestMapping(value = "saveLogDataTmp")
	@ResponseBody
	public JsonResponse saveLogDataTmp() throws IOException {
		String requestStr = IOUtils.toString(this.request.getInputStream(), "UTF-8");
		DBObject dbObject = (DBObject) JSON.parse(requestStr);
		JsonResponse response = new JsonResponse();
		service.dropCollection("logdatatmp");
		DBObject db_res = service.addIntoCollection("logdatatmp", dbObject);
		response.setResult(ConstantUtils.JSON.RESULT_OK);
		response.setMsg("save successfullly.");
		response.setData(db_res);
		return response;
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
	public JsonResponse viewDetail(int time, boolean isNew, int machine, long timeSecond, int inc) throws IOException {
		JsonResponse response = new JsonResponse();
		ObjectId objId = new ObjectId(time, machine, inc);
		DBObject obj = service.getNGIRecordById(objId.toString(), "appLogs");
		obj.removeField("_id");
		obj.removeField("_class");
		response.setResult(ConstantUtils.JSON.RESULT_OK);
		response.setData(obj.toString());
		return response;
	}

	/**
	 * @Title: exportExcel
	 * @Description: 导出用户数据生成的excel文件
	 * @param model
	 * @param request
	 * @param response
	 * @param 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public String exportExcel(ModelMap model, SearchCritera critera) {
		List<DBObject> dbObjs = service.getLogDBObjects("appLogs", critera);
		model.put("dataList", dbObjs);
		String params = critera.getParams();
		model.put("params", params);
		return "exportExcel";
	}

}