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

package com.seailz.modalapi.listeners;

import com.seailz.modalapi.Modal;
import com.seailz.modalapi.controller.ModalManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * The event that listens for modal interactions
 * @author Seailz
 * @version 1.0
 * @since 04/07/2022 (dd/MM/yyyy)
 */
public class ModalListener extends ListenerAdapter {

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent e){
        for (Map.Entry<Member, Modal> modalEntry : ModalManager.getModals()) {
            if (modalEntry.getValue().getId().equals(e.getModalId()) && modalEntry.getKey().getId().equals(e.getMember().getId())){
                ModalMapping[] mappings = e.getValues().toArray(new ModalMapping[0]);
                modalEntry.getValue().getOnSubmit().accept(e.getMember(), mappings, e);
            }
        }
    }

}
