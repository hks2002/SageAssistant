package sageassistant.controller;

import java.util.Map;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alibaba.fastjson.JSONObject;

import sageassistant.service.StatusService;
import sageassistant.utils.Utils;

@CrossOrigin
@RestController
public class StatusController {
	// private static final Logger log = LogManager.getLogger();

	@Autowired
	private StatusService statusService;

	@GetMapping("/Data/TobeDelivery")
	public String getTobeDelivery(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
		return Utils.listToString(statusService.findTobeDeliveryBySite(Site, 0));
	}

	@GetMapping("/Data/TobeReceive")
	public String getTobeReceive(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
		return Utils.listToString(statusService.findTobeReceiveBySite(Site, 0));
	}

	@GetMapping("/Data/TobeDealWithOrderLine")
	public String getTobeDealWithOrderLineBySite(
			@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
		return Utils.listToString(statusService.findTobeDealWithOrderLineBySite(Site, 0));
	}

	@GetMapping("/Data/TobePurchaseBom")
	public String getTobePurchaseBom(
			@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
		return Utils.listToString(statusService.findTobePurchaseBomBySite(Site, 0));
	}

	@GetMapping("/Data/TobeClosedWO")
	public String getTobeClosedWO(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
		return Utils.listToString(statusService.findTobeClosedWOBySite(Site, 0));
	}

	@GetMapping("/Data/TobeTracking")
	public String getTobeTracking(
			@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
		return Utils.listToString(statusService.findTobeTracking(Site));
	}

	@GetMapping("/Data/TrackingNotes")
	public String getTrackingNotes(
			@RequestParam(value = "TrackCode", required = false, defaultValue = "NULL") String TrackCode) {

		if (TrackCode.equals("NULL")) {
			return Utils.listToString(statusService.findTrackingNotes(null));
		} else {
			return Utils.listToString(statusService.findTrackingNotes(TrackCode));
		}
	}

	@DeleteMapping("/Data/TrackingNotes")
	public String deleteTrackingNotes(@RequestBody Map<String, JSONObject> requestObj) {
		JSONObject params = requestObj.get("params");
		Boolean rst = statusService.deleteTrackingNotes(params.get("TrackCode").toString());
		if (rst) {
			return "Success";
		} else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/Data/TrackingNotes")
	public String insertTrackingNotes(@RequestBody Map<String, JSONObject> requestObj) {
		JSONObject data = requestObj.get("data");
		Boolean rst = statusService.insertTrackingNotes(
				data.get("TrackCode").toString(),
				data.get("Note").toString(),
				data.get("NoteBy").toString());
		if (rst) {
			return "Success";
		} else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/Data/TrackingNotes")
	public String updateTrackingNotes(@RequestBody Map<String, JSONObject> requestObj) {
		JSONObject params = requestObj.get("params");
		JSONObject data = requestObj.get("data");

		Boolean rst = statusService.updateTrackingNotes(
				params.get("TrackCode").toString(),
				data.get("Note").toString(),
				data.get("NoteBy").toString());
		if (rst) {
			return "Success";
		} else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
	}
}
