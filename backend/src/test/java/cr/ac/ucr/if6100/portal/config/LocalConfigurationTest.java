package cr.ac.ucr.if6100.portal.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import static org.assertj.core.api.Assertions.assertThat;

class LocalConfigurationTest {
    private final ApplicationContextRunner runner = new ApplicationContextRunner()
        .withInitializer(new ConfigDataApplicationContextInitializer());

    @Test void buildsJdbcUrlFromSharedVariables() {
        runner.withPropertyValues("DB_NAME=portal_test", "DB_PORT=55432",
            "CORS_ALLOWED_ORIGIN=http://localhost:5174").run(context -> {
            assertThat(context.getEnvironment().getProperty("spring.datasource.url"))
                .isEqualTo("jdbc:postgresql://localhost:55432/portal_test");
            assertThat(context.getEnvironment().getProperty("app.cors.allowed-origin"))
                .isEqualTo("http://localhost:5174");
        });
    }

    @Test void permitsExplicitExternalJdbcUrl() {
        runner.withPropertyValues("DB_URL=jdbc:postgresql://db.example:6432/external",
            "DB_NAME=ignored", "DB_PORT=55432").run(context ->
            assertThat(context.getEnvironment().getProperty("spring.datasource.url"))
                .isEqualTo("jdbc:postgresql://db.example:6432/external"));
    }
}
