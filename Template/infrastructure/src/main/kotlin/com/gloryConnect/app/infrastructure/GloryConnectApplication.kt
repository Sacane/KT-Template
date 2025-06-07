package com.gloryConnect.app.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GloryConnectApplication

fun main(args: Array<String>) {
	runApplication<GloryConnectApplication>(*args)
}
