package com.commerce.server.util.mail.dto;

public record EmailPayload(
        String to,
        String subject,
        String text
) {
}
