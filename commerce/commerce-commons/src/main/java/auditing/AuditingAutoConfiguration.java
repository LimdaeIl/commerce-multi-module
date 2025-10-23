package auditing;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@AutoConfiguration
@ConditionalOnClass(DateTimeProvider.class)
@EnableConfigurationProperties(AuditingProperties.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAware",
        dateTimeProviderRef = "auditingDateTimeProvider")
public class AuditingAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    AuditorAware<Long> auditorAware() {
        // TODO: 나중에 공통 필터/시큐리티 컨텍스트에서 가져오도록 교체 가능
        return () -> Optional.of(0L);
    }

    @Bean
    @ConditionalOnMissingBean(name = "auditingDateTimeProvider")
    DateTimeProvider auditingDateTimeProvider(AuditingProperties props) {
        ZoneId zone = ZoneId.of(props.getZone());
        if (props.isUseLocalDateTime()) {
            return () -> Optional.of(LocalDateTime.now(zone));
        } else {
            return () -> Optional.of(ZonedDateTime.now(zone));
        }
    }
}