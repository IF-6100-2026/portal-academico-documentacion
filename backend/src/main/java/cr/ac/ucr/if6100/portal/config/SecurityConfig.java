package cr.ac.ucr.if6100.portal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import cr.ac.ucr.if6100.portal.persistence.repository.PortalUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.UUID;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JdbcTemplate jdbcTemplate,
        ObjectMapper objectMapper) throws Exception {
        return http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/v1/health", "/actuator/health").permitAll()
                .requestMatchers("/api/v1/guardian/**").hasRole("GUARDIAN")
                .requestMatchers("/api/v1/director/**").hasRole("DIRECTOR")
                .anyRequest().authenticated())
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint((request, response, exception) -> {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    objectMapper.writeValue(response.getOutputStream(), Map.of(
                        "code", "AUTHENTICATION_REQUIRED",
                        "message", "Se requiere autenticación válida.",
                        "correlationId", UUID.randomUUID()));
                })
                .accessDeniedHandler((request, response, exception) -> {
                    String username = request.getUserPrincipal() == null ? "anonymous"
                        : request.getUserPrincipal().getName();
                    jdbcTemplate.update("INSERT INTO security_access_log "
                            + "(id, username, requested_path, occurred_at) "
                            + "VALUES (?, ?, ?, CURRENT_TIMESTAMP)",
                        UUID.randomUUID(), username, request.getRequestURI());
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    objectMapper.writeValue(response.getOutputStream(), Map.of(
                        "code", "ACCESS_DENIED",
                        "message", "No tiene permiso para realizar esta operación.",
                        "correlationId", UUID.randomUUID()));
                }))
            .httpBasic(Customizer.withDefaults())
            .build();
    }

    @Bean
    public UserDetailsService userDetailsService(
        PortalUserRepository userRepository,
        PasswordEncoder passwordEncoder,
        @Value("${app.demo-users.guardian.password}") String guardianPassword,
        @Value("${app.demo-users.empty-guardian.password}") String emptyGuardianPassword,
        @Value("${app.demo-users.director.password}") String directorPassword,
        @Value("${app.demo-users.teacher.password}") String teacherPassword) {
        return username -> {
            var account = userRepository.findById(username)
                .filter(user -> user.isActive())
                .orElseThrow(() -> new UsernameNotFoundException("Cuenta no disponible"));
            String password = switch (account.getUsername()) {
                case "encargada.demo" -> guardianPassword;
                case "sin-vinculos.demo" -> emptyGuardianPassword;
                case "direccion.demo" -> directorPassword;
                case "docente.demo" -> teacherPassword;
                default -> throw new UsernameNotFoundException("Cuenta sin credencial de demostración");
            };
            return User.withUsername(account.getUsername())
                .password(passwordEncoder.encode(password))
                .roles(account.getRoleName())
                .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
