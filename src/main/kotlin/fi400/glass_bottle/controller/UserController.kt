package fi400.glass_bottle.controller

import fi400.glass_bottle.commons.utils.ResData
import fi400.glass_bottle.commons.utils.logger
import fi400.glass_bottle.model.entity.Letter
import fi400.glass_bottle.model.entity.User
import fi400.glass_bottle.repository.UserRepository
import fi400.glass_bottle.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
class UserController @Autowired constructor(private val userService: UserService) {

    private val log = this.logger()

    /**
     * userId로 해당 유저의 편지 조회
     * cookie 미정
     * @param userId 유저 아이디
     * @return response
     */
    @GetMapping
    fun getLetterById(@RequestParam userId: Long, @RequestParam letterStatus: String = ""): ResponseEntity<ResData> {
        val response = ResData.Builder()
        val letterList: List<Letter>?

        try {
            letterList = userService.getLettersByUserId(userId, letterStatus)
            response.data(letterList).status(200).message("Successfully retrieved letter")
        } catch (e: Exception) {
            log.error("$e")
            response.status(404).message("An error occurred: ${e.message}")
        }

        return ResponseEntity.ok(response.build())
    }
    @PostMapping("/saveUser")
    fun save(@RequestParam id: Long) {
    }
}