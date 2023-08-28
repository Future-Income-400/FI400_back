package fi400.glass_bottle.controller

import fi400.glass_bottle.model.entity.User
import fi400.glass_bottle.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
class UserController @Autowired constructor(val userRepository: UserRepository) {

    @PostMapping("/saveUser")
    fun save(@RequestParam id: Long) {
    }
}