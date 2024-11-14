package org.gradle.api.experimental.jvm;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.experimental.jvm.internal.JvmPluginSupport;
import org.gradle.api.internal.plugins.software.SoftwareType;
import org.gradle.api.plugins.JavaLibraryPlugin;
import org.gradle.api.tasks.SourceSet;
import org.gradle.jvm.toolchain.JavaToolchainService;

import javax.inject.Inject;

/**
 * Creates a declarative {@link JvmLibrary} DSL model, applies the official Jvm plugin,
 * and links the declarative model to the official plugin.
 */
public abstract class StandaloneJvmLibraryPlugin implements Plugin<Project> {

    public static final String JVM_LIBRARY = "jvmLibrary";

    @SuppressWarnings("UnstableApiUsage")
    @SoftwareType(name = JVM_LIBRARY, modelPublicType = JvmLibrary.class)
    public abstract JvmLibrary getJvmLibrary();

    @Override
    public void apply(Project project) {
        JvmLibrary dslModel = getJvmLibrary();

        project.getPlugins().apply(JavaLibraryPlugin.class);

        linkDslModelToPlugin(project, dslModel);
    }

    @Inject
    protected abstract JavaToolchainService getJavaToolchainService();

    private void linkDslModelToPlugin(Project project, JvmLibrary dslModel) {

        SourceSet commonSources = JvmPluginSupport.setupCommonSourceSet(project);
        JvmPluginSupport.linkSourceSetToDependencies(project, commonSources, dslModel.getDependencies());

        JvmPluginSupport.linkJavaVersion(project, dslModel);

        dslModel.getTargets().withType(JavaTarget.class).all(target -> {
            SourceSet sourceSet = JvmPluginSupport.createTargetSourceSet(project, target, commonSources, getJavaToolchainService());

            // Link dependencies to DSL
            JvmPluginSupport.linkSourceSetToDependencies(project, sourceSet, target.getDependencies());

            // Extend common dependencies
            project.getConfigurations().getByName(sourceSet.getApiConfigurationName())
                    .extendsFrom(project.getConfigurations().getByName(commonSources.getApiConfigurationName()));
        });
    }

}
