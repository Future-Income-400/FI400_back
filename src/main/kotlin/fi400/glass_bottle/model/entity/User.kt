package fi400.glass_bottle.model.entity

import fi400.glass_bottle.model.OAuthProvider
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name = "TBL_USER")
@EntityListeners(AuditingEntityListener::class)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column
    var name: String,

    @Column
    var password: String,

    @Column
    var email: String,

    @Column(name = "bottle_cnt")
    var bottleCount: Int,

    @Column(name="oauth_provider")
    @Enumerated(EnumType.STRING)
    var oAuthProvider: OAuthProvider,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val letterList: MutableList<Letter>? = mutableListOf(),

    @CreatedDate
    @Column(nullable = false, updatable = false)
    val createdDate: LocalDateTime = LocalDateTime.now(),
)
{
    /**
     * Letter List를 출력하는 내장 메소드
     */
    fun printLetters() {
        letterList?.forEach { letter ->
            println("Letter ID: ${letter.id}")
            println("Content: ${letter.content}")
            println("Summary: ${letter.contentSummary}")
            println("Status: ${letter.letterStatus}")
            println("Created Date: ${letter.createdDate}")
            println("-------------------------")
        }
    }
}
