package com.surrius.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class StatsController {

	@GetMapping("stats/month")
	public String getAforo() {
		return "{\r\n" + 
				"  \"dateOfMaxPersons\": 20200705,\r\n" + 
				"  \"dateOfMinPersons\": 20200704,\r\n" + 
				"  \"july\": [\r\n" + 
				"    {\r\n" + 
				"      \"date\": 20200701,\r\n" + 
				"      \"smallPool\": 4,\r\n" + 
				"      \"mediumPool\": 12,\r\n" + 
				"      \"bigPool\": 19,\r\n" + 
				"      \"general\": 265\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"date\": 20200702,\r\n" + 
				"      \"smallPool\": 6,\r\n" + 
				"      \"mediumPool\": 15,\r\n" + 
				"      \"bigPool\": 25,\r\n" + 
				"      \"general\": 205\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"date\": 20200703,\r\n" + 
				"      \"smallPool\": 2,\r\n" + 
				"      \"mediumPool\": 10,\r\n" + 
				"      \"bigPool\": 17,\r\n" + 
				"      \"general\": 165\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"date\": 20200704,\r\n" + 
				"      \"smallPool\": 3,\r\n" + 
				"      \"mediumPool\": 10,\r\n" + 
				"      \"bigPool\": 18,\r\n" + 
				"      \"general\": 150\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"date\": 20200705,\r\n" + 
				"      \"smallPool\": 7,\r\n" + 
				"      \"mediumPool\": 20,\r\n" + 
				"      \"bigPool\": 25,\r\n" + 
				"      \"general\": 300\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";
	}

}
