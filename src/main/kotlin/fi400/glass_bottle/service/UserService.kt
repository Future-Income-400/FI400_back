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
import org.springframework.util.ObjectUtils
import org.springframework.web.bind.annotation.PostMapping
import java.util.Optional
import kotlin.jvm.optionals.getOrDefault
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull

@Service
@RequiredArgsConstructor
class UserService(private val userRepository: UserRepository,
                  private val letterRepository: LetterRepository) {

    // logger
    private val log = this.logger()

    /**
     * READ
     * 아이디로 해당 유저 조회 후 해당 유저의 Letter 반환
     * @param userId : Long
     * @return List<Letter> : List or EmptyList
     */
    fun getLettersByUserId(userId: Long, letterStatus: String): List<Letter>? {
        val user = userRepository.findById(userId).orElseThrow { NullPointerException("Can not found By Id: $userId") }
        var letterList: List<Letter>

        // letterStatus가 null 값일 때에는 모든 상태 리턴
        if (ObjectUtils.isEmpty(letterStatus)) {
            return user.letterList
        }

        // 특정 letterStatus 일때 처리
        try {
            letterList = user.letterList.orEmpty().filter {
                it.letterStatus == LetterStatus.valueOf(letterStatus.uppercase())
            }
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid letter status: ${letterStatus}")
        }

        return letterList
    }

    /**
     * CREATE, UPDATE, DELETE
     * 유저 수정
     * @param user
     * @return message: 상태 메시지
     */
    fun updateUser(user: User) {
        userRepository.save(user)
    }
}
