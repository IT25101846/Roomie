package com.sliit.y1s2._5.Roomie.controller;

import com.sliit.y1s2._5.Roomie.model.Room;
import com.sliit.y1s2._5.Roomie.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired private RoomService roomService;

    @GetMapping("/list")
    public String listRooms(@RequestParam(required = false) String type, Model model) {
        model.addAttribute("rooms",
                (type != null && !type.isBlank()) ? roomService.getRoomsByType(type)
                                                   : roomService.getAllRooms());
        model.addAttribute("filterType", type);
        return "room/listRooms";
    }

    @GetMapping("/available")
    public String availableRooms(Model model) {
        model.addAttribute("rooms", roomService.getAvailableRooms());
        return "room/listRooms";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("room", new Room());
        return "room/addRoom";
    }

    @PostMapping("/add")
    public String addRoom(@ModelAttribute Room room, RedirectAttributes ra) {
        String result = roomService.addRoom(room);
        ra.addFlashAttribute(result.startsWith("ERROR") ? "error" : "success",
                result.startsWith("ERROR") ? result : "Room added successfully.");
        return "redirect:/room/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes ra) {
        return roomService.getRoomById(id).map(r -> {
            model.addAttribute("room", r);
            return "room/editRoom";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "Room not found.");
            return "redirect:/room/list";
        });
    }

    @PostMapping("/edit")
    public String updateRoom(@ModelAttribute Room room, RedirectAttributes ra) {
        String result = roomService.updateRoom(room);
        ra.addFlashAttribute(result.startsWith("ERROR") ? "error" : "success",
                result.startsWith("ERROR") ? result : "Room updated successfully.");
        return "redirect:/room/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable String id, RedirectAttributes ra) {
        String result = roomService.deleteRoom(id);
        ra.addFlashAttribute(result.startsWith("ERROR") ? "error" : "success",
                result.startsWith("ERROR") ? result : "Room deleted.");
        return "redirect:/room/list";
    }
}
