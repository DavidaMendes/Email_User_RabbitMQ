package com.ms.email.domain.consumer;

import com.ms.email.domain.dto.EmailRequestDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    // Método ouvinte que vai consumir as mensagens da fila

    // Como parametro vamos passar um dto que vai ser o dto para o service de fato
    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRequestDTO request){
        System.out.println(request.emailTo());
        // Aqui vai chamar o service e executar a action
    }
}
