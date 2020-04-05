package tech.edwardvan.msspringcloudcommon.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author EdwardVan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private int status;
    private String msg;
}
