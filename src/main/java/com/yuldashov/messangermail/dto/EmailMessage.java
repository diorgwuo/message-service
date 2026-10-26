package com.yuldashov.messangermail.dto;

public record EmailMessage (
    String address,
    String title,
    String body) {}
