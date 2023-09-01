package fi400.glass_bottle.commons.utils

/**
 * http request & response 처리용 유틸리티
 * Builder 패턴으로 작성됨
 * @author jinyong
 */

/**
 * @sample
 *      return Message.Builder()
 *             .status(HttpStatus.OK.value())
 *             .message("Success")
 *             .data(item)
 *             .build()
 */
class Res(
    val status: Int,
    val message: String,
    val data: Any? = null
) {
    class Builder {
        private var status: Int = 0
        private var message: String = ""
        private var data: Any? = null

        fun status(status: Int) = apply { this.status = status }
        fun message(message: String) = apply { this.message = message }
        fun data(data: Any?) = apply { this.data = data }

        fun build() = Res(status, message, data)
    }
}