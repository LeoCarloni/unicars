package edu.unifacef.unicars.controllers;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
public class PageResponse<T> {

    private List<T> content;
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Long totalElements;

    public PageResponse(final Page<T> page){
        this.content = page.getContent();
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
}