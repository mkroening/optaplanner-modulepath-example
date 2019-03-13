plugins {
    application
    id("org.javamodularity.moduleplugin") version "1.4.1"
}

patchModules.config = listOf(
        // The package org.xmlpull.v1 is split between xpp3.min and xmlpull
        // This workaround patches the xmlpull module to include the contents of the xpp3_min artifact
        // See https://github.com/x-stream/xstream/issues/120
        "xmlpull=xpp3_min-1.1.4c.jar"
)

application {
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
    jcenter()
}

dependencies {
    implementation("org.optaplanner:optaplanner-core:7.18.0.Final")
    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
}

application {
    mainClassName = "$moduleName/io.github.mwkroening.optaplannermodulepathexample.App"
}
