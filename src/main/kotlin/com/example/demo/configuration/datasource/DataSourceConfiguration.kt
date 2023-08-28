package com.example.demo.configuration.datasource

import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct
import javax.sql.DataSource

@Configuration
class DataSourceConfiguration(dataSourceProperties: DataSourceProperties) {
    private val dataSourceProperties: DataSourceProperties

    init {
        this.dataSourceProperties = dataSourceProperties
    }

    @Bean
    fun dataSource(): DataSource {
        val customDataSource = TenantRoutingDataSource()
        customDataSource.setTargetDataSources(dataSourceProperties.getDatasources())
        return customDataSource
    }

    @PostConstruct
    fun migrateAll() {
        dataSourceProperties
            .getDatasources()
            .values
            .stream()
            .map { dataSource -> dataSource }
            .forEach{migrate(it as DataSource)}
    }

    private fun migrate(dataSource: DataSource) {
        val flyway = Flyway.configure().dataSource(dataSource).load()
        flyway.migrate()
    }
}
