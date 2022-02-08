package com.tba.editor.entity.command;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Représente une commande permettant de sortir du jeu
 */
@Entity
@Table(name = "exit_command")
public class ExitCommand extends Command
{
    /**
     * @inheritDoc
     */
    @Override
    public String getTag()
    {
        return "exit";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getPurposeDescription()
    {
        return "Exit the game";
    }
}
