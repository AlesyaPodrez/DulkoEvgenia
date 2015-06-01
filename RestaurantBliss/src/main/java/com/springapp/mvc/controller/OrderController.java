package com.springapp.mvc.controller;

import com.springapp.mvc.domain.*;
import com.springapp.mvc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгения on 20.05.2015.
 */

@Controller
public class OrderController {

    private DishRepository dishRepository;
    private DishTypeRepository dishTypeRepository;
    private ClientRepository clientRepository;
    private MyOrderRepository myOrderRepository;
    private OrderDishRepository orderDishRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderController(DishRepository dishRepository, DishTypeRepository dishTypeRepository, ClientRepository clientRepository, MyOrderRepository myOrderRepository, OrderDishRepository orderDishRepository, UserRepository userRepository) {
        this.dishRepository = dishRepository;
        this.dishTypeRepository = dishTypeRepository;
        this.clientRepository = clientRepository;
        this.myOrderRepository = myOrderRepository;
        this.orderDishRepository=orderDishRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "addDishToOrder", method = RequestMethod.POST)
    public String addDishToOrder(HttpServletRequest request, Model model, @RequestParam("orderDish") String od) {

        if(od!="") {
            StringBuilder dishes = new StringBuilder(od);
            dishes.append('/').append(request.getParameter("dishId"));
            od=dishes.toString();
        } else {
            od=request.getParameter("dishId");
        }
        List<DishType> dishTypes = this.dishTypeRepository.listAll();
        List<Dish> dishes = this.dishRepository.listAll();
        model.addAttribute("dishTypes", dishTypes);
        model.addAttribute("dishes", dishes);
        model.addAttribute("orderDish", od);
        return "order";
    }

