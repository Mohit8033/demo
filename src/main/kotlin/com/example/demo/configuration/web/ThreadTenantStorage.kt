package com.example.demo.configuration.web

object ThreadTenantStorage {
    private val currentTenant = ThreadLocal<String>()

    fun setTenantId(tenantId: String) {
        currentTenant.set(tenantId)
    }

    fun getTenantId(): String? {
        return currentTenant.get()
    }

    fun clear() {
        currentTenant.remove()
    }
}


