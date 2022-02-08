package com.tba.editor.entity.command;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tba.editor.entity.effect.Effect;

/**
 * Représente une action que le joueur peut utiliser sur un élément interactif
 */
@Entity
@Table(name = "item_command")
public class ItemCommand extends Command
{
    /**
     * Le message par défaut à afficher lorsque la commande est utilisée avec un élément interactif non prévu pour
     */
    @Column(name = "default_message")
    private String defaultMessage;
    /**
     * La liste de tous les effets que cette commande peut produire
     */
    @OneToMany
    @JoinColumn(name = "command_id")
    private List<Effect> effects;
    /**
     * Le nom de l'élément interactif sur lequel la commande doit actuellement agir
     */
    @Transient
    private String currentItemName;

    /**
     * Détermine si la commande est capable de traiter une saisie utilisateur
     * @param userInput La saisie utilisateur à traiter
     * @return Vrai (true) si la commande est capable de traiter la saisie utilisateur, faux (false) sinon
     */
    @Override
    public boolean match(String userInput)
    {
        // Oublie le précédent élément interactif sur laquelle la commande devait agir
        currentItemName = null;
        // Renvoie vrai si la saisie utilisateur contient le nom de la commande suivi d'un nom d'objet, faux sinon
        Pattern pattern = Pattern.compile("^" + name + "\\s+(.+)$");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.matches()) {
            // Retient temporairement le nom de l'élément interactif sur lequel la commande doit être utilisée
            currentItemName = matcher.group(1);
            return true;
        }
        return false;
    }

    /**
     * @return Le message par défaut à afficher lorsque la commande est utilisée avec un élément interactif non prévu pour
     */
    public String getDefaultMessage()
    {
        return defaultMessage;
    }

    /**
     * Modifie le message par défaut de la commande
     * @param message Le nouveau message
     */
    public void setDefaultMessage(String message)
    {
        this.defaultMessage = message;
    }

    /**
     * @return Le nom de l'élément interactif sur lequel la commande doit actuellement agir
     */
    public String getCurrentItemName()
    {
        return currentItemName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getTag()
    {
        return "item";
    }

    
    /**
     * @inheritDoc
     */
    @Override
    public String getPurposeDescription()
    {
        return "Interact with items";
    }
}
