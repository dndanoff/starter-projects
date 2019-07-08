package io.github.dndanoff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.dndanoff.entry_point.kafka.KafkaConsumer;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("itest")
@DirtiesContext
public class BaseIntegrationTest {
	@Autowired
	@MockBean
	protected KafkaConsumer kafkaConsumer;

}
