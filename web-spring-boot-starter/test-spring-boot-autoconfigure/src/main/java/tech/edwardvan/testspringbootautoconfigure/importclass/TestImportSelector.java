package tech.edwardvan.testspringbootautoconfigure.importclass;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author EdwardVan
 */
@Slf4j
public class TestImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        log.warn("This is {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return new String[0];
    }
}
