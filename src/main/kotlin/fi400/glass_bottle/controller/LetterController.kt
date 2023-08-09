package fi400.glass_bottle.controller

import fi400.glass_bottle.model.entity.Letter
import fi400.glass_bottle.model.entity.User
import fi400.glass_bottle.repository.LetterRepository
import fi400.glass_bottle.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.optionals.getOrNull

/**
 * 편지 관련 API 호출하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/letter")
class LetterController {
    @Autowired
    private lateinit var userRepository: UserRepository
    private lateinit var LetterRepository: LetterRepository

    @GetMapping("/myLetter")
    fun getAllLetter(@RequestParam id: Long): ResponseEntity<User> {
        return ResponseEntity.ok(userRepository.findById(id).getOrNull())
    }
}