package com.sliit.y1s2._5.Roomie.controller;

import com.sliit.y1s2._5.Roomie.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("rooms", roomService.getAllRooms());

        return "room/listRooms";
    }
}