package com.ucc.vacCauca.domain.entity;

import com.ucc.vacCauca.domain.log.DadLog;
import com.ucc.vacCauca.enums.RegisterStatusEnum;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Embeddable
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RegisterStatusEnum status;

    public AbstractEntity() {
    }

    public AbstractEntity(RegisterStatusEnum status) {
        this.status = status;
    }

    public AbstractEntity(DadLog dadLog) {
        this.status = dadLog.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegisterStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RegisterStatusEnum status) {
        this.status = status;
    }

}
