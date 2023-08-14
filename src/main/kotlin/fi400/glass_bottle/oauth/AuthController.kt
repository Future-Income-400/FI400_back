package fi400.glass_bottle.oauth

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val oAuthLoginService: OAuthLoginService
) {

    @PostMapping("/kakao")
    fun loginKakao(@RequestBody params: KakaoLoginParams): ResponseEntity<AuthTokens> {
        return ResponseEntity.ok(oAuthLoginService.login(params))
    }

}