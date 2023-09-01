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

    /**
     * id로 특정 Letter 가져오는 메소드
     * return Optional<Letter>
     */
//    fun getLetterByUserId(userId: Long): Letter? {
//        return letterRepository.findByUserId(userId)
//            .orElseThrow{ NullPointerException("Letter not found for ID: $userId") }
//    }

    /**
     * 사용자 id를 받아 해당 사용자가 작성한 Letter 가져옴
     * return List<Letter>
     */
//    fun getMyLetter(id: Long): List<Letter> {
//        val user = userRepository.findById(id).getOrElse { throw NotFoundException() }
//        val sendList: List<Letter> = user.letterList.orEmpty().filter { letter: Letter -> letter.letterStatus == LetterStatus.SEND || letter.letterStatus == LetterStatus.SENDING}
//        return sendList
//    }
    /**
     * Letter 객체를 받아 객체 생성
     * return responseCode
     */
//    fun createMyLetter(letter: Letter): ResponseEntity<Letter> {
//        var responseCode: HttpStatus = HttpStatus.BAD_REQUEST
//        try {
//            letterRepository.save(letter)
//            responseCode = HttpStatus.OK
//        } catch (e: Exception) {
//            print(e)
//        }
//        return ResponseEntity(responseCode)
//    }
    /**
     * Letter 객체 업데이트
     */
//    fun updateMyLetter(letter: Letter): ResponseEntity<Any> {
//        var responseCode: HttpStatus = HttpStatus.BAD_REQUEST
//        try {
//            letterRepository.save(letter)
//            responseCode = HttpStatus.OK
//        } catch (e: Exception) {
//            print(e)
//        }
//        return ResponseEntity(responseCode)
//    }
    /**
     * Letter 객체 삭제
     */
//    fun deleteMyLetter(letter: Letter): ResponseEntity<Any> {
//        var responseCode: HttpStatus = HttpStatus.BAD_REQUEST
//        try {
//            letterRepository.delete(letter)
//            responseCode = HttpStatus.OK
//        } catch (e: Exception) {
//            print(e)
//        }
//        return ResponseEntity(responseCode)
//    }