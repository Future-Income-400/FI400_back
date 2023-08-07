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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long,

    @Column (name ="name")
    var name : String,

    @Column
    var password : String,

    @Column
    var email : String,

    @Column(name="oauth_provider")
    @Enumerated(EnumType.STRING)
    val oAuthProvider: OAuthProvider,

    // accesstoken
    // refreshtoken

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    //@OneToMany
    //val letterList : List<Letter>

)
