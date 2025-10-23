package auditing;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;

    @Column
    private LocalDateTime deletedAt;

    @Column
    private Long deletedBy;

    public void markDeleted(Long by, LocalDateTime at) {
        this.deletedBy = by;
        this.deletedAt = at;
    }

    public void restore() {
        this.deletedBy = null;
        this.deletedAt = null;
    }

    @Transient
    public boolean isDeleted() {
        return deletedAt != null;
    }

    protected void setDeletedAt(LocalDateTime at) {
        // 엔티티가 “KST/UTC” 정책을 몰라야 테스트와 재사용이 쉬운 점을 고려했습니다.
        this.deletedAt = at;
    }

    protected void setDeletedBy(Long by) {
        this.deletedBy = by;
    }
}
