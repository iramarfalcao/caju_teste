package com.caju.containers

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.testcontainers.containers.PostgreSQLContainer

class PostgresTestContainer : QuarkusTestResourceLifecycleManager {

    private val db: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:16")
        .withDatabaseName("test")
        .withUsername("test")
        .withPassword("test")
        .withInitScript("init-data-test.sql")

    override fun start(): Map<String, String> {
        return try {
            db.start()
            mapOf(
                "quarkus.datasource.jdbc.url" to db.jdbcUrl,
                "quarkus.datasource.username" to db.username,
                "quarkus.datasource.password" to db.password
            )
        } catch (e: Exception) {
            throw RuntimeException("Falha ao iniciar o container PostgreSQL", e)
        }
    }

    override fun stop() {
        if (db.isRunning) {
            db.stop()
        }
    }
}
