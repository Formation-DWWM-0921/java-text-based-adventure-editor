package com.tba.editor.entity.effect;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tba.editor.entity.Item;
import com.tba.editor.entity.command.ItemCommand;

/**
 * Représente un effet qui se déclenche lorsque le joueur utilise une commande sur un élément interactif
 */
@Entity
@Table(name = "effect")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Effect
{
    /**
     * L'identifiant en base de données
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    /**
     * La commande qui déclenche l'effet
     */
    @ManyToOne
    @JoinColumn(name = "command_id")
    private ItemCommand command;
    /**
     * L'élément interactif qui déclenche l'effet lorsque la commande est utilisée avec
     */
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    /**
     * L'ordre d'exécution de cet effet parmi tous les effets associés au même élément interactif et à la même commande
     */
    @Column(name = "_order")
    private int order;

    /**
     * @return L'identifiant en base de données
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return L'élément interactif qui déclenche l'effet lorsque la commande est utilisée avec
     */
    public Item getItem()
    {
        return item;
    }

    /**
     * @return La commande qui déclenche l'effet
     */
    public ItemCommand getCommand()
    {
        return command;
    }
}
