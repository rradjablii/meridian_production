package com.rradjabli.meridian.Audit.dto;

import com.rradjabli.meridian.common.enums.RequestState;
import com.rradjabli.meridian.common.enums.RequestType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuditRequestDTO {

    public AuditRequestDTO(UUID requestFrom, RequestType requestType){
        this.requestFrom=requestFrom;
        this.requestType=requestType;
        this.requestState=RequestState.STARTED;
    }

    public AuditRequestDTO(UUID requestFrom, RequestType requestType, RequestState requestState){}

    private UUID requestId;
    private UUID attemptId;
    private Integer attemptNumber=0;
    @NotBlank
    private UUID requestFrom;
    @NotBlank
    private RequestType requestType;
    @NotBlank
    private RequestState requestState;

}
