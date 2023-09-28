import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.6.21"
	kotlin("kapt") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("plugin.jpa") version "1.6.10"
	id("org.springframework.boot") version "2.6.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.sonarqube") version "3.0"
	id("net.linguica.maven-settings") version "0.5"
	id("org.flywaydb.flyway") version "8.2.0"
	java
	jacoco
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	maven {
		name = "nexus"
		url = uri("https://nexus.mercuryonline.co/nexus/content/groups/public")
	}
	mavenCentral()
}

extra["springBootVersion"] = "2.7.0"
extra["springCloudVersion"] = "2021.0.3"
extra["springCloudStreamVersion"] = "3.2.4"
extra["springRetryVersion"] = "1.3.1"
extra["utilsVersion"] = "2022.0.21"
extra["mysqlVersion"] = "8.0.16"
extra["h2Version"] = "1.4.200"
extra["jupiterVersion"] = "5.7.1"
extra["awsSdkVersion"] = "2.16.48"
extra["feignVersion"] = "11.8"
extra["sentryVersion"] = "5.0.1"
extra["jwtVersion"] = "3.14.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.pharmeasy:jwt-auth-util:${property("utilsVersion")}")
	implementation("com.pharmeasy:mercury-model:${property("utilsVersion")}")
	implementation("com.pharmeasy:micro-service-util:${property("utilsVersion")}")
	implementation("com.pharmeasy:tenancy-util:${property("utilsVersion")}")
	implementation("mysql:mysql-connector-java:${property("mysqlVersion")}")
	implementation("mysql:mysql-connector-java:8.0.26")
	implementation("org.flywaydb:flyway-core:8.2.2")
	implementation("org.flywaydb:flyway-sqlserver:8.2.2")
	testImplementation("org.flywaydb.flyway-test-extensions:flyway-spring-test:7.0.0")
	compileOnly("org.projectlombok:lombok")
	compileOnly("org.flywaydb:flyway-core")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.flywaydb:flyway-core")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.boot:spring-boot-dependencies:${property("springBootVersion")}")
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
		mavenBom("org.springframework.cloud:spring-cloud-stream-dependencies:${property("springCloudStreamVersion")}")
		mavenBom("software.amazon.awssdk:bom:${property("awsSdkVersion")}")
		mavenBom("io.awspring.cloud:spring-cloud-aws-dependencies:2.3.3")
		mavenBom("org.junit:junit-bom:${property("jupiterVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

flyway {
	driver = "com.mysql.cj.jdbc.Driver"
	url = "jdbc:mysql://127.0.0.1"
	user = "springstudent"
	password = "springstudent"
	baselineOnMigrate = true
	schemas = arrayOf("t1","t2","t3","t4")
}

tasks.register("flyway_migrate_tenant", org.flywaydb.gradle.task.FlywayMigrateTask::class) {
	val tenant = project.findProperty("tenant") as String?
	url = "jdbc:mysql://127.0.0.1"
	user = "springstudent"
	password = "springstudent"
	schemas = arrayOf(tenant)
	baselineOnMigrate = true
	locations = arrayOf("filesystem:src/main/resources/db/migration")
	dependsOn("classes")
}