package tech.edwardvan.msspringcloudcommon.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author EdwardVan
 */
@Configuration
@ComponentScan("tech.edwardvan.msspringcloudclient.fallback")
public class FeignConfig {
}
