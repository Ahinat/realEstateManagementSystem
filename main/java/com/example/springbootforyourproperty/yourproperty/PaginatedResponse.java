package com.example.springbootforyourproperty.yourproperty;

import java.util.List;

public class PaginatedResponse<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int number;

    // Constructors
    public PaginatedResponse(List<T> content, int totalPages, long totalElements, int number) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.number = number;
    }

    // Getters
    public List<T> getContent() {
        return content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getNumber() {
        return number;
    }
}
