package com.tba.editor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tba.editor.entity.command.DirectionCommand;

/**
 * Représente un passage entre deux lieux.
 */
@Entity
@Table(name = "room_connection")
public class RoomConnection
{
    /**
     * L'identifiant en base de données
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    /**
     * Le lieu de départ
     */
    @ManyToOne
    @JoinColumn(name = "from_room_id")
    private Room fromRoom;
    /**
     * Le lieu d'arrivée
     */
    @ManyToOne
    @JoinColumn(name = "to_room_id")
    private Room toRoom;
    /**
     * La direction à emprunter
     */
    @ManyToOne
    @JoinColumn(name = "direction_id")
    private DirectionCommand direction;

    /**
     * @return L'identifiant en base de données
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return Le lieu de départ
     */
    public Room getFromRoom() {
        return fromRoom;
    }

    /**
     * Modifie le lieu de départ
     * @param room Le nouveau lieu de départ
     */
    public void setFromRoom(Room room) {
        this.fromRoom = room;
    }
    
    /**
     * @return Le lieu d'arrivée
     */
    public Room getToRoom() {
        return toRoom;
    }

    /**
     * Modifie le lieu d'arrivée
     * @param room Le nouveau lieu d'arrivée
     */
    public void setToRoom(Room room) {
        this.toRoom = room;
    }
    
    /**
     * @return La direction à emprunter
     */
    public DirectionCommand getDirection() {
        return direction;
    }

    /**
     * Modifie la direction à emprunter
     * @param direction La nouvelle direction à emprunter
     */
    public void setDirection(DirectionCommand direction) {
        this.direction = direction;
    }
}
