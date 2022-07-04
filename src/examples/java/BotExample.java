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

import com.seailz.modalapi.listeners.ModalListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class BotExample {

    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault("token").build();
        jda.addEventListener(new ModalListener()); // THIS IS CRUCIAL. YOU MUST INCLUDE THIS LINE.

        jda.upsertCommand("modal", "A test modal").queue();
        jda.addEventListener(new ModalCommand());
    }

}
