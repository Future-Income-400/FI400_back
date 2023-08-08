package fi400.glass_bottle.repository

import fi400.glass_bottle.model.entity.Letter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LetterRepository : JpaRepository<Letter, Long> {
}