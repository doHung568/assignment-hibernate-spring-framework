package com.hsf.controller;

import com.hsf.dto.OrderDTO;
import com.hsf.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/form")
    public String viewOrderForm() {
        return "order/order-form";
    }

    @PostMapping("/form")
    // @ModelAttribute get data from form -> bind to orderDTO
    public String submitOrderForm(HttpServletRequest request, @Valid @ModelAttribute OrderDTO dto,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                message.append(error.getDefaultMessage()).append("\n");
            }
            request.setAttribute("message", message);
            return "order/order-form";
        }

        try {
            OrderDTO orderDTO = orderService.createOrder(dto);
            redirectAttributes.addFlashAttribute("message", "Create order successfully");
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            return "order/order-form";
        }
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
