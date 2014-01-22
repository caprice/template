package com.gm.infobus.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gm.infobus.entity.Point;
import com.gm.infobus.entity.Trip;
import com.gm.infobus.json.JsonResponse;
import com.gm.infobus.service.RacingDataService;
import com.gm.infobus.util.ConstantUtils;

/**
 * @Description:
 * @author liuwei
 * @date 2013年11月12日 下午3:28:39
 * 
 */
@Controller
@RequestMapping(value = "/trip")
public class TripController extends BaseController {
	@Autowired
	private RacingDataService rdService;

	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 */
	@RequestMapping(value = "uploadTripData")
	@ResponseBody
	public JsonResponse addNewUser(@RequestBody Trip trip) {
		JsonResponse response = new JsonResponse();
		rdService.addTrip(trip);
		response.setMsg("upload trip data successfully.");
		response.setResult(ConstantUtils.JSON.RESULT_OK);
		return response;
	}
	
	
	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 */
	@RequestMapping(value = "getNeighbourTrips")
	@ResponseBody
	public JsonResponse login(Point p) {
		JsonResponse response = new JsonResponse();
		List<Trip> trips = rdService.getNeighborTrips(p);
		if (trips != null) {
			response.setResult(ConstantUtils.JSON.RESULT_OK);
			response.setData(trips);
			response.setMsg("Successfully.");
		} else {
			response.setResult(ConstantUtils.JSON.RESULT_FAILED);
			response.setMsg("retrieve neighbour trips failed.");
		}
		return response;
	}

}
