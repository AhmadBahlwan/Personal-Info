package bahlawan.alwafidin.personalInfo.controllers;

import bahlawan.alwafidin.personalInfo.entities.Person;
import bahlawan.alwafidin.personalInfo.services.PersonService;
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
    PersonService personService;

    @GetMapping("/persons")
    public String listByPage(@RequestParam(defaultValue = "asc") String sortDirection, Model model) {
        return listByPage(1, "firstName", sortDirection, null, model);
    }

    @GetMapping("/persons/page/{pageNumber}")
    public String listByPage(@PathVariable(name = "pageNumber") Integer pageNumber, @RequestParam String sortField,
                             @RequestParam String sortDirection, @RequestParam(required = false) String keyword, Model model) {
        Page<Person> page = personService.listByPage(pageNumber, sortField, sortDirection, keyword);

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
        model.addAttribute("keyword", keyword);
        model.addAttribute("moduleURL", "/persons");

        return "persons";
    }

    @GetMapping("/search")
    public String searchFirstPage(String keyword, Model model) {
        return searchByPage(keyword, 1, model);
    }

    @GetMapping("/search/page/{pageNumber}")
    public String searchByPage(String keyword, @PathVariable("pageNumber") int pageNumber, Model model) {
        Page<Person> page = personService.search(keyword, pageNumber);

        int pageSize = page.getSize();

        long startCount = (long) (pageNumber - 1) * pageSize + 1;
        long endCount = startCount + pageSize - 1;
        if (endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("startCount", startCount);
        model.addAttribute("endCount", endCount);
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("listUsers", page.getContent());
        model.addAttribute("moduleURL", "/persons");

        return "persons";
    }
}
