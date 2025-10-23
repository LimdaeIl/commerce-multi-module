package auditing;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "app.auditing")
class AuditingProperties {

    private String zone = "Asia/Seoul";
    private boolean useLocalDateTime = true; // true: LocalDateTime, false: ZonedDateTime
}
