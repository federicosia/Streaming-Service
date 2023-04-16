package com.tesistio.microservices.data;

public record ReviewV3(long id, String title, String content, int rating, int likes, long detailsId) {
}
