package com.tba.editor.controller;

import com.tba.editor.entity.Item;
import com.tba.editor.entity.Room;
import com.tba.editor.repository.ItemRepository;
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
 * Contrôleur spécialisé dans les routes permettant de gérer des objets Item
 */
@Controller
@RequestMapping("/items")
public class ItemController
{
    /**
     * Le gestionnaire responsable des opérations en base de données
     */
    @Autowired
    private ItemRepository repository;
    @Autowired
    private RoomRepository roomRepository;

    /**
     * Afficher tous les éléments
     * @param model Le gestionnaire permettant de communiquer avec la vue
     * @return Le nom du template à partir duquel construire la page HTML
     */
    @GetMapping
    public String index(Model model)
    {
        // Ajoute la liste de tous les éléments aux variables accessibles par la vue
        model.addAttribute("items", repository.findAll());
        // Renvoie le nom du template à partir duquel construire la page HTML
        return "items/index";
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
        Item item = repository.findById(id).orElseThrow(
            // Si l'élément n'existe pas, génère une erreur 404
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item #" + id + " does not exist."); }
        );
        // Ajoute l'élément demandé aux variables accessibles par la vue
        model.addAttribute("item", item);
        // Renvoie le nom du template à partir duquel construire la page HTML
        return "items/details";
    }

    /**
     * Affiche le formulaire de création d'un nouvel élément
     * @return Le nom du template à partir duquel construire la page HTML
     */
    @GetMapping("/create")
    public String createForm(Model model)
    {
        // Ajoute l'élément demandé aux variables accessibles par la vue
        Item item = new Item();
        item.setVisible(true);
        item.setRoom(new Room());
        model.addAttribute("item", item);
        model.addAttribute("rooms", roomRepository.findAll());
        // Renvoie le nom du template à partir duquel construire la page HTML
        return "items/edit";
    }

    /**
     * Traite le contenu du formulaire d'ajout envoyé par le client
     * @param name Le nom du nouveau élément
     * @param visible Le nouveau élément est-il visible?
     * @param roomId L'identifiant en base de données du lieu dans lequel se trouve l'élément
     * @return Une redirection vers la page de détails du nouveau lieu
     */
    @PostMapping("/create")
    public RedirectView create(RedirectAttributes attributes, @RequestParam String name, @RequestParam(defaultValue = "0") boolean visible, @RequestParam int roomId)
    {
        // Récupère l'élément demandé en base de données
        Room room = roomRepository.findById(roomId).orElseThrow(
            // Si l'élément n'existe pas, génère une erreur 404
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room #" + roomId + " does not exist."); }
        );
        // Remplit les propriétés du nouveau élément interactif à partir des données du formulaire
        Item item = new Item();
        item.setName(name);
        item.setVisible(visible);
        item.setRoom(room);
        // Sauvegarde le nouveau élément interactif en base de données
        Item savedItem = repository.save(item);
        // Ajoute un message éphémère à afficher sur la prochaine page
        attributes.addFlashAttribute("message", "New item succesfully created!");
        // Redirige vers la page de détails du nouveau lieu
        return new RedirectView("/items/" + savedItem.getId());
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
        Item item = repository.findById(id).orElseThrow(
            // Si l'élément n'existe pas, génère une erreur 404
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item #" + id + " does not exist."); }
        );
        // Ajoute l'élément demandé aux variables accessibles par la vue
        model.addAttribute("item", item);
        model.addAttribute("rooms", roomRepository.findAll());
        // Renvoie le nom du template à partir duquel construire la page HTML
        return "items/edit";
    }

    /**
     * Traite le contenu du formulaire de modification envoyé par le client
     * @param id L'identifiant en base de données de l'élément demandé
     * @param name Le nom du nouveau élément
     * @param visible Le nouveau élément est-il visible?
     * @param roomId L'identifiant en base de données du lieu dans lequel se trouve l'élément
     * @return Une redirection vers la page de détails du lieu modifié
     */
    @PostMapping("/{id}/update")
    public RedirectView update(RedirectAttributes attributes, @PathVariable int id, @RequestParam String name, @RequestParam(defaultValue = "0") boolean visible, @RequestParam int roomId)
    {
        // Récupère l'élément demandé en base de données
        Item item = repository.findById(id).orElseThrow(
            // Si l'élément n'existe pas, génère une erreur 404
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item #" + id + " does not exist."); }
        );    
        // Récupère l'élément demandé en base de données
        Room room = roomRepository.findById(roomId).orElseThrow(
            // Si l'élément n'existe pas, génère une erreur 404
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room #" + roomId + " does not exist."); }
        );
        // Change les propriétés de l'élément interactif existant à partir des données du formulaire
        item.setName(name);
        item.setVisible(visible);
        item.setRoom(room);
        // Sauvegarde le nouvel élément interactif en base de données
        repository.save(item);
        // Ajoute un message éphémère à afficher sur la prochaine page
        attributes.addFlashAttribute("message", "Item succesfully updated!");
        // Redirige vers la page de détails du nouveau lieu
        return new RedirectView("/items/" + id);
    }

    @PostMapping("/{id}/delete")
    public RedirectView delete(RedirectAttributes attributes, @PathVariable int id)
    {
        // Récupère l'élément demandé en base de données
        Item item = repository.findById(id).orElseThrow(
            // Si l'élément n'existe pas, génère une erreur 404
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item #" + id + " does not exist."); }
        );
        // Supprime l'élément de la base de données
        repository.delete(item);
        // Ajoute un message éphémère à afficher sur la prochaine page
        attributes.addFlashAttribute("message", "Item succesfully deleted!");
        // Redirige vers la page listant tous les éléments existants
        return new RedirectView("/items");
    }
}
