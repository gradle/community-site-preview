package org.gradle.util

import spock.lang.Specification

import static org.gradle.test.util.TestAssertions.assertDirContainsExactly

class ResourceLoaderIntegrationTest extends Specification {
    private File outputDir = new File("build/tmp/integTest/output").tap {
        deleteDir()
        mkdirs()
    }

    def "can load resource from jar file"() {
        given:
        ResourceLoader resourceLoader = new ResourceLoader()

        when:
        resourceLoader.extractDirectoryFromResources("templates/java-library", outputDir)

        then:
        assertDirContainsExactly(outputDir, ['build.gradle.dcl', 'src/main/java/com/example/lib/Library.java'])
    }
}
