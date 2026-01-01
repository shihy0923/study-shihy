# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a multi-module Java learning/study project demonstrating core Java concepts, Spring Boot features, concurrency patterns, and messaging architectures.

## Build Commands

```bash
# Build all modules (dev profile is default)
./mvnw clean install

# Build with test profile
./mvnw clean install -P test

# Build specific module
./mvnw clean install -pl springboot
./mvnw clean install -pl webflux
```

## Run Applications

```bash
# Run Spring Boot application (from springboot module)
./mvnw spring-boot:run -pl springboot

# Run specific main class
./mvnw spring-boot:run -pl springboot -Dspring-boot.run.main-class=com.example.jvm.Application
./mvnw spring-boot:run -pl springboot -Dspring-boot.run.main-class=com.example.aop.Demo1Application
```

## Architecture

**Multi-module Maven project:**
- `springboot/` - Main module with Spring Boot web application (Java 8)
- `webflux/` - Reactive Spring WebFlux module (Java 9)

**Key packages in springboot module (`com.example.*`):**

| Package | Purpose |
|---------|---------|
| `aop` | AOP demonstrations with AspectJ |
| `aqs` | Concurrency primitives (locks, conditions, synchronizers) |
| `classloader` | Custom classloader implementations |
| `jvm` | JVM profiling examples (deadlock, OOM tests) |
| `proxy_dynamic` | JDK and CGLIB dynamic proxy patterns |
| `rabbitmq` | RabbitMQ messaging patterns (direct, pub/sub, federation, sharding, streams) |
| `threadpool` | Thread pool and executor service examples |
| `protostuff` | Binary serialization with Protostuff |
| `springboot` | Spring Boot features (caching, transactions, interceptors) |

**Persistence layer:**
- MyBatis with XML mappers in `springboot/src/main/resources/mapper/`
- MySQL database with Druid connection pool
- Entities: User, Department

## Configuration

- Server port: 8080, context path: `/shihy`
- Profile activation via Maven: `dev` (default) or `test`
- Configuration files in `springboot/src/main/resources/`:
  - `application.properties` - main config
  - `application-dev.properties` - dev profile
  - `application-test.properties` - test profile

## Key Dependencies

- Spring Boot 2.5.9
- MyBatis for data access
- Caffeine for caching
- RabbitMQ (AMQP) for messaging
- Protostuff for serialization
- Hutool utility library
- Lombok for boilerplate reduction
