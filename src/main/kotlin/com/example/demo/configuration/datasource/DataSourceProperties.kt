package com.example.demo.configuration.datasource

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.stereotype.Component
import javax.sql.DataSource


@Component
@ConfigurationProperties(prefix = "tenants")
class DataSourceProperties {
    private val datasources: MutableMap<Any, Any> = LinkedHashMap()
    fun getDatasources(): Map<Any, Any> {
        return datasources
    }

    fun setDatasources(datasources: Map<String, Map<String?, String?>>) {
        datasources
            .forEach { (key: String, value: Map<String?, String?>) ->
                this.datasources[key] = convert(value)
            }
    }

    fun convert(source: Map<String?, String?>): DataSource {
        return DataSourceBuilder.create()
            .url(source["jdbcUrl"])
            .driverClassName(source["driverClassName"])
            .username(source["username"])
            .password(source["password"])
            .build()
    }
}
