package io.github.dndanoff.dataprovider;

import org.junit.runner.RunWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.dndanoff.itest.common.BaseIntegrationTest;

@RunWith(SpringRunner.class)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/insertData.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/deleteTableData.sql")
})
public class RepositoryIT extends BaseIntegrationTest {

}