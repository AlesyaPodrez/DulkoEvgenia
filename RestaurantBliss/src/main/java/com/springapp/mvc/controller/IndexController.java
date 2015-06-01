package com.springapp.mvc.controller;

import com.springapp.mvc.domain.*;
import com.springapp.mvc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Евгения on 08.05.2015.
 */
@Controller
public class IndexController {

    private MyTableRepository myTableRepository;
    private DishRepository dishRepository;
    private DishTypeRepository dishTypeRepository;
    private ReservationRepository reservationRepository;


    @Autowired
    public IndexController(DishRepository dishRepository, DishTypeRepository dishTypeRepository, ReservationRepository reservationRepository, MyTableRepository myTableRepository) {
        this.myTableRepository = myTableRepository;
        this.dishRepository = dishRepository;
        this.dishTypeRepository = dishTypeRepository;
        this.reservationRepository = reservationRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public String toMenu(Model model) {
        List<DishType> dishTypes = this.dishTypeRepository.listAll();
        List<Dish> dishes = this.dishRepository.listAll();
        model.addAttribute("dishTypes", dishTypes);
        model.addAttribute("dishes", dishes);
        return "menu";
    }


    @RequestMapping(value = "myoffice", method = RequestMethod.GET)
    public String toMyOffice() {
        return "myoffice";
    }


    @RequestMapping(value = "reservation", method = RequestMethod.GET)
    public String toReservation(Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new java.util.Date());
        model.addAttribute("date", date);
        return "reservation";
    }

    @RequestMapping(value = "reservation", method = RequestMethod.POST)
    public String reservation(@RequestParam String dateStr, @RequestParam String timeInStr, Model model) {
        DateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        Date t=null;
        timeInStr+=":00";
        try {
            t = sdf1.parse(timeInStr);
            } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar tt = Calendar.getInstance();
        tt.setTime(t);
        tt.add(Calendar.HOUR, 2);
        String strTimeOut = tt.getTime().toString().substring(11, 19);
        List<Reservation> reservations = this.reservationRepository.findReservationByDate(dateStr, timeInStr, strTimeOut);
        List<MyTable> tables = this.myTableRepository.listAll();
        for(Reservation reservation: reservations) {
            for(MyTable table: tables) {
                if(table.getId().equals(reservation.getTId().getId())) {
                    tables.remove(table);
                    break;
                }
            }
        }
        Reservation yourRes = new Reservation(dateStr, timeInStr, null, null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new java.util.Date());
        model.addAttribute("date", date);
        model.addAttribute("yourRes", yourRes);
        model.addAttribute("client", new Client());
        model.addAttribute("reservations", reservations);
        model.addAttribute("tables",tables);
        return "reservation";
    }



}
