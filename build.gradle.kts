plugins {
    application
    id("org.javamodularity.moduleplugin") version "1.5.0"
    id("org.beryx.jlink") version "2.10.3"
}

patchModules.config = listOf(
        // The package org.xmlpull.v1 is split between xpp3.min and xmlpull
        // This workaround patches the xmlpull module to include the contents of the xpp3_min artifact
        // See https://github.com/x-stream/xstream/issues/120
        "xmlpull=xpp3_min-1.1.4c.jar"
)

jlink {
    addOptions("--bind-services")

    // based on `./gradlew suggestMergedModuleInfo --language=kotlin`
    mergedModule {
        requires("java.management")
        requires("java.naming")
        requires("java.logging")
        requires("java.security.sasl")
        requires("java.scripting")
        requires("java.sql")
        requires("jdk.jdi")
        requires("java.xml")
        requires("java.desktop")
        requires("java.datatransfer")
        requires("java.compiler")
        requires("jdk.unsupported")
        uses("javax.annotation.processing.Processor")
        uses("org.kie.internal.runtime.conf.ObjectModelResolver")

        // PatternCompiler is not public in com.google.common.base; cannot be accessed from outside package
        // uses("com.google.common.base.PatternCompiler");

        // the service implementation type must be a subtype of the service interface type, or have a public static no-args method named "provider" returning the service implementation
        // provides("org.xmlpull.v1.XmlPullParserFactory").with("org.xmlpull.mxp1.MXParser,org.xmlpull.mxp1_serializer.MXSerializer");

        provides("javax.script.ScriptEngineFactory").with("org.mvel2.jsr223.MvelScriptEngineFactory")
        provides("javax.tools.JavaCompiler").with("org.eclipse.jdt.internal.compiler.tool.EclipseCompiler")

        // Manual additions
        requires("org.slf4j")
    }

    launcher {
        jvmArgs = listOf(
                // Drools requires to access java.lang.ClassLoader.defineClass
                "--add-opens", "java.base/java.lang=optaplanner.modulepath.example.merged.module",

                // XStream requires us to manually open up the JDK
                // See https://github.com/x-stream/xstream/issues/101
                "--add-opens", "java.base/java.util=optaplanner.modulepath.example.merged.module",
                "--add-opens", "java.base/java.lang.reflect=optaplanner.modulepath.example.merged.module",
                "--add-opens", "java.base/java.text=optaplanner.modulepath.example.merged.module",
                "--add-opens", "java.desktop/java.awt.font=optaplanner.modulepath.example.merged.module"
        )
    }
}


application {
    mainClassName = "$moduleName/io.github.mwkroening.optaplannermodulepathexample.App"
    applicationDefaultJvmArgs = listOf(
            // Workaround for OptaPlanner solverConfig.xml loading
            // (org.optaplanner.core.config.util.ConfigUtils)
            "--add-modules", "java.scripting",

            // Drools requires to access java.lang.ClassLoader.defineClass
            "--add-opens", "java.base/java.lang=org.drools.core",

            // XStream requires us to manually open up the JDK
            // See https://github.com/x-stream/xstream/issues/101
            "--add-opens", "java.base/java.util=xstream",
            "--add-opens", "java.base/java.lang.reflect=xstream",
            "--add-opens", "java.base/java.text=xstream",
            "--add-opens", "java.desktop/java.awt.font=xstream"
    )
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.optaplanner:optaplanner-core:7.21.0.Final")
    implementation("org.slf4j:slf4j-api:1.8.0-beta4")
    runtimeOnly("ch.qos.logback:logback-classic:1.3.0-alpha4")
}
