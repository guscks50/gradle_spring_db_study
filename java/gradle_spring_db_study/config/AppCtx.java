package gradle_spring_db_study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "gradle_spring_db_study.spring","gradle_spring_db_study.spring.mapper" })
@Import({ContextDataSource.class, ContextSqlSession.class})
public class AppCtx {
  
}

