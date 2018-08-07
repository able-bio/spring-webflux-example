package com.able;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.*;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient(timeout = "36000")
@SpringBootTest(classes = ReactiveServer.class)
public class DemoApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void get() {

		webTestClient.get().uri("/events")
				.accept(MediaType.TEXT_EVENT_STREAM)
				.exchange()
				.expectStatus()
				.isOk()
				.expectHeader().contentTypeCompatibleWith(MediaType.TEXT_EVENT_STREAM)
				.returnResult(Event.class);
	}



}
