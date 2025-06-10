package org.shoaib.dpdns.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RabbitController {
	
	@Autowired
	AmqpTemplate rabbitTemplate;
	
	@Autowired
	ObjectMapper mapper;
	
	@GetMapping("/data")
	public Map<String,Object>  getMethodName() throws JsonProcessingException {
		
		Map<String,Object> response = new HashMap<>();
		response.put("name", "shoaib");
		response.put("vill", "habibpur");
		response.put("city", "siwan");
		
		
		String jsonData = mapper.writeValueAsString(response);
		
		rabbitTemplate.convertAndSend("demoExchange","demoRoutingKey", jsonData);
		
		return response;
	}
	

}
