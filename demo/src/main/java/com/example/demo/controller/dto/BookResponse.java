package com.example.demo.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class BookResponse {
    private Integer id;
    private String author;
    private String isbn;
}
