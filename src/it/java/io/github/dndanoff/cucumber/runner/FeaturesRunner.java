package io.github.dndanoff.cucumber.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.kafka.test.rule.KafkaEmbedded;

import com.datamountaineer.kafka.schemaregistry.RestApp;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.github.dndanoff.utils.KafkaCommonUtils;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features/"},
				plugin = { "pretty", "html:target/cucumber"},
				glue = { "io.github.dndanoff.stepdefinitions"})
public class FeaturesRunner {

	@ClassRule
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, 1);

	private static RestApp schemaRegistry;
	
	@BeforeClass
	public static void setup() {
		System.setProperty("config.kafka.bootstrapserver", embeddedKafka.getBrokersAsString());
		try {
			schemaRegistry = KafkaCommonUtils.createSchemaRegistry(embeddedKafka);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void tearDown() {
		KafkaCommonUtils.stopSchemaRegistry(schemaRegistry);
	}

}
