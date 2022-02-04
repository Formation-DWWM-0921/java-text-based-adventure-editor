package com.tba.editor.controller;

import com.tba.editor.entity.Room;
import com.tba.editor.repository.RoomRepository;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Contrôleur spécialisé dans les routes permettant de gérer des objets Room
 */
@Controller
@RequestMapping("/rooms")
public class RoomController
{
    /**
     * Le gestionnaire responsable des opérations en base de données
     */
    @Autowired
    private RoomRepository repository;

    /**
     * Afficher tous les éléments
     * @param model Le gestionnaire permettant de communiquer avec la vue
     * @return Le nom du template à partir duquel construire la page HTML
     */
    @GetMapping
    public String index(Model model)
    {
        // Ajoute la liste de tous les éléments aux variables accessibles par la vue
        model.addAttribute("rooms", repository.findAll());
        // Renvoie le nom du template à partir duquel construire la page HTML
        return "rooms/index";
    }

    /**
     * Affiche les détails d'un élément
     * @param model Le gestionnaire permettant de communiquer avec la vue
     * @param id L'identifiant en base de données de l'élément demandé
     * @return Le nom du template à partir duquel construire la page HTML
     */
    @GetMapping("/{id}")
    public String details(Model model, @PathVariable int id)
    {
        // Récupère l'élément demandé en base de données
        Room room = repository.findById(id).orElseThrow(
            // Si l'élément n'existe pas, génère une erreur 404
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room #" + id + " does not exist."); }
        );
        // Ajoute l'élément demandé aux variables accessibles par la vue
        model.addAttribute("room", room);
        // Renvoie le nom du template à partir duquel construire la page HTML
        return "rooms/details";
    }

    /**
     * Affiche le formulaire de création d'un nouvel élément
     * @return Le nom du template à partir duquel construire la page HTML
     */
    @GetMapping("/create")
    public String createForm(Model model)
    {
        // Ajoute l'élément demandé aux variables accessibles par la vue
        model.addAttribute("room", new Room());
        // Renvoie le nom du template à partir duquel construire la page HTML
        return "rooms/edit";
    }

    /**
     * Traite le contenu du formulaire d'ajout envoyé par le client
     * @param name Le nom du nouveau lieu
     * @param description La description du nouveau lieu
     * @return Une redirection vers la page de détails du nouveau lieu
     */
    @PostMapping("/create")
    public RedirectView create(RedirectAttributes attributes, @RequestParam String name, @RequestParam String description)
    {
        // Crée un nouveau lieu
        Room room = new Room();
        // Remplit les propriétés du nouveau lieu à partir des données du formulaire
        room.setName(name);
        room.setDescription(description);
        // Sauvegarde le nouveau lieu en base de données
        Room savedRoom = repository.save(room);
        // Ajoute un message éphémère à afficher sur la prochaine page
        attributes.addFlashAttribute("message", "New room succesfully created!");
        // Redirige vers la page de détails du nouveau lieu
        return new RedirectView("/rooms/" + savedRoom.getId());
    }

    /**
     * Affiche le formulaire de modification d'un élément existant
     * @param model Le gestionnaire permettant de communiquer avec la vue
     * @param id L'identifiant en base de données de l'élément demandé
     * @return Le nom du template à partir duquel construire la page HTML
     */
    @GetMapping("/{id}/update")
    public String updateForm(Model model, @PathVariable int id)
    {
        // Récupère l'élément demandé en base de données
        Room room = repository.findById(id).orElseThrow(
            // Si l'élément n'existe pas, génère une erreur 404
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room #" + id + " does not exist."); }
        );
        // Ajoute l'élément demandé aux variables accessibles par la vue
        model.addAttribute("room", room);
        // Renvoie le nom du template à partir duquel construire la page HTML
        return "rooms/edit";
    }

    /**
     * Traite le contenu du formulaire de modification envoyé par le client
     * @param id L'identifiant en base de données de l'élément demandé
     * @param name Le nouveau nom du lieu
     * @param description La nouvelle description du lieu
     * @return Une redirection vers la page de détails du lieu modifié
     */
    @PostMapping("/{id}/update")
    public RedirectView update(RedirectAttributes attributes, @PathVariable int id, @RequestParam String name, @RequestParam String description)
    {
        // Récupère l'élément demandé en base de données
        Room room = repository.findById(id).orElseThrow(
            // Si l'élément n'existe pas, génère une erreur 404
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room #" + id + " does not exist."); }
        );
        // Change les propriétés du lieu existant à partir des données du formulaire
        room.setName(name);
        room.setDescription(description);
        // Sauvegarde le nouveau lieu en base de données
        repository.save(room);
        // Ajoute un message éphémère à afficher sur la prochaine page
        attributes.addFlashAttribute("message", "Room succesfully updated!");
        // Redirige vers la page de détails du nouveau lieu
        return new RedirectView("/rooms/" + id);
    }

    /**
     * Supprime un élément
     * @param id L'identifiant en base de données de l'élément demandé
     * @return Une redirection vers listant tous les éléments existants
     */
    @PostMapping("/{id}/delete")
    public RedirectView delete(RedirectAttributes attributes, @PathVariable int id)
    {
        // Récupère l'élément demandé en base de données
        Room room = repository.findById(id).orElseThrow(
            // Si l'élément n'existe pas, génère une erreur 404
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room #" + id + " does not exist."); }
        );
        // Supprime l'élément de la base de données
        repository.delete(room);
        // Ajoute un message éphémère à afficher sur la prochaine page
        attributes.addFlashAttribute("message", "Room succesfully deleted!");
        // Redirige vers la page listant tous les éléments existants
        return new RedirectView("/rooms");
    }
}
