package org.bees.admin.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResponse {

    private Date timestamp;
    private String path;
    private String status;
    private String error;
    private List<String> details;

    public ErrorResponse(Date timestamp, String path, String status, String error, String details) {
        this.timestamp = timestamp;
        this.path = path;
        this.status = status;
        this.error = error;
        this.details = Collections.singletonList(details);
    }

    public ErrorResponse(Date timestamp, String path, String status, String error, String... details) {
        this.timestamp = timestamp;
        this.path = path;
        this.status = status;
        this.error = error;
        this.details = Arrays.asList(details);
    }


    public ErrorResponse(Date timestamp, String path, String status, String error, List<String> details) {
        this.timestamp = timestamp;
        this.path = path;
        this.status = status;
        this.error = error;
        this.details = details;
    }
}
