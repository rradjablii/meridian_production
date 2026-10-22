package com.rradjabli.meridian.audit.entity;

import com.rradjabli.meridian.common.enums.RequestType;
import com.rradjabli.meridian.common.enums.RequestState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "audit_logs")
public class Audit {

    public Audit(RequestType requestType, UUID requestFrom){
        this.requestFrom=requestFrom;
        this.requestType=requestType;
    }

    @Id
    @UuidGenerator
    @Column(name = "request_id")
    private UUID requestId;

    @Column(name = "attempt_id")
    @UuidGenerator
    private UUID attemptId;

    @Column(name = "attempt_number")
    private Integer attemptNumber=0;

    @NotNull
    @Column(name = "request_from")
    private UUID requestFrom;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type")
    private RequestType requestType;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_state")
    private RequestState requestState = RequestState.STARTED;
}
