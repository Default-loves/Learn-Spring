package com.junyi.exception.demo.controller;

import com.junyi.exception.demo.controller.exception.FormValidationException;
import com.junyi.exception.demo.controller.request.NewCoffeeRequest;
import com.junyi.exception.demo.model.Coffee;
import com.junyi.exception.demo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/coffee")
@Slf4j
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;


    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee addCoffee(@Valid NewCoffeeRequest newCoffee, BindingResult result) {
        if (result.hasErrors()) {
            log.warn("Bind Error:{}", result);
            throw new FormValidationException(result);
        }
        return coffeeService.addCoffee(newCoffee.getName(), newCoffee.getPrice());
    }

//    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public Coffee addCoffeeWithoutBindingResult(@Valid NewCoffeeRequest newCoffeeRequest) {
//        return coffeeService.addCoffee(newCoffeeRequest.getName(), newCoffeeRequest.getPrice());
//    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee addJsonCoffeeWithoutBingdingResult(@Valid @RequestBody NewCoffeeRequest newRequest, BindingResult result) {
        if (result.hasErrors()) {
            log.warn("Bind Error:{}", result);
            throw new ValidationException(result.toString());
        }
        return coffeeService.addCoffee(newRequest.getName(), newRequest.getPrice());
    }

    @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public List<Coffee> batchAddCoffee(@RequestParam MultipartFile file) {
        List<Coffee> coffees = new ArrayList<>();
        if (!file.isEmpty()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                String str;
                while ((str = reader.readLine())!=null) {
                    String[] arr = StringUtils.split(str, " ");
                    if (arr != null & arr.length == 2) {
                        coffees.add(coffeeService.addCoffee(arr[0], Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(arr[1]))));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(reader);
            }
        }
        return coffees;
    }


    @GetMapping(path = "/", params = "!name")
    @ResponseBody
    public List<Coffee> getAllCoffee() {
        return coffeeService.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
            //produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Coffee> getById(@PathVariable Long id) {
        Coffee coffee = coffeeService.getCoffee(id);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
                .body(coffee);
    }

    @GetMapping(path = "/", params = "name")
    @ResponseBody
    public Coffee getByName(@RequestParam String name) {
        return  coffeeService.getCoffee(name);
    }
}
