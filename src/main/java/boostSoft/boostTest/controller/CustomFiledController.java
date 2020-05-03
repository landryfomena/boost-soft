package boostSoft.boostTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boostSoft.boostTest.service.api.CustomFieldServiceApi;

@RestController
@RequestMapping(path = "customField")
public class CustomFiledController {

	@Autowired CustomFieldServiceApi customFieldServiceApi;
}
