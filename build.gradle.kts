plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
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
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("junit:junit:4.13.1")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	annotationProcessor("org.projectlombok:lombok")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")
	implementation("io.vertx:vertx-core:3.5.3")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
