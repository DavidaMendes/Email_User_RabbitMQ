package com.ms.email.domain.dto;

import java.util.UUID;

public record EmailRequestDTO(UUID userId,
                             String emailTo,
                             String subject,
                             String text) {
}
