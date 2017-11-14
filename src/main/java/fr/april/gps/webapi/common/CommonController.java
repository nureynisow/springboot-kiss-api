package fr.april.gps.webapi.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * by osow on 14/11/17.
 * for GPS
 */
@Controller
@RequestMapping("common")
public class CommonController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<String> ping() {
		return new ResponseEntity<>("pong", HttpStatus.OK);
	}
}
