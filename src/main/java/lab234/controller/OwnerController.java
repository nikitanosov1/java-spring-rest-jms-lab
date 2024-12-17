package lab234.controller;


import lab234.model.Owner;
import lab234.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute(new Owner());
        return "CreateOwner";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Owner owner) {
        ownerService.create(owner);
        return "redirect:/owners";
    }

    @GetMapping
    public String getAll(Model model) {
        List<Owner> owners = ownerService.getAll();
        model.addAttribute("owners", owners);
        return "ShowOwners";
    }

    @GetMapping("/{id}/update")
    public String getUpdatePage(@PathVariable("id") UUID ownerId, Model model) {
        model.addAttribute("owner", ownerService.get(ownerId));
        return "UpdateOwner";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") UUID ownerId, @ModelAttribute Owner owner) {
        ownerService.update(ownerId, owner);
        return "redirect:/owners";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") UUID id) {
        ownerService.delete(id);
        return "redirect:/owners";
    }


}
