package com.example.acceptancetestisolation.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * id 'org.springframework.boot' version '2.7.0' 이상일 때
 */
@Profile("test")
@Component
public class DatabaseCleanup implements InitializingBean {
    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tableNames;

    /**
     * .filter(it -> it.getJavaType().getDeclaredAnnotation(Entity.class) != null)
     */
    @Override
    public void afterPropertiesSet() {
        tableNames = entityManager.getMetamodel().getEntities().stream()
                .map(this::extractTableName)
                .collect(Collectors.toList());
    }

    private String extractTableName(EntityType<?> entity) {
        Table declaredAnnotation = entity.getJavaType().getDeclaredAnnotation(Table.class);
        if (declaredAnnotation == null) {
            return convertCamelToSnake(entity.getName());
        }
        return declaredAnnotation.name();
    }

    private String convertCamelToSnake(String str){
        String regex = "([a-z])([A-Z])";
        String replacement = "$1_$2";
        return str.replaceAll(regex, replacement).toLowerCase();
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        for (String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1")
                    .executeUpdate();
        }
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
