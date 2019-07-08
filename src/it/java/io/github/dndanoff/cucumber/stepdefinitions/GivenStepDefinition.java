package io.github.dndanoff.cucumber.stepdefinitions;

import java.sql.SQLException;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
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
	public void testSetup() throws ScriptException, SQLException {
		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("db/deleteTableData.sql"));
		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("db/insertData.sql"));
	}
	
	@After
	public void testTeardown() throws ScriptException, SQLException {
		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("db/deleteTableData.sql"));
	}
	
}
