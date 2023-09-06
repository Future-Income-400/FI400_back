package fi400.glass_bottle.service

import fi400.glass_bottle.commons.utils.logger
import fi400.glass_bottle.model.entity.Letter
import fi400.glass_bottle.model.entity.LetterStatus
import fi400.glass_bottle.model.entity.User
import fi400.glass_bottle.repository.LetterRepository
import fi400.glass_bottle.repository.UserRepository
import jdk.jshell.spi.ExecutionControl.InternalException
import lombok.RequiredArgsConstructor
import org.apache.coyote.Response
import org.hibernate.exception.DataException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import java.util.Optional
import kotlin.jvm.optionals.getOrDefault
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull

@Service
@RequiredArgsConstructor
class LetterService(private val userRepository: UserRepository,
                    private val letterRepository: LetterRepository) {

    // logger
    private val log = this.logger()

    /**
     * READ
     * 편지 가져오기
     * @param letterId : Long
     * @return Letter : Object or throw NullPointerException
     */
    fun getLetterById(letterId: Long): Letter? {
        return letterRepository.findById(letterId)
            .orElseThrow { NullPointerException("Letter not found for ID: $letterId") }
    }

    /**
     * CREATE, UPDATE, DELETE
     * 편지 저장, 수정, 삭제
     * @param letter
     * @return letter : Object or throw Exception
     */
    fun draftLetter(letter: Letter): Letter? {
        return letterRepository.save(letter)
    }

}