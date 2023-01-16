package com.step1.project.DTO;

import lombok.Data;

@Data
public class SortRequestDTO {
    String field;
    String sortDirection;
    long page;

}