    @RequestMapping(value = "orderConfirmation", method=RequestMethod.POST)
    public String orderConfirmation(@RequestParam("orderDish") String od, Model model){
        String[] dishes;
        Dish dish;
        List<Dish> orderDishes = new ArrayList<Dish>();
        int[] dishesId;
        if (od!="") {
            dishes = od.split("/");
            dishesId = new int[dishes.length];
            for (int i = 0; i < dishes.length; i++) {
                dishesId[i] = Integer.valueOf(dishes[i]);
            }
            for (int i = 0; i < dishesId.length; i++) {
                dish = dishRepository.findDish(dishesId[i]);
                orderDishes.add(dish);
            }
            int cost = 0;
            for(Dish o: orderDishes) {
                cost += o.getDPrice();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(new java.util.Date());
            model.addAttribute("date", date);
            model.addAttribute("cost", cost);
            model.addAttribute("orderDish", od);
            model.addAttribute("dishes", orderDishes);
            model.addAttribute("client", new Client());
        }
        else {
            model.addAttribute("mess", "Вы не добавили ни одного блюда в заказ");
        }
        return "order-confirmation";
    }

    @RequestMapping(value = "getCoast", method=RequestMethod.POST)
    public String getCoast(@RequestParam("orderDish") String od, Model model, HttpServletRequest request){
        String[] dishes;
        Dish dish;
        List<Dish> orderDishes = new ArrayList<Dish>();
        int[] dishesId;
        if (od!="") {
            dishes = od.split("/");
            dishesId = new int[dishes.length];
            for (int i = 0; i < dishes.length; i++) {
                dishesId[i] = Integer.valueOf(dishes[i]);
            }
            for (int i = 0; i < dishesId.length; i++) {
                dish = dishRepository.findDish(dishesId[i]);
                orderDishes.add(dish);
            }
        }
        int cost = 0;
        for(Dish o: orderDishes) {
            cost += o.getDPrice()*Integer.valueOf(request.getParameter(o.getId().toString()));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new java.util.Date());
        model.addAttribute("date", date);
        model.addAttribute("cost", cost);
        model.addAttribute("orderDish", od);
        model.addAttribute("dishes", orderDishes);
        model.addAttribute("client", new Client());
        return "order-confirmation";
    }

    @RequestMapping(value = "deleteOrderDish", method = RequestMethod.GET)
    public String deleteClient(Model model, @RequestParam("orderDish") String od, @RequestParam("dishId") int id) {
        String[] dishes;
        Dish dish;
        List<Dish> orderDishes = new ArrayList<Dish>();
        int[] dishesId;
        if (od!="") {
            dishes = od.split("/");
            dishesId = new int[dishes.length];
            for (int i = 0; i < dishes.length; i++) {
                dishesId[i] = Integer.valueOf(dishes[i]);
            }
            for (int i = 0; i < dishesId.length; i++) {
                dish = dishRepository.findDish(dishesId[i]);
                orderDishes.add(dish);
            }
        }
        StringBuilder dishesStr =new StringBuilder();
        if (orderDishes != null) {
            for (Dish dish1 : orderDishes) {
                if (dish1.getId()== id) {
                    orderDishes.remove(dish1);
                    break;
                }
            }
        }
        if (orderDishes != null) {
            for (Dish dish1 : orderDishes) {
                 dishesStr.append(dish1.getId().toString()).append('/');
            }
        }
        int cost = 0;
        for(Dish o: orderDishes) {
                cost += o.getDPrice();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new java.util.Date());
        model.addAttribute("date", date);
        model.addAttribute("cost", cost);
        model.addAttribute("dishes", orderDishes);
        model.addAttribute("client", new Client());
        model.addAttribute("orderDish", dishesStr.substring(0, dishesStr.length() - 1));
        return "order-confirmation";
    }

    @RequestMapping(value = "order", method = RequestMethod.GET)
    public String toOrder(Model model) {
        List<DishType> dishTypes = this.dishTypeRepository.listAll();
        List<Dish> dishes = this.dishRepository.listAll();
        model.addAttribute("dishTypes", dishTypes);
        model.addAttribute("dishes", dishes);
        String orderDish = "";
        model.addAttribute("orderDish", orderDish);
        return "order";
    }

    @RequestMapping(value = "addOrder", method = RequestMethod.POST)
    public String addOrder(@ModelAttribute("client") Client client, @RequestParam String date, @RequestParam String time, @RequestParam("orderDish") String od, HttpServletRequest request) {
        String[] dishes;
        Dish dish;
        List<Dish> orderDishes = new ArrayList<Dish>();
        int[] dishesId;
        if (od!="") {
            dishes = od.split("/");
            dishesId = new int[dishes.length];
            for (int i = 0; i < dishes.length; i++) {
                dishesId[i] = Integer.valueOf(dishes[i]);
            }
            for (int i = 0; i < dishesId.length; i++) {
                dish = dishRepository.findDish(dishesId[i]);
                orderDishes.add(dish);
            }
        }
        this.clientRepository.addClient(client);
        MyOrder order = new MyOrder(date, time, client);
        this.myOrderRepository.addOrder(order);
        List<MyOrder> orders= new ArrayList<MyOrder>(this.myOrderRepository.listAll());
        order=orders.get(orders.size()-1);
        OrderDish orderDish;
        for(Dish dish1: orderDishes) {
            orderDish=new OrderDish(1, order, dish1);
            this.orderDishRepository.addOrderDish(orderDish);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "addOrderAuthenticated", method = RequestMethod.POST)
    public String addOrderAuthenticated(@RequestParam String date, @RequestParam String time, @RequestParam("orderDish") String od, HttpServletRequest request) {
        String[] dishes;
        Dish dish;
        List<Dish> orderDishes = new ArrayList<Dish>();
        int[] dishesId;
        if (od!="") {
            dishes = od.split("/");
            dishesId = new int[dishes.length];
            for (int i = 0; i < dishes.length; i++) {
                dishesId[i] = Integer.valueOf(dishes[i]);
            }
            for (int i = 0; i < dishesId.length; i++) {
                dish = dishRepository.findDish(dishesId[i]);
                orderDishes.add(dish);
            }
        }
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findUserByLogin(userName);
        MyOrder order = new MyOrder(date, time, user.getCId());
        this.myOrderRepository.addOrder(order);
        List<MyOrder> orders= new ArrayList<MyOrder>(this.myOrderRepository.listAll());
        order=orders.get(orders.size()-1);
        OrderDish orderDish;
        for(Dish dish1: orderDishes) {
            orderDish=new OrderDish(1, order, dish1);
            this.orderDishRepository.addOrderDish(orderDish);
        }
        return "redirect:/";
    }



}
