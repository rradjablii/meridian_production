package com.rradjabli.meridian.Audit.dto;

import com.rradjabli.meridian.common.enums.RequestState;
import com.rradjabli.meridian.common.enums.RequestType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class AuditResponseDTO {

    private UUID requestId;
    private UUID attemptId;
    private Integer attemptNumber;
    private UUID requestFrom;
    private RequestType requestType;
    private RequestState requestState;

}
