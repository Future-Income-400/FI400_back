package fi400.glass_bottle.model.entity

import jakarta.persistence.*
import lombok.Builder.Default
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

/**
 * Letter
 * 편지 모델
 * @property id
 * @property content
 * @property contentSummary
 * @property letterStatus
 * @property user
 * @property createdDate
 * @constructor Create empty Letter
 * @since 2023.08.08
 */

@Entity
@Table(name = "TBL_LETTER")
@EntityListeners(AuditingEntityListener::class)
data class Letter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long,

    @Column
    var content : String,

    @Column(name="content_summary")
    var contentSummary : String,

    @Column(name="letter_status")
    @Enumerated(EnumType.STRING)
    var letterStatus : LetterStatus,

    @Column(name="is_activated", columnDefinition = "CHAR(1) DEFAULT 'Y'")
    var isActivated : Char,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User?,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    val createdDate: LocalDateTime = LocalDateTime.now(),
) {
    constructor() : this(0, "", "", LetterStatus.SENDING, 'Y', null, LocalDateTime.now())
}