package fi400.glass_bottle.controller

import fi400.glass_bottle.commons.utils.ResData
import fi400.glass_bottle.commons.utils.logger
import fi400.glass_bottle.model.entity.Letter
import fi400.glass_bottle.model.entity.User
import fi400.glass_bottle.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * 유저 관련 API 호출하는 컨트롤러
 */
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
    fun getLetterListByUserId(@RequestParam userId: Long, @RequestParam letterStatus: String = ""): ResponseEntity<Any> {
//        val token = HttpCookie .get decode
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

    /**
     * 객체를 받아서 생성하는 API
     * @param User 생성할 유저 객체
     * @return response
     */
    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<Any> {
        val response = ResData.Builder()

        try {
            userService.updateUser(user)
            response.data(user).status(200).message("Successfully create user")
        } catch (e: Exception) {
            log.error("$e")
            response.status(500).message("Save user fail: ${e.message}")
        }

        return ResponseEntity.ok(response.build())
    }

    /**
     * user 객체를 받아서 저장하는 API
     * @param user 저장 하고 싶은 유저 객체
     * @return response
     */
    @PutMapping
    fun updateUser(@RequestBody user: User): ResponseEntity<Any> {
        val response = ResData.Builder()

        try {
            userService.updateUser(user)
            response.data(user).status(200).message("Successfully update user")
        } catch (e: Exception) {
            log.error("$e")
            response.status(500).message("Save user fail: ${e.message}")
        }

        return ResponseEntity.ok(response)
    }

    /**
     * user id를 받아서 삭제
     * @param user 저장 하고 싶은 유저 객체
     * @return response
     */
    @DeleteMapping
    fun deleteUser(@RequestBody user: User): ResponseEntity<Any> {
        val response = ResData.Builder()
        user.isActivated = 'N'

        try {
            userService.updateUser(user)
            response.data(user).status(200).message("Successfully delete user")
        } catch (e: Exception) {
            log.error("$e")
            response.status(500).message("Save user fail: ${e.message}")
        }

        return ResponseEntity.ok(response)
    }


}