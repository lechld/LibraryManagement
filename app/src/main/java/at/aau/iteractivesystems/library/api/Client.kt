package at.aau.iteractivesystems.library.api

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*

private const val TIMEOUT = 60000

val ktorHttpClient = HttpClient(Android) {

    expectSuccess = true // throw exception on non 2xx responses

    install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    engine {
        connectTimeout = TIMEOUT
        socketTimeout = TIMEOUT
    }

    install(ResponseObserver) {
        onResponse { response ->
            Log.d("HTTP status", "${response.status.value}")
        }
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}