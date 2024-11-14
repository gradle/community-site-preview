package org.gradle.api.experimental.common;


import org.gradle.api.artifacts.dsl.Dependencies;
import org.gradle.api.artifacts.dsl.DependencyCollector;

/**
 * Basic types of dependencies used by either an application or library, for production or test code.
 */
@SuppressWarnings("UnstableApiUsage")
public interface BasicDependencies extends Dependencies {
    DependencyCollector getImplementation();
    DependencyCollector getRuntimeOnly();
    DependencyCollector getCompileOnly();
}
