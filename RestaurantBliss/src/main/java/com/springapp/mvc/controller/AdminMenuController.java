package com.springapp.mvc.controller;

import com.dropbox.core.DbxException;
import com.springapp.mvc.domain.*;
import com.springapp.mvc.repository.*;
import com.springapp.mvc.service.Dropbox;
import com.springapp.mvc.validation.DishTypeValidator;
import com.springapp.mvc.validation.DishValidator;
import com.springapp.mvc.validation.TableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Евгения on 24.05.2015.
 */
@Controller
public class AdminMenuController {

    private UserRepository userRepository;
    private ClientRepository clientRepository;
    private MyOrderRepository myOrderRepository;
    private MyTableRepository myTableRepository;
    private DishRepository dishRepository;
    private DishTypeRepository dishTypeRepository;
    private TableValidator tableValidator;
    private DishTypeValidator dishTypeValidator;


    @Autowired
    public AdminMenuController(UserRepository userRepository, ClientRepository clientRepository,
                               MyOrderRepository myOrderRepository, DishTypeValidator dishTypeValidator,
                               DishRepository dishRepository, DishTypeRepository dishTypeRepository,
                               MyTableRepository myTableRepository, TableValidator tableValidator) {

        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.myOrderRepository = myOrderRepository;
        this.myTableRepository = myTableRepository;
        this.dishRepository = dishRepository;
        this.dishTypeRepository = dishTypeRepository;
        this.tableValidator = tableValidator;
        this.dishTypeValidator = dishTypeValidator;
    }

