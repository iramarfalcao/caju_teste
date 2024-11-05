package com.caju.utils

import java.time.Clock
import java.time.LocalDateTime
import java.time.ZoneId

object DateTimeUtils {

    private val AMERICA_SP: ZoneId = ZoneId.of("America/Sao_Paulo")

    fun buildDateLocal(): LocalDateTime {
        return LocalDateTime.now(Clock.system(AMERICA_SP))
    }
}
