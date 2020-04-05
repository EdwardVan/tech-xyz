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
public class IllegalArgumentException extends RuntimeException {
    private int status;
    private String msg;
}
