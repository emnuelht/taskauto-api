plugins {
	java
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.emnuelht"
version = "0.0.1"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven("https://repo.spring.io/milestone")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.ai:spring-ai-bom:1.0.0-M6")
	}
}

dependencies {
	// Swagger OpenAI
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.8")

	// Web
	implementation("org.springframework.boot:spring-boot-starter-web")

	// IA
	implementation("org.springframework.ai:spring-ai-ollama-spring-boot-starter")

	// JPA + PostgreSQL
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.postgresql:postgresql")

	// Validação
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Jackson
	implementation("com.fasterxml.jackson.core:jackson-databind")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Testes
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}