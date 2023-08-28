package fi400.glass_bottle.controller

import fi400.glass_bottle.model.entity.Letter
import fi400.glass_bottle.service.LetterService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * 편지 관련 API 호출하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/letter")
class LetterController {
    val letterService = LetterService()

    @GetMapping("/my-letter")
    fun getAllLetter(@RequestParam id: Long): ResponseEntity<Letter> {
        return letterService.getLetter(id)
    }

//    @PostMapping
//    fun saveLetter(@RequestParam letter: Letter): ResponseEntity<Letter> {
//        return ResponseEntity.ok(letterRepository.save(letter))
//    }
//
//    @PostMapping()
//    fun getTest(): List<User> {
//        return userRepository.findAll()
//    }
}