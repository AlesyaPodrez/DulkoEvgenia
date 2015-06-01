package com.springapp.mvc.controller;

import com.springapp.mvc.domain.*;
import com.springapp.mvc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Евгения on 08.05.2015.
 */
@Controller
public class ReservationController {

    private ReservationRepository reservationRepository;
    private ClientRepository clientRepository;
    private MyTableRepository myTableRepository;
    private UserRepository userRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository, ClientRepository clientRepository, MyTableRepository myTableRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.myTableRepository=myTableRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "addReservation", method = RequestMethod.POST)
    public String addReservation(@RequestParam String dateStr, @RequestParam String timeInStr, @RequestParam int selectedTable, @ModelAttribute("client") Client client) {
        this.clientRepository.addClient(client);
        MyTable table = this.myTableRepository.findTable(selectedTable);
        Reservation reservation = new Reservation(dateStr, timeInStr, client, table);
        this.reservationRepository.addReservation(reservation);
        return "redirect:/";
    }

    @RequestMapping(value = "addReservationAuthenticated", method = RequestMethod.POST)
    public String addReservationAuthenticated(@RequestParam String dateStr, @RequestParam String timeInStr, @RequestParam int selectedTable) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findUserByLogin(userName);
        MyTable table = this.myTableRepository.findTable(selectedTable);
        Reservation reservation = new Reservation(dateStr, timeInStr, user.getCId(), table);
        this.reservationRepository.addReservation(reservation);
        return "redirect:/";
    }

    @RequestMapping(value = "myReservation", method = RequestMethod.GET)
    public String myReservation(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findUserByLogin(userName);
        List<Reservation> reservations= this.reservationRepository.findReservationByClient(user.getCId().getId());
        if(reservations.size()>0) {
            model.addAttribute("reservations", reservations);
        }
        else model.addAttribute("mess", "У вас нет зарезервированных столов");
        return "myoffice";
    }

    @RequestMapping(value = "deleteReservation/{id}", method = RequestMethod.GET)
    public String deleteReservation(@PathVariable Integer id) {
        this.reservationRepository.removeReservation(id);
        return "redirect:/myReservation";
    }

}
