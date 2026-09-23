package com.ms.email.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Classe a qual vai guardar a fila e toda vez o broker vai acessar daqui

    @Value("${broker.queue.email.name}")
    private String queue;
    // Aqui passamos a fila que vai ser criada

    @Bean
    public Queue queue() {
        return new Queue(queue, true);
    }
    // durable: permite que se o servidor cair a fila é conservada

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
    // A mensagem de Json vai ser convertido para o tipo em java
}
