package kr.ac.hansung.cse.model;

import lombok.Data;

@Data
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
    private String requestUri;
}
