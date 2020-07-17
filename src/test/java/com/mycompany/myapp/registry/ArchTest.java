package com.mycompany.myapp.registry;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.mycompany.myapp.registry");

        noClasses()
            .that()
            .resideInAnyPackage("com.mycompany.myapp.registry.service..")
            .or()
            .resideInAnyPackage("com.mycompany.myapp.registry.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.mycompany.myapp.registry.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
