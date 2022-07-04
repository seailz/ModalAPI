/**
 * MODAL API - Interact with JDA modals with ease.
 * Copyright (C) 2022 Seailz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.seailz.modalapi;

import com.seailz.modalapi.controller.ModalManager;
import com.seailz.modalapi.util.TriConsumer;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Manages the front end logic for the API.
 * Extend this class to create your own modals.
 *
 * @author Seailz
 * @version 1.0
 * @since 04/07/2022 (dd/MM/yyyy)
 */
@Getter
public class Modal {

    private final String title;
    private final String id;
    private final ArrayList<Function<Member, ItemComponent>> components = new ArrayList<>();
    private TriConsumer<Member, ModalMapping[], ModalInteractionEvent> onSubmit;


    /**
     * Creates a new Modal instance (you must extend this class, don't create an instance of this class!!)
     * @param title The title of the modal
     * @param id The id of the modal
     */
    public Modal(String title, String id) {
        this.title = title;
        this.id = id;
    }

    /**
     * Adds a component to the modal.
     * @param component The component to add.
     */
    public void addComponent(Function<Member, ItemComponent> component) {
        components.add(component);
    }

    /**
     * Opens the modal
     * @param member The member to show the modal to
     * @param interaction The interaction to reply to
     */
    public void open(Member member, ButtonInteractionEvent interaction) {
        ModalManager.open(this, member, interaction);
    }

    /**
     * Opens the modal
     * @param member The member to show the modal to
     * @param interaction The interaction to reply to
     */
    public void open(Member member, SlashCommandInteractionEvent interaction) {
        ModalManager.open(this, member, interaction);
    }

    /**
     * Selects the method to be called when the modal is submitted.
     * @param onSubmit The method to be called when the modal is submitted.
     */
    public void onSubmit(TriConsumer<Member, ModalMapping[], ModalInteractionEvent> onSubmit) {
        this.onSubmit = onSubmit;
    }
}
