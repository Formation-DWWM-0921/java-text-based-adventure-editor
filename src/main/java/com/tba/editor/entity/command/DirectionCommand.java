package com.tba.editor.entity.command;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tba.editor.entity.RoomConnection;

/**
 * Représente une direction que le joueur peut emprunter pour se déplacer d'un lieu à l'autre.
 */
@Entity
@Table(name = "direction_command")
public class DirectionCommand extends Command
{
    /**
     * La liste de tous les passages entre des lieux qui empruntent cette direction
     */
    @OneToMany
    @JoinColumn(name = "direction_id")
    private List<RoomConnection> connections;

    /**
     * @inheritDoc
     */
    @Override
    public String getTag()
    {
        return "direction";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getPurposeDescription()
    {
        return "Move between rooms";
    }
}
