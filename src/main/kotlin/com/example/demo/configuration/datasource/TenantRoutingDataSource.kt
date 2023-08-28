package com.example.demo.configuration.datasource

import com.example.demo.configuration.web.ThreadTenantStorage
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

class TenantRoutingDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any? {
        return ThreadTenantStorage.getTenantId()
    }
}