package fi400.glass_bottle.model

import org.springframework.util.MultiValueMap

interface OAuthLoginParams {
    fun oAuthProvider(): OAuthProvider
    fun makeBody(): MultiValueMap<String, String>
}