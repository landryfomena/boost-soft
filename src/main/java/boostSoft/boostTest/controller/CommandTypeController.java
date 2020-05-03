package boostSoft.boostTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boostSoft.boostTest.service.api.CommandTypeServiceApi;

@RestController
@RequestMapping(path = "commandType")
public class CommandTypeController {

	@Autowired CommandTypeServiceApi commandTypeServiceApi;
}
