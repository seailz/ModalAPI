# ModalAPI
ModalAPI lets you interact with JDA & Discord modals with ease.
Here's an example of how to make a modal:

```java
public class ModalExample extends Modal {

    public ModalExample() {
        super("Questions", "age-question"); // The title and the ID
        TextInput input = TextInput.create("age", "How old are you?", TextInputStyle.SHORT)
                .setMaxLength(2).build(); // The input fields

        addComponent(member -> input); // adds the component to the modal
        onSubmit((member, mappings, event) -> {
            event.reply("Thanks for submitting your age! We received **" + mappings[0].getAsString() + "** years old.").queue();
        }); // what to do when the modal is submitted
    }
}
```

Here's an example of opening a modal for a user:

```java
public class ModalCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("modal"))
            return;
        ModalExample testModal = new ModalExample();
        testModal.open(event.getMember(), event);
    }

}
```

If you want to use this API, you can use Maven to add it to your project:
**COMING SOON**


*Note: Discord currently supports select menus in modals, but they haven't documented it yet. If you want to use select menus, please use the maven configuration found below.
I can't gaurentee that branch will be stable though, as it is using commits from a PR on JDA, found here: https://github.com/DV8FromTheWorld/JDA/pull/2162*

<details>
<summary> Select Menus Maven </summary>

</details>