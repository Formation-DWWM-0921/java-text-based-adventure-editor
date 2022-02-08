package com.tba.editor.entity.command;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Représente une commande permettant d'afficher la description du lieu actuel
 */
@Entity
@Table(name = "look_command")
public class LookCommand extends Command
{
    /**
     * @inheritDoc
     */
    @Override
    public String getTag()
    {
        return "look";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getPurposeDescription()
    {
        return "Get current room description";
    }
}
