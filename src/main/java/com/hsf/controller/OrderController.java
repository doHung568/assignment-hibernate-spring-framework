package com.hsf.controller;

import com.hsf.dto.OrderDTO;
import com.hsf.service.CustomerService;
import com.hsf.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;

    public OrderController(OrderService orderService,  CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @GetMapping("/form")
    public String viewOrderForm() {
        return "order/order-form";
    }

    @PostMapping("/form")
    // @ModelAttribute get data from form -> bind to orderDTO
    public String submitOrderForm(HttpServletRequest request, @Valid @ModelAttribute OrderDTO dto,
                                  BindingResult bindingResult) {

        // log errors
        if (bindingResult.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                message.append(error.getDefaultMessage()).append("\n");
            }
            request.setAttribute("message", message);
            return "order/order-form";
        }

        boolean existEmail = customerService.checkExistByEmail(dto.getEmail());
        boolean existPhone = customerService.checkExistByPhoneNumber(dto.getPhoneNumber());
        // email and phone don't exist -> save new customer and allow to create new order
        if(!existEmail && !existPhone){
            customerService.saveNewCustomer(dto);
        } else if((existEmail && !existPhone) || (!existEmail && existPhone)){
            request.setAttribute("message", "Email and phone not mapping");
            return "order/order-form";
        }
        // save order
        String messageCreate = orderService.createOrder(dto);
        if(messageCreate != null){
            request.setAttribute("message", messageCreate);
            return "order/order-form";
        }
        request.setAttribute("status", "success");
        request.setAttribute("message", "Create order successfully");
        return "order/order-form";
    }

    @GetMapping("/list")
    public String viewOrderList(HttpServletRequest request) {
        List<OrderDTO> orderDTOs = orderService.getAll();
        request.setAttribute("orderDTOs", orderDTOs);
        return "order/order-list";
    }

    @GetMapping("/details")
    public String viewOrderDetails(HttpServletRequest request, @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = orderService.getOrderDetails(orderId);
        request.setAttribute("orderDTO", orderDTO);
        return "order/order-details";
    }
}
