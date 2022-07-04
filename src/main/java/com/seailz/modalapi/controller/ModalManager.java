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
import java.util.HashMap;
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
    public static void open(Modal modal, Member member, SlashCommandInteractionEvent interaction) {
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

        interaction.replyModal(jdaModal.build()).queue();
        modals.add(Map.entry(member, modal));

    }

    public static void open(Modal modal, Member member, ButtonInteractionEvent interaction) {
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

        interaction.replyModal(jdaModal.build()).queue();
        modals.add(Map.entry(member, modal));
    }

}
