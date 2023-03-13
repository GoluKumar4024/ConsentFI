package com.technocrats.aa;

import com.technocrats.aa.services.AAClientSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(exclude = {WebFluxAutoConfiguration.class})
@RequiredArgsConstructor
public class AaSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AaSvcApplication.class, args);
	}

	private final AAClientSvc aaClientSvc;

	@EventListener(ApplicationStartedEvent.class)
	public void dosomethng(){
		System.out.println(aaClientSvc.checkHeartBeat());
	}
}
