package fi400.glass_bottle.oauth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtTokenProvider(@Value("\${jwt.secret-key}") secretKey: String) {

    private val key: Key

    init {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        this.key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun generate(subject: String, expiredAt: Date): String {
        return Jwts.builder()
            .setSubject(subject)
            .setExpiration(expiredAt)
            .signWith(key)
            .compact()
    }

    fun extractSubject(accessToken: String): String {
        val claims = parseClaims(accessToken)
        return claims.subject
    }

    private fun parseClaims(accessToken: String): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .body
        } catch (e: ExpiredJwtException) {
            e.claims
        }
    }
}