package io.github.dndanoff.cucumber.stepdefinitions;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.dndanoff.BaseIntegrationTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("itest")
@DirtiesContext
public class GivenStepDefinition extends BaseStepDefinition{

}
