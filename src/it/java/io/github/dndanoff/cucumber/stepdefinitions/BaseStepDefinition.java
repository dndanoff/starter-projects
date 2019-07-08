package io.github.dndanoff.cucumber.stepdefinitions;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.dndanoff.cucumber.ScenarioContext;


public class BaseStepDefinition {
	@Autowired
	protected ScenarioContext scenarioContext;
	@Autowired
	protected DataSource dataSource;

}
