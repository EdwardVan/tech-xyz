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
        log.info("This is TestImportSelector.class log");
        return new String[0];
    }
}
