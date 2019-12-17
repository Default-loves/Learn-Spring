package com.junyi.thymoleaf.view.demo.controller;

import com.junyi.thymoleaf.view.demo.controller.request.NewOrderRequest;
import com.junyi.thymoleaf.view.demo.model.Coffee;
import com.junyi.thymoleaf.view.demo.model.CoffeeOrder;
import com.junyi.thymoleaf.view.demo.service.CoffeeOrderService;
import com.junyi.thymoleaf.view.demo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService orderService;

    @GetMapping("/{id}")
    @ResponseBody
    public CoffeeOrder getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new order:{}", newOrder);
        Coffee[] coffees = coffeeService.findCoffeeByName(newOrder.getItems()).toArray(new Coffee[]{});
        return orderService.createOrder(newOrder.getCustomer(), coffees);
    }

    @ModelAttribute
    public List<Coffee> coffeeList() {
        return coffeeService.findAll();
    }

    @GetMapping("/")
    public ModelAndView showCreateForm() {
        return new ModelAndView("create-order-form");
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createOrder(@Valid NewOrderRequest newOrder, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            log.info("Warning : {}", result);
            map.addAttribute("message", result.toString());
            return "create-order-form";
        }
        Coffee[] coffees = coffeeService.findCoffeeByName(newOrder.getItems()).toArray(new Coffee[]{});
        CoffeeOrder order = orderService.createOrder(newOrder.getCustomer(), coffees);
        return "redirect:/order/" + order.getId();
    }
}
