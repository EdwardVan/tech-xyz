package tech.edwardvan.testspringbootautoconfigure.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

/**
 * ConditionalTest的条件判断
 *
 * @author EdwardVan
 */
public class TestCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object> allAnnotationAttributes = metadata.getAllAnnotationAttributes(ConditionalTest.class.getName());
        return true;
    }
}
