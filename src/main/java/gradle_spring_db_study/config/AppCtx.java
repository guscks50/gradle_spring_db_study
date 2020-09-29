package gradle_spring_db_study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "gradle_spring_db_study.spring" })
@Import({ContextDataSource.class, ContextSqlSession.class})
public class AppCtx {
  
}
