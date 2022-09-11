# ModalAPI
ModalAPI lets you interact with JDA & Discord modals with ease.
Here's an example of how to make a modal:

### QUICK DISCALIMER
You **must** include this line in your main class:
```java
        jdaInstanceVariable.addEventListener(new ModalListener());
```

If you don't, it will not work.
I may try to find a way around this in the future, but for now, that's how it is.

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

If you want to use this API, you can use Maven or Gradle to add it to your project:

Maven:

```xml
<repository>
    <id>jitpack.io</id>
	<url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
    <groupId>com.github.seailz</groupId>
	<artifactId>modalapi</artifactId>
	<version>v1.0</version>
</dependency>
```

Gradle:

```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```groovy
	dependencies {
	        implementation 'com.github.seailz:modalapi:Tag'
	}
```
