package fi400.glass_bottle.model.entity

import fi400.glass_bottle.oauth.OAuthProvider
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import org.springframework.data.jpa.domain.support.AuditingEntityListener

/**
 * User
 * 사용자 모델
 * @property id
 * @property name
 * @property password
 * @property email
 * @property bottleCount
 * @property oAuthProvider
 * @property letterList
 * @property createdDate
 * @constructor Create empty User
 * @since 2023.08.08
 */
@Entity
@Table(name = "TBL_USER")
@EntityListeners(AuditingEntityListener::class)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var nickname: String? = null,

    @Column
    var password: String? = null,

    @Column
    var email: String? = null,

    @Column(name = "bottle_cnt")
    var bottleCount: Int = 0,

    @Column(name="oauth_provider")
    @Enumerated(EnumType.STRING)
    var oAuthProvider: OAuthProvider,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var letterList: MutableList<Letter>? = mutableListOf(),

    @Column(name="is_deleted", columnDefinition = "CHAR(1) DEFAULT 'Y'")
    var isDeleted : Char,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),
)
{
    /**
     * Print letters
     * void
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
