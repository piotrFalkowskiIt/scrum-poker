plugins {
    id("org.springframework.boot") version "2.3.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("multiplatform") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    kotlin("plugin.jpa") version "1.3.72"
    kotlin("plugin.allopen") version "1.3.61"

}

group = "uk.co.objectivity"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    jcenter()
}

extra["springCloudVersion"] = "Hoxton.SR4"

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

kotlin {
    jvm() {
        tasks.register<Copy>("copyJsToJvm") {
            eachFile(::println)
            from("$buildDir/distributions")
            include("*.js", "*.css")
            into( "$buildDir/processedResources/jvm/main/static")
        }
        tasks.named("jvmProcessResources") {
            dependsOn(":jsBrowserDistribution", ":copyJsToJvm")
        }

    }
    js {
        browser {

        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("org.springframework.boot:spring-boot-starter-actuator")
                implementation("org.springframework.boot:spring-boot-starter-data-jpa")
                implementation("org.springframework.boot:spring-boot-starter-web")
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
                implementation("org.jetbrains.kotlin:kotlin-reflect")
                implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
                implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
                implementation("org.springframework.boot:spring-boot-devtools")
                runtimeOnly("com.h2database:h2")
                implementation("org.latencyutils:LatencyUtils:2.0.3")
                implementation("org.springframework.boot:spring-boot-starter-test") {
                    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
                }
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.mockito:mockito-core")
                implementation(kotlin("test-junit"))
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains:kotlin-react:16.13.1-pre.106-kotlin-1.3.72")
                implementation("org.jetbrains:kotlin-react-dom:16.13.1-pre.106-kotlin-1.3.72")
                implementation(npm("react", "16.13.1"))
                implementation(npm("react-dom", "16.13.1"))

            }
        }

        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}


//tasks.named<org.gradle.jvm.tasks.Jar>("jvmJar") {
//	dependsOn(jsBrowserProductionWebpack)
//	from(new File(jsBrowserProductionWebpack.entry.name, jsBrowserProductionWebpack.outputPath))
//}
//

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.register<JavaExec>("run") {
    dependsOn(":backendJar")
    main = "uk.co.objectivity.scrum.poker.ScrumPokerApplicationKt"
//    classpath(configurations.backendRuntimeClasspath, backendJar)
//    args = []
}
