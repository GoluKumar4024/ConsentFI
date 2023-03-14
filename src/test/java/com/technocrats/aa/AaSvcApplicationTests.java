package com.technocrats.aa;

import com.technocrats.aa.repo.ConsentReqTemplatesRepo;
import com.technocrats.aa.services.rules.impl.PrepareConsentReq;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;


class AaSvcApplicationTests {

	@Autowired
	private ConsentReqTemplatesRepo repo;

	@Test
	void contextLoads() {
		System.out.println(repo.findByPurposeCode("105"));
	}

}
