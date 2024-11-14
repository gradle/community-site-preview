package org.gradle.api.experimental.android.extensions.testing;

import org.gradle.api.artifacts.dsl.Dependencies;
import org.gradle.api.artifacts.dsl.DependencyCollector;
import org.gradle.declarative.dsl.model.annotations.Restricted;

@SuppressWarnings("UnstableApiUsage")
@Restricted
public interface AndroidTestDependencies extends Dependencies {
    DependencyCollector getImplementation();
    DependencyCollector getCompileOnly();
    DependencyCollector getRuntimeOnly();
    DependencyCollector getAndroidImplementation();
}
