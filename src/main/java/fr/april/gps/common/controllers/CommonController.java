package fr.april.gps.common.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * by osow on 14/11/17.
 * for GPS
 */
@RestController
@RequestMapping("common")
public class CommonController {
	@RequestMapping(value = "ping", method = RequestMethod.GET)
	public ResponseEntity<String> ping() {
		return new ResponseEntity<>("pong", HttpStatus.OK);
	}
}
