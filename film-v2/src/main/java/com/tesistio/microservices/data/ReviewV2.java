package com.tesistio.microservices.data;

public record ReviewV2(long id, String title, String content, int rating, long detailsId) {
}
