package com.tba.editor.entity.command;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Représente une commande que le joueur peut entrer
 */
@Entity
@Table(name = "command")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Command
{
    /**
     * L'identifiant en base de données
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected int id;
    /**
     * Le nom de la commande
     */
    @Column(name = "name")
    protected String name;

    /**
     * Détermine si la commande est capable de traiter une saisie utilisateur
     * @param userInput La saisie utilisateur à traiter
     * @return Vrai (true) si la commande est capable de traiter la saisie utilisateur, faux (false) sinon
     */
    public boolean match(String userInput)
    {
        // Renvoie vrai si la saisie utilisateur correspond exactement au nom de la commande, faux sinon
        if (userInput.equals(name)) {
            return true;
        }
        return false;
    }

    /**
     * @return L'identifiant en base de données
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return Le nom de la direction
     */
    public String getName()
    {
        return name;
    }

    /**
     * Modifie le nom de la commande
     * @param name Le nouveau nom
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return L'identifiant du type de commande à insérer dans les URI
     */
    abstract public String getTag();

    /**
     * @return La description du but de la commande
     */
    abstract public String getPurposeDescription();
}
