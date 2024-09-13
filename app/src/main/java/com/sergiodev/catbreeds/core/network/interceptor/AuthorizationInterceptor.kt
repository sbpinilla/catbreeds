package com.sergiodev.catbreeds.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

/**
 * Interceptor para autorización de peticiones, consulta el servicio de autenticación
 * para asegurar el acceso a usuarios autenticados y autorizados solamente.
 * @author sbpinilla
 * @version 1.0
 */
class AuthorizationInterceptor @Inject constructor() : Interceptor {

    companion object {
        const val HEADER_AUTH = "x-api-key"

    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        return chain.proceed(getRequestBuilder(request))

    }

    private fun getRequestBuilder(request: Request): Request {

        val newRequestBuilder = request.newBuilder()
        val token = "live_99Qe4Ppj34NdplyLW67xCV7Ds0oSLKGgcWWYnSzMJY9C0QOu0HUR4azYxWkyW2nr"
        newRequestBuilder.addHeader(HEADER_AUTH, token)
        newRequestBuilder.method(request.method, request.body)
        return newRequestBuilder.build()

    }


}