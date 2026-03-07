package com.arjunnigam.productservice.controllers;


import org.springframework.web.bind.annotation.*;


@RestController // This annotation indicates that this class is a Spring MVC Controller
// Rest Controller means it is going to host REST HTTP   APIs (Web APIs)
@RequestMapping("/sample") //endpoint = /sample
public class  SampleController {
/*
amazon.in/orders => OrderController
If any request comes to /orders pass it to OrderController

amazon.in/payments => PaymentController
If any request comes to /payments , pass it to PaymentController

 */

    //localhost:8080/sample/hello -> SampleController.sayHello()
    @GetMapping("/hello/{name}/{times}")
    public String sayHello(@PathVariable("name") String name,
                           @PathVariable("times") int x)
    {
        String s = "";
        for (int i = 0; i <= x; i++)
        {
            s += "Hello " + name + "<br>"; // <br> is new line in HTML
        }
        return s;
    }

    //localhost:8080/sample/hi -> SampleController.sayHi()
    @GetMapping("/hi")
    public String sayHi()
    {
        return "Hi";
    }






}
