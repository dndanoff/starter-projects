package io.github.dndanoff.cucumber.stepdefinitions;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("itest")
@DirtiesContext
public class GivenStepDefinition extends BaseStepDefinition{
	@Before
	public void testSetup() {
		
	}
	
	@After
	public void testTeardown() {
		
	}
}
