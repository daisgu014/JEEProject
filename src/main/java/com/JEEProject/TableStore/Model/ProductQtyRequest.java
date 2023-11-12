package com.JEEProject.TableStore.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductQtyRequest {
    private Integer id;
    private Integer qty;
}
