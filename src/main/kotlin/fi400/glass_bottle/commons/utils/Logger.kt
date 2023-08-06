package fi400.glass_bottle.commons.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Logging 유틸리티
 * 팩토리 메소드 패턴으로 작성됨
 * @author jinyong
 */

/**
 * @sample
 *     private val log = logger()
 *     fun doSomething() {
 *         log.debug("Doing something...")
 *     }
 */
inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}