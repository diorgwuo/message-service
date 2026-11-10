package com.yuldashov.messangermail.dto;

public record EmailMessage (
    String address,
    EmailType type,
    String body) {}
