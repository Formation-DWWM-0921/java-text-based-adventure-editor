package com.tba.editor.controller;

import java.util.ArrayList;
import java.util.List;

import com.tba.editor.entity.command.Command;
import com.tba.editor.entity.command.DirectionCommand;
import com.tba.editor.entity.command.ExitCommand;
import com.tba.editor.entity.command.ItemCommand;
import com.tba.editor.entity.command.LookCommand;
import com.tba.editor.repository.CommandRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
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

/**
 * Contrôleur spécialisé dans les routes permettant de gérer des objets Command
*/
@Controller
@RequestMapping("/commands")
public class CommandController
{
    /**
     * Le gestionnaire responsable des opérations en base de données
     */
    @Autowired
    private CommandRepository repository;

    @GetMapping
    public String index(Model model)
    {
        model.addAttribute("commands", repository.findAll());
        model.addAttribute("commandPrototypes", new Command[] {
            new ItemCommand(),
            new DirectionCommand(),
            new ExitCommand(),
            new LookCommand()
        });
        return "commands/index";
    }

    @GetMapping("/{id}")
    public String details(Model model, @PathVariable int id)
    {
        Command command = repository.findById(id).orElseThrow(
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Command #" + id + " does not exist."); }
        );

        model.addAttribute("command", command);
        return "commands/details";
    }

    @GetMapping("/create")
    public RedirectView redirectToCreateForm(@RequestParam String tag)
    {
        return new RedirectView("/commands/" + tag + "/create");
    }

    @PostMapping("/{id}/delete")
    public RedirectView delete(@PathVariable int id)
    {
        Command command = repository.findById(id).orElseThrow(
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Command #" + id + " does not exist."); }
        );
        repository.delete(command);
        return new RedirectView("/commands");
    }
}
