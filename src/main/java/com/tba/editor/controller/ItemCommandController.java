package com.tba.editor.controller;

import com.tba.editor.entity.command.ItemCommand;
import com.tba.editor.repository.ItemCommandRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/commands/item")
public class ItemCommandController
{
    @Autowired
    private ItemCommandRepository repository;

    @GetMapping("/create")
    public String createForm(Model model)
    {
        model.addAttribute("command", new ItemCommand());
        return "commands/edit";
    }

    @PostMapping("/create")
    public RedirectView create(@RequestParam String name, @RequestParam String defaultMessage)
    {
        ItemCommand command = new ItemCommand();
        command.setName(name);
        command.setDefaultMessage(defaultMessage);
        ItemCommand savedCommand = repository.save(command);
        return new RedirectView("/commands/" + savedCommand.getId());
    }

    @GetMapping("/{id}/update")
    public String updateForm(Model model, @PathVariable int id)
    {
        ItemCommand command = repository.findById(id).orElseThrow(
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Command #" + id + " does not exist."); }
        );
        model.addAttribute("command", command);
        return "commands/edit";
    }

    @PostMapping("/{id}/update")
    public RedirectView update(@PathVariable int id, @RequestParam String name, @RequestParam String defaultMessage)
    {
        ItemCommand command = repository.findById(id).orElseThrow(
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Command #" + id + " does not exist."); }
        );
        command.setName(name);
        command.setDefaultMessage(defaultMessage);
        ItemCommand savedCommand = repository.save(command);
        return new RedirectView("/commands/" + savedCommand.getId());
    }
}
