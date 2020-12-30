package guru.springframework.sfgpetclinic.controllers;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    @RequestMapping({"","/","/index","/index.html"})
    public String listOwners(){
        return "owner/OwnerIndex";
    }
}
