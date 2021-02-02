package guru.springframework.petclinic.controllers;

import guru.springframework.petclinic.services.map.OwnerServiceMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnersController {

    private final OwnerServiceMap ownerServiceMap;

    public OwnersController(OwnerServiceMap ownerServiceMap) {
        this.ownerServiceMap = ownerServiceMap;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerServiceMap.findAll());
        return "owners/index";
    }

    @RequestMapping("/find")
        public String findOwners(Model model) {
            return "notimplemented";
        }
    }

