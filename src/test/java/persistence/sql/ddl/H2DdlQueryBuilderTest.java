package persistence.sql.ddl;

import domain.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.dialect.H2.H2Dialect;
import persistence.sql.metadata.EntityMetadata;

import static org.junit.jupiter.api.Assertions.*;

class H2DdlQueryBuilderTest {
	private final EntityMetadata entityMetadata = new EntityMetadata(new Person());

	@DisplayName("DB Type에 맞는 전략을 출력한다.")
	@Test
	void When_DbTypeIsH2_Then_GetH2Diaalect() {
		assertEquals(H2DdlQueryBuilder.build().dialect.getClass(), H2Dialect.class);
	}

	@DisplayName("Person 객체로 CREATE 쿼리 생성 테스트")
	@Test
	void test_createQuery() {
		assertEquals(H2DdlQueryBuilder.build().createQuery(entityMetadata),
				"CREATE TABLE users(id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, nick_name VARCHAR(255), old INTEGER, email VARCHAR(255) NOT NULL);");
	}

	@DisplayName("Person 객체로 DROP 쿼리 생성 테스트")
	@Test
	void test_dropQuery() {
		assertEquals(H2DdlQueryBuilder.build().dropQuery(entityMetadata),
				"DROP TABLE users IF EXISTS;");
	}


}