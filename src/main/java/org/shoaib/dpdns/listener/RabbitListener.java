package org.shoaib.dpdns.listener;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitListener {
	
	@Autowired
	ObjectMapper mapper;
	
	
	@SuppressWarnings("unchecked")
	@org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "${rabbitmq.queue}")
	public void fetch(String data) throws JsonMappingException, JsonProcessingException {		
		
		Map<String,String> m =  mapper.readValue(data, Map.class);
		System.out.println(m);
		
	}

}
