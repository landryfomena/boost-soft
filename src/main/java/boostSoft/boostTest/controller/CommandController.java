package boostSoft.boostTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import boostSoft.boostTest.data.Command;
import boostSoft.boostTest.service.api.CommandServiceApi;

@RestController
@RequestMapping(path = "command")
public class CommandController {

	@Autowired CommandServiceApi commandServiceApi;
	
	@PostMapping("/register")
	public HttpEntity<? extends Object> createCommand(@RequestBody Command command){
		return commandServiceApi.createCommand(command);
	}
	@PutMapping("/confirm")
	public HttpEntity<? extends Object> confirmCommand(@RequestParam Long commandId){
		return commandServiceApi.confirmCommand(commandId);
	}
	@PutMapping("/cancel")
	public HttpEntity<? extends Object> cancelCommand(@RequestParam Long commandId){
		return commandServiceApi.cancelCommand(commandId);
	}
	@PutMapping("/delever")
	public HttpEntity<? extends Object> deleverCommand(@RequestParam Long commandId){
		return commandServiceApi.deleverCommand(commandId);
	}
}
