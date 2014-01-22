package com.gm.infobus.web;

//import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gm.infobus.entity.Trip;
import com.gm.infobus.entity.TripTrack;

@Controller
public class IndexController extends BaseController{

	@RequestMapping(value = "/index")
	public String index(Map<String, String> map) throws JsonProcessingException {
		Trip trip = new Trip();
		trip.setMapurl("www.google1.com");
		trip.setUserName("spring02");
		trip.setTitle("天下无道1");
		trip.setLatitude(11.1212);
		trip.setLongitude(132.2212);
		trip.setRecordtime(Calendar.getInstance().getTimeInMillis());
		ArrayList<TripTrack> list = new ArrayList<TripTrack>();
		for (int i = 0; i < 5; i++) {
			TripTrack a = new TripTrack();
			a.setLatitude(11.0001+i);
			a.setLongitude(121.0012+i);
			a.setDistance(22.32);
			a.setSpeed(80);
			a.setRecordtime(Calendar.getInstance().getTimeInMillis()+i*200);
			list.add(a);
		}
		trip.setTrackList(list);
		JSONObject tripJson = JSONObject.fromObject(trip);
		System.out.print(tripJson.toString());
		map.put("tripJson", tripJson.toString());
		return "/index";
	}
	
	@RequestMapping(value = "/welcome")
	public String goToLoginPage(){
		return "/login";
	}
	
}