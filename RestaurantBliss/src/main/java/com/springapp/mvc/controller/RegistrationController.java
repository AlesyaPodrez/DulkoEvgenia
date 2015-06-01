package com.springapp.mvc.controller;

import com.springapp.mvc.domain.*;
import com.springapp.mvc.logic.CookieWork;
import com.springapp.mvc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Евгения on 08.05.2015.
 */
@Controller
public class RegistrationController {
    private UserRepository userRepository;
    private ClientRepository clientRepository;
    private OrderDishRepository orderDishRepository;
    private MyOrderRepository myOrderRepository;
    private MyTableRepository myTableRepository;
    private DishRepository dishRepository;
    private DishTypeRepository dishTypeRepository;
    private ReservationRepository reservationRepository;
    private AuthorityRepository authorityRepository;


    @Autowired
    public RegistrationController(UserRepository userRepository, ClientRepository clientRepository, OrderDishRepository orderDishRepository, MyOrderRepository myOrderRepository, DishRepository dishRepository, DishTypeRepository dishTypeRepository, ReservationRepository reservationRepository, MyTableRepository myTableRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.orderDishRepository = orderDishRepository;
        this.myOrderRepository = myOrderRepository;
        this.myTableRepository = myTableRepository;
        this.dishRepository = dishRepository;
        this.dishTypeRepository = dishTypeRepository;
        this.reservationRepository = reservationRepository;
        this.authorityRepository = authorityRepository;
    }

    @RequestMapping(value = "toRegistration", method = RequestMethod.GET)
    public String toRegistration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("client", new Client());
        return "registration";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String registration(Model model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("client") Client client, @ModelAttribute("user") User user) {
        User u = this.userRepository.findUserByLogin(user.getULogin());
        if(u!=null) {
            String error="Пользователь с таким именем уже существует";
            model.addAttribute("mess", error);
            return "registration";
        }
        else {
            user.setCId(client);
            Authority authority = new Authority("user", user);
            this.clientRepository.addClient(client);
            this.userRepository.addUser(user);
        this.authorityRepository.addAuthority(authority);
            model.addAttribute("mess", 1);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "adminMenu", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String adminMenu(Model model) {
        return "admin-menu";
    }
}