    @RequestMapping(value = "getTables", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String getTables(Model model) {
        List<MyTable> tables = this.myTableRepository.listAll();
        model.addAttribute("tables", tables);
        return "admin-menu";
    }

    @RequestMapping(value = "myOrder", method = RequestMethod.GET)
    public String myOrder(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findUserByLogin(userName);
        List<MyOrder> orders= this.myOrderRepository.findOrderByClient(user.getCId().getId());
        if(orders.size()>0) {
            int[] cost = new int[orders.size()];
            for (int i = 0; i < orders.size(); i++) {
                cost[i] = 0;
                List<OrderDish> ods = (List<OrderDish>) orders.get(i).getOrderDishCollection();
                for (OrderDish od : ods) {
                    cost[i] += od.getONumOfDish() * od.getDish().getDPrice();
                }
            }
            model.addAttribute("cost", cost);
            model.addAttribute("orders", orders);
        }
        else model.addAttribute("mess", "Вы еще ничего не заказали");
        return "myoffice";
    }

    @RequestMapping(value = "deleteOrder/{id}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable Integer id) {
        this.myOrderRepository.removeOrder(id);
        return "redirect:/myOrder";
    }

    @RequestMapping(value = "addTable", method = RequestMethod.GET)
    public String addTable(Model model) {
        model.addAttribute("table", new MyTable());
        return "add";
    }

    @RequestMapping(value = "addTable", method = RequestMethod.POST)
    public String addTable(@ModelAttribute("table") MyTable table, BindingResult bindingResult) {
        this.tableValidator.validate(table, bindingResult);
        if(bindingResult.hasErrors()){
            return "add";
        }
        this.myTableRepository.addTable(table);
        return "redirect:/getTables";
    }

    @RequestMapping(value = "deleteTable/{id}", method = RequestMethod.GET)
    public String deleteTable(@PathVariable Integer id, Model model) {
        try {
            this.myTableRepository.removeTable(id);
        }
        catch (QueryTimeoutException ex) {
            model.addAttribute("mess", "Вы не можете удалить этот стол");
            List<MyTable> tables = this.myTableRepository.listAll();
            model.addAttribute("tables", tables);
            return "admin-menu";
        }
        return "redirect:/getTables";
    }

    @RequestMapping(value = "editTable/{id}", method = RequestMethod.GET)
    public String editTable(@PathVariable Integer id, Model model) {
        model.addAttribute("table", this.myTableRepository.findTable(id));
        return "edit";
    }

    @RequestMapping(value = "/editTable", method = RequestMethod.POST)
    public String editTable(@ModelAttribute("table") MyTable table) {
        this.myTableRepository.updateTable(table);
        return "redirect:/getTables";
    }

    @RequestMapping(value = "getDishes", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String getDishes(Model model) {
        List<Dish> dishes = this.dishRepository.listAll();
        model.addAttribute("dishes", dishes);
        return "admin-menu";
    }

    @RequestMapping(value = "addDish", method = RequestMethod.GET)
    public String addDish(Model model) {
        model.addAttribute("dish", new Dish());
        List<DishType> type = this.dishTypeRepository.listAll();
        model.addAttribute("dishTypes", type);
        return "add";
    }

    @RequestMapping(value = "addDish", method = RequestMethod.POST)
    public String addDish(@RequestParam("dName") String dName, @RequestParam("dComposition") String dComposition, @RequestParam("dMass") String dMass, @RequestParam("dPrice") Integer dPrice, @RequestParam("dtId") Integer dtId, @RequestParam("dPicture")MultipartFile dPicture) throws IOException, DbxException {
        DishType dishType = this.dishTypeRepository.findDishType(dtId);
        Dropbox dropbox = new Dropbox();
        Dish dish = new Dish(dName, dComposition, dMass, dPrice, dishType, dropbox.UploadingFile(dPicture));
        this.dishRepository.addDish(dish);
        return "redirect:/getDishes";
    }

    @RequestMapping(value = "/deleteDish/{id}", method = RequestMethod.GET)
    public String deleteDish(@PathVariable Integer id, Model model) {
        try {
            this.dishRepository.removeDish(id);
        } catch (QueryTimeoutException ex) {
            model.addAttribute("mess", "Вы не можете удалить этот стол");
        }
        return "redirect:/getDishes";
    }

    @RequestMapping(value = "editDish/{id}", method = RequestMethod.GET)
    public String editDish(@PathVariable Integer id, Model model) {
        model.addAttribute("dish", this.dishRepository.findDish(id));
        List<DishType> type = this.dishTypeRepository.listAll();
        model.addAttribute("dishTypes", type);
        return "edit";
    }

    @RequestMapping(value = "/editDish", method = RequestMethod.POST)
    public String editDish(@RequestParam("id") Integer id,@RequestParam("dName") String dName, @RequestParam("dComposition") String dComposition, @RequestParam("dMass") String dMass, @RequestParam("dPrice") Integer dPrice, @RequestParam("dtId") Integer dtId, @RequestParam("dPicture")MultipartFile dPicture) throws IOException, DbxException {
        DishType dishType = this.dishTypeRepository.findDishType(dtId);
        Dropbox dropbox = new Dropbox();
        Dish dish = new Dish(id, dName, dComposition, dMass, dPrice, dishType, dropbox.UploadingFile(dPicture));
        this.dishRepository.updateDish(dish);
        return "redirect:/getDishes";
    }

    @RequestMapping(value = "getDishTypes", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String getDishTypes(Model model) {
        List<DishType> dishTypes = this.dishTypeRepository.listAll();
        model.addAttribute("dishTypes", dishTypes);
        return "admin-menu";
    }

    @RequestMapping(value = "addDishType", method = RequestMethod.GET)
    public String addDishType(Model model) {
        model.addAttribute("dishType", new DishType());
        return "add";
    }

    @RequestMapping(value = "addDishType", method = RequestMethod.POST)
    public String addDishType(@ModelAttribute("dishType") DishType dishType, BindingResult bindingResult) {
        this.dishTypeValidator.validate(dishType, bindingResult);
        if(bindingResult.hasErrors()){
            return "add";
        }
        this.dishTypeRepository.addDishType(dishType);
        return "redirect:/getDishTypes";
    }

    @RequestMapping(value = "deleteDishType/{id}", method = RequestMethod.GET)
    public String deleteDishType(@PathVariable Integer id, Model model) {
        try {
        this.dishTypeRepository.removeDishType(id);
    } catch (QueryTimeoutException ex) {
        model.addAttribute("mess", "Вы не можете удалить этот стол");
    }
        return "redirect:/getDishTypes";
    }

    @RequestMapping(value = "editDishType/{id}", method = RequestMethod.GET)
    public String editDishType(@PathVariable Integer id, Model model) {
        model.addAttribute("dishType", this.dishTypeRepository.findDishType(id));
        return "edit";
    }

    @RequestMapping(value = "/editDishType", method = RequestMethod.POST)
    public String editDishType(@ModelAttribute("dishType") DishType dishType) {
        this.dishTypeRepository.updateDishType(dishType);
        return "redirect:/getDishTypes";
    }

    @RequestMapping(value = "addClient", method = RequestMethod.GET)
    public String addClient(Model model) {
        model.addAttribute("client", new Client());
        return "add";
    }

    @RequestMapping(value = "addClient", method = RequestMethod.POST)
    public String addClient(@ModelAttribute("client") Client client) {
        this.clientRepository.addClient(client);
        return "myoffice";
    }

    @RequestMapping(value = "deleteClient/{id}", method = RequestMethod.GET)
    public String deleteClient(@PathVariable Integer id) {

        this.clientRepository.removeClient(id);
        return "myoffice";
    }

    @RequestMapping(value = "editClient", method = RequestMethod.GET)
    public String editClient(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findUserByLogin(userName);
        model.addAttribute("client", user.getCId());
        return "myoffice";
    }

    @RequestMapping(value = "/editClient", method = RequestMethod.POST)
    public String editClient(@ModelAttribute("client") Client client) {
        this.clientRepository.updateClient(client);
        return "myoffice";
    }

}