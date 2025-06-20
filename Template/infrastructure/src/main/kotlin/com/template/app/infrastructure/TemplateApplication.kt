package com.template.app.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TemplateApplication

fun main(args: Array<String>) {
	runApplication<TemplateApplication>(*args)
}
