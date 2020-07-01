package com.junyi.springbucks.customer.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@Setter
public class NewOrderRequest {
    private String name;
    private List<String> items;
}
