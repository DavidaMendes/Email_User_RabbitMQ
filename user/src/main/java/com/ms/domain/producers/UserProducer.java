package com.ms.domain.producers;

import com.ms.domain.dto.EmailDTO;
import com.ms.domain.model.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    // Injeção de RabbitTemplate
    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    // Captura a fila para produzir a mensagem nela
    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    // Method que vai publicar a mensagem na fila
    public void publishMessageEmail(UserModel userModel) {
        var emailDto = new EmailDTO();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");
        emailDto.setText(userModel.getName() + ", seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto); // Aqui nesse method passamos
        // 1. Exchange -> Default (string vazia)
        // 2. routingKey -> broker.queue.email.name
        // 3. Corpo da mensagem -> EmailDTO
    }
}
