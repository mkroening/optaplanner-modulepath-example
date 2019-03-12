plugins {
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.optaplanner:optaplanner-core:7.18.0.Final")
    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
}

application {
    mainClassName = "io.github.mwkroening.optaplannermodulepathexample.App"
}
