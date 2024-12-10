package shopify.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.flywaydb.core.Flyway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

@Configuration

public class FlywayConfig {
    private static final Logger log = LoggerFactory.getLogger(FlywayConfig.class);

    @Value("${flywaypf.active}")
    private String DEFAULT_SCHEMA;
    private static final String DEFAULT_LOCATION = "db/migration";
    private final DataSource dataSource;

    @Autowired
    public FlywayConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    @Profile("!test") // disable Flyway in H2 for test profile
    public Flyway flyway() {
        log.info("Migrating default schema: {} with location: {}", DEFAULT_SCHEMA, DEFAULT_LOCATION);
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .schemas(DEFAULT_SCHEMA)
                .locations(DEFAULT_LOCATION)
                .baselineOnMigrate(true)
                .load();
        flyway.migrate();
        return flyway;
    }
}
