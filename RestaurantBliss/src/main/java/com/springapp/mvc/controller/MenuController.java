package com.springapp.mvc.controller;

import com.springapp.mvc.domain.*;
import com.springapp.mvc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Евгения on 08.05.2015.
 */
@Controller
public class MenuController {


    private DishRepository dishRepository;
    private DishTypeRepository dishTypeRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public MenuController(ReservationRepository reservationRepository, DishRepository dishRepository, DishTypeRepository dishTypeRepository, ClientRepository clientRepository, MyTableRepository myTableRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.dishRepository = dishRepository;
        this.dishTypeRepository = dishTypeRepository;
    }

    @RequestMapping(value = "getDishCollection/{id}", method = RequestMethod.GET)
    public String getDishCollection(@PathVariable Integer id, Model model) {
        List<DishType> dishTypes = this.dishTypeRepository.listAll();
        System.out.println(this.dishTypeRepository.findDishType(id).getDtName());
        DishType dt = this.dishTypeRepository.findDishType(id);
        List<Dish> dishes = (List<Dish>) dt.getDishCollection();
        model.addAttribute("dishTypes", dishTypes);
        model.addAttribute("dishes", dishes);
        return "menu";
    }

    @RequestMapping(value = "findDish", method = RequestMethod.POST)
    public String findDish(@RequestParam String dishName, Model model) {
        System.out.println(dishName);
        List<Reservation> reservations = this.reservationRepository.listAll();
        List<Dish> dishes = this.dishRepository.findDishByName(dishName);
        List<DishType> dishTypes = this.dishTypeRepository.listAll();
        model.addAttribute("reservations", reservations);
        model.addAttribute("dishTypes", dishTypes);
        model.addAttribute("dishes", dishes);
        return "menu";
    }
}
