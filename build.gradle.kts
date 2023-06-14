plugins {
	java
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.apirest"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("io.r2dbc:r2dbc-postgresql:0.8.10.RELEASE")
	implementation("io.projectreactor:reactor-tools:3.5.5")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.0.4")
    testImplementation("junit:junit:4.13.1")

    compileOnly("org.projectlombok:lombok")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("io.projectreactor.tools:blockhound:1.0.8.RELEASE")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
