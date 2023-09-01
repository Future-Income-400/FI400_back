package fi400.glass_bottle.controller

import fi400.glass_bottle.commons.utils.Res
import fi400.glass_bottle.commons.utils.logger
import fi400.glass_bottle.model.entity.Letter
import fi400.glass_bottle.repository.LetterRepository
import fi400.glass_bottle.service.LetterService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpCookie
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * 편지 관련 API 호출하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/letter")
class LetterController(private val letterService: LetterService) {

    private val log = this.logger()


    /**
     * letterID로 편지 하나 조회
     * 현재는 cookie를 사용할지 어떤 걸 사용할지 모르기 때문에
     * 일단은 QueryString으로 값을 받아옴.
     * @param letterId 편지의 id
     * @return response
     */
    @GetMapping
    fun getLetterById(@RequestParam letterId: Long): ResponseEntity<Res> {
//        token = HttpCookie get().decode
        val response = Res.Builder()
        val letter: Letter?

        try {
            letter = letterService.getLetterById(letterId)
            response.data(letter).status(200).message("Successfully retrieved letter")
        } catch (e: Exception) {
            log.error("$e")
            // 이거 null을 보내는 게 맞나... 아닌가 모르겠네
            response.status(404).message("An error occurred: ${e.message}")
        }

        return ResponseEntity.ok(response.build())
    }

    /**
     * letter 객체를 받아서 저장하는 api
     * @param letter 저장하고픈 편지 객체
     * @return response
     */
    @PostMapping
    fun draftLetter(@RequestBody letter: Letter): ResponseEntity<Res> {

        val response = Res.Builder()

        try {
            letterService.draftLetter(letter)
            response.data(letter).status(200).message("Successfully draft letter")
        } catch (e: Exception) {
            log.error("$e")
            response.status(500).message("Save letter fail: ${e.message}")
        }

        return ResponseEntity.ok(response.build())
    }

    /**
     * letter 객체를 받아서 '수정'하는 api
     * JPA Dirty Check 를 공부할 것!
     * 그리고 위에 코드랑 아래 코드 어느 게 더 이쁜가?
     * 난 위 왜 why? reponseEntity 두 번써야함.
     * 근데 만약 resonse builder안쓰면 이거 개깔끔하게 할 수 있을듯?
     * 졸려 ㅅㅂ 왜 12시냐
     * 아니시발 IDE 이거 왜이래 마우스 가끔 개뻑남
     * @param letter 수정하고픈 편지 객체
     * @return response
     */
    @PutMapping
    fun reviseLetter(@RequestBody letter: Letter): ResponseEntity<Res> {

        val response = Res.Builder()

        return try {
            letterService.draftLetter(letter) // JPA Dirty Check
            response.data(letter).status(200).message("Successfully modify letter")
            ResponseEntity.ok(response.build())
        } catch (e: Exception) {
            log.error("$e")
            response.status(500).message("Update letter fail: ${e.message}")
            ResponseEntity.ok(response.build())
        }
    }

    /**
     * Letter 객체의 isActivated를 'N'으로 바꿔서 저장
     * !- 직접 데이터베이스를 삭제하는 것은 위험한 행동 -!
     * @param letter 삭제하고픈 편지 객체
     * @return response
     */
    @DeleteMapping
    fun removeLetter(@RequestBody letter: Letter): ResponseEntity<Res> {

        val response = Res.Builder()
        letter.isActivated = 'N'

        try {
            letterService.draftLetter(letter) // JPA Dirty Check
            response.data(letter).status(200).message("Successfully discard letter")
        } catch (e: Exception) {
            log.error("$e")
            response.status(500).message("Delete letter fail: ${e.message}")
        }

        return ResponseEntity.ok(response.build())
    }

}
