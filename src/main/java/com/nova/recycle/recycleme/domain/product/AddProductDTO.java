package com.nova.recycle.recycleme.domain.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddProductDTO {
    private Long categoryId;
    private Long timeId;
}
