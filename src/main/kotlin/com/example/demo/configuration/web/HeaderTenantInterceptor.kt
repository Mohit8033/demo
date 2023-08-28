package com.example.demo.configuration.web

import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor


@Component
class HeaderTenantInterceptor : WebRequestInterceptor {
    @Throws(Exception::class)
    override fun preHandle(request: WebRequest) {
        ThreadTenantStorage.setTenantId(request.getHeader(TENANT_HEADER)!!)
    }

    @Throws(Exception::class)
    override fun postHandle(request: WebRequest, model: ModelMap?) {
        ThreadTenantStorage.clear()
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: WebRequest, ex: Exception?) {
    }

    companion object {
        const val TENANT_HEADER = "X-tenant"
    }
}
