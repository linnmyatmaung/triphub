package com.triphub.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.triphub.model.Bus;
import com.triphub.model.Product;
import com.triphub.services.BusRepository;

@Controller
@RequestMapping("/bus")
public class BusController {

    private final BusRepository repo;

    public BusController(BusRepository repo) {
        this.repo = repo;
    }

    @GetMapping({"","/"})
    public String getBus(Model model) {
        List<Bus> buses = repo.findAll(); // Ensure repository method is correct
        model.addAttribute("buses", buses);
        return "bus/index"; // Name of the Thymeleaf template
    }
    @GetMapping("/add")
    public String addBus(Model model) {
        model.addAttribute("bus", new Bus());
        return "bus/add";
    }
    @PostMapping("/add")
    public String saveBus(Bus bus, RedirectAttributes redirectAttributes) {
        repo.save(bus);
        redirectAttributes.addFlashAttribute("successMessage", "Bus added successfully!");
        return "redirect:/bus";
    }
    @GetMapping("/edit/{busId}")
    public String showEditForm(@PathVariable("busId") String busId, Model model) {
        Optional<Bus> bus = repo.findById(busId);
        if (bus.isPresent()) {
            model.addAttribute("bus", bus.get());
            return "bus/edit";
        } else {
            return "redirect:/bus";
        }
    }
    @PostMapping("/edit/{busId}")
    public String updateBus(@PathVariable("busId") String busId, @ModelAttribute Bus bus, RedirectAttributes redirectAttributes) {
        Optional<Bus> existingBus = repo.findById(busId);
        if (existingBus.isPresent()) {
            Bus updatedBus = existingBus.get();
            updatedBus.setVin(bus.getVin());
            updatedBus.setBusType(bus.getBusType());
            updatedBus.setCapacity(bus.getCapacity());
            updatedBus.setYear(bus.getYear());
            updatedBus.setModel(bus.getModel());
            updatedBus.setStatus(bus.getStatus());
            updatedBus.setOperatorId(bus.getOperatorId());
            updatedBus.setRouteId(bus.getRouteId());
            repo.save(updatedBus);
            redirectAttributes.addFlashAttribute("successMessage", "Bus updated successfully!");
            return "redirect:/bus";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Bus not found");
            return "redirect:/bus";
        }
    }
    @GetMapping("/delete/{busId}")
    public String deleteProduct(@PathVariable("busId") String busId, RedirectAttributes redirectAttributes) {
        Optional<Bus> bus = repo.findById(busId);
        if (bus.isPresent()) {
            repo.delete(bus.get());
            redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found");
        }
        return "redirect:/bus";
    }
}
