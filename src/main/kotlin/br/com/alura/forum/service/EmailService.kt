package br.com.alura.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {
    fun notify(email: String) {
        val message = SimpleMailMessage()
        message.subject = "[Alura] Resposta recebida"
        message.text = "Olá, o seu tópico foi respondido. Vamos lá conferir?"
        message.from = "alura@email.com"
        message.setTo(email)
        javaMailSender.send(message)
    }
}