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

package com.seailz.modalapi.controller;

import com.seailz.modalapi.Modal;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.Interaction;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ItemComponent;

import java.util.ArrayList;
import java.util.Map;

/**
 * Manages the back-end logic for the API.
 * @author Seailz
 * @version 1.0
 * @since 04/07/2022 (dd/MM/yyyy)
 */
public class ModalManager {

    @Getter
    private static final ArrayList<Map.Entry<Member, Modal>> modals = new ArrayList<>();

    /**
     * Opens the modal
     * @param modal The modal to open
     * @param member The member to show the modal to
     * @param interaction The interaction to reply to
     */
    public static void open(Modal modal, Member member, Interaction interaction) {
        ArrayList<ItemComponent> components = new ArrayList<>();
        modal.getComponents().forEach(component -> {
            components.add(component.apply(member));
        });

        net.dv8tion.jda.api.interactions.components.Modal.Builder jdaModal = net.dv8tion.jda.api.interactions.components.Modal.create(
                modal.getId(), modal.getTitle()
        );

        components.forEach(component -> {
            jdaModal.addActionRows(ActionRow.of(component));
        });

        if (interaction instanceof SlashCommandInteractionEvent) {
            SlashCommandInteractionEvent e = (SlashCommandInteractionEvent) interaction;
            e.replyModal(jdaModal.build()).queue();
        } else if (interaction instanceof ButtonInteractionEvent) {
            ButtonInteractionEvent e = (ButtonInteractionEvent) interaction;
            e.replyModal(jdaModal.build()).queue();
        } else
            throw new IllegalStateException("Interaction is not a SlashCommandInteractionEvent or ButtonInteractionEvent");


        modals.add(Map.entry(member, modal));

    }
}
