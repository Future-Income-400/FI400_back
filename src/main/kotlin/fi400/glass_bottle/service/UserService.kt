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

        if (ObjectUtils.isEmpty(letterStatus)) {
            return user.letterList
        }

        return try {
             user.letterList?.filter {
                it.letterStatus == LetterStatus.valueOf(letterStatus.uppercase())
            }
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid letter status: ${letterStatus}")
        }
    }

    /**
     * CREATE, UPDATE, DELETE
     * 유저 저장, 수정, 삭제
     * @param user
     * @return message: 상태 메시지
     */
    fun draftUser(user: User): String {
        return "나가 시발로마 개새끼야 오팬무 쳐 잘보고 시발!!!!!!!! 나도 간다 !!! 오팬무 본다 !!!" +
                "good night"
    }

}
