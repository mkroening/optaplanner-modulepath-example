plugins {
    application
    id("org.javamodularity.moduleplugin") version "1.5.0"
}

patchModules.config = listOf(
        // The package org.xmlpull.v1 is split between xpp3.min and xmlpull
        // This workaround patches the xmlpull module to include the contents of the xpp3_min artifact
        // See https://github.com/x-stream/xstream/issues/120
        "xmlpull=xpp3_min-1.1.4c.jar"
)

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
