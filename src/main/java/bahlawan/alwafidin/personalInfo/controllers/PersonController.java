package bahlawan.alwafidin.personalInfo.controllers;

import bahlawan.alwafidin.personalInfo.entities.Parent;
import bahlawan.alwafidin.personalInfo.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonController {

    @Autowired
    ParentService parentService;

    @GetMapping("/persons")
    public String listByPage(@RequestParam(defaultValue = "asc") String sortDirection, Model model) {
        return listByPage(1, "firstName", sortDirection, model);
    }

    @GetMapping("/persons/page/{pageNumber}")
    public String listByPage(@PathVariable(name = "pageNumber") Integer pageNumber, @RequestParam String sortField,
                             @RequestParam String sortDirection, Model model) {
        Page<Parent> page = parentService.listByPage(pageNumber, sortField, sortDirection);

        int pageSize = page.getSize();

        long startCount = (long) (pageNumber - 1) * pageSize + 1;
        long endCount = startCount + pageSize - 1;
        if (endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }

        String reversedSortDir = sortDirection.equals("asc") ? "desc" : "asc";

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("startCount", startCount);
        model.addAttribute("endCount", endCount);
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("listUsers", page.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reversedSortDir", reversedSortDir);
        model.addAttribute("moduleURL", "/persons");

        return "persons";
    }


    @GetMapping("/hello")
    public String test() {
        return "test";
    }

}
