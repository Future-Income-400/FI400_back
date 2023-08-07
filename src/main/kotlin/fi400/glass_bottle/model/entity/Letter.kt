package fi400.glass_bottle.model.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "TBL_LETTER")
@EntityListeners(AuditingEntityListener::class)
data class Letter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long,
    var content : String,
    @Column (name="content_summary")
    var contentSummary : String,
    @Column (name="letter_status")

    var letterStatus : String,
    @Column (name="user_id")
    var userId : Long,
    @CreatedDate
    var createdDate : LocalDateTime = LocalDateTime.now()
)