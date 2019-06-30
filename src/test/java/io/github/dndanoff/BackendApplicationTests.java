package io.github.dndanoff;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.dndanoff.entry_point.kafka.KafkaConsumer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles="test")
public class BackendApplicationTests {
	
	@Autowired
	@MockBean
	private KafkaConsumer kafkaConsumer;
	
	
	@Test
	public void contextLoads() {
	}

}
