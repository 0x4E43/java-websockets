package com.web.dummy;

import com.web.dummy.websockets.vo.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DummyServiceApplication {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DummyServiceApplication.class, args);
	}

	@GetMapping
	public String test(){
		return "This end point works";
	}


	@PostMapping("/add")
	public String addEvent(@RequestBody Greeting greeting) {
		messagingTemplate.convertAndSend("/topic/greetings/"+greeting.getTenant(), greeting);
		return "Event added";
	}
}
