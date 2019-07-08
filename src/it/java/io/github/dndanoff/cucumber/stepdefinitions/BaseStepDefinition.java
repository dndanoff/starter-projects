package io.github.dndanoff.cucumber.stepdefinitions;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.dndanoff.cucumber.ScenarioContext;


public class BaseStepDefinition {
	@Autowired
	protected ScenarioContext scenarioContext;
	@Autowired
	protected DataSource dataSource;
}
