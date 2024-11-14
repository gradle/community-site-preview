package org.gradle.test.fixtures


import org.gradle.internal.impldep.org.junit.Rule
import org.gradle.test.fixtures.file.TestFile
import org.gradle.test.fixtures.file.TestNameTestDirectoryProvider
import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.FAILED
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class AbstractSpecification extends Specification {
    @Rule
    public final TestNameTestDirectoryProvider temporaryFolder = new TestNameTestDirectoryProvider(getClass())
    private TestFile testDirOverride = null

    File settingsFile
    File buildFile

    BuildResult result

    def setup() {
        settingsFile = file('settings.gradle.dcl')
        buildFile = file('build.gradle.dcl')
    }

    TestFile file(Object... path) {
        if (path.length == 1 && path[0] instanceof TestFile) {
            return path[0] as TestFile
        }
        getTestDirectory().file(path)
    }

    TestFile getTestDirectory() {
        if (testDirOverride != null) {
            return testDirOverride
        }
        temporaryFolder.testDirectory
    }

    def succeeds(String... tasks) {
        result = GradleRunner.create()
                .withProjectDir(getTestDirectory())
                .withArguments(tasks)
                .withPluginClasspath()
                .build()
        tasks.each { task ->
            assert result.task(task).outcome == SUCCESS
        }
    }

    def fails(String... tasks) {
        result = GradleRunner.create()
                .withProjectDir(getTestDirectory())
                .withArguments(tasks)
                .withPluginClasspath()
                .run()
        tasks.each { task ->
            assert result.task(task).outcome == FAILED
        }
    }
}
