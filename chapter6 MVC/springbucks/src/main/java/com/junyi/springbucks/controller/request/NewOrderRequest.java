package com.junyi.springbucks.controller.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class NewOrderRequest {
    private String customer;
    private List<String> items;
}
