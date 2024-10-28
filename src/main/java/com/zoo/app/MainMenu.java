package com.zoo.app;

import com.zoo.Zookeeper;
import com.zoo.utils.BoxUtils;
import com.zoo.utils.ColorUtils;
import com.zoo.utils.UserInterface;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static com.zoo.utils.ColorUtils.*;

@RequiredArgsConstructor
public class MainMenu {

    private final Zookeeper zookeeper;
    private final List<MenuOption> menuOptions = new ArrayList<>();

    public void execute(){

        loadOptions();
        printMenu();
        Optional<MenuOption> option;
        do {
            option = chooseOption();
            option.ifPresent(o -> {
                o.execute(zookeeper);
                if (!o.isExit()) {
                    printMenu();
                }
            });
        } while (option.map(o -> !o.isExit()).orElse(true));
    }

    private Optional<MenuOption> chooseOption(){

        val input = UserInterface.readInteger("Choose one option");
        val option = findById(input);
        if (option.isEmpty()) {
            UserInterface.printError("Invalid option!");
        }
        return option;
    }

    private void printMenu(){

        BoxUtils.print("Main Menu",0, 14, ANSI_CYAN);
        final int leftMenuSize = 5;
        val leftMenu = menuOptions.subList(0, leftMenuSize);
        val rightMenu = menuOptions.subList(leftMenuSize, menuOptions.size());

        for (int i = 0; i < leftMenuSize; i++) {
            printMenuOption(leftMenu.get(i));
            if (i < rightMenu.size()){
                printMenuOption(rightMenu.get(i));
            }
            UserInterface.println();
        }
        UserInterface.println();
    }

    private void printMenuOption(final MenuOption option){

        if (option.getId() == 9) {
            UserInterface.printf("%s%2d%s. %-15s ", "", option.getId(), "", option.getLabel());
        } else {
            UserInterface.printf("%s%2d%s. %-15s ", ANSI_YELLOW, option.getId(), ANSI_RESET, option.getLabel());
        }
    }

    private Optional<MenuOption> findById(final Integer id){

        return menuOptions.stream()
                .filter(o -> o.getId().equals(id))
                .findAny();
    }

    private void loadOptions() {

        addMenuOption(1, "Add animal", Zookeeper::addAnimal);
        addMenuOption(2, "View animals", Zookeeper::showAnimals);
        addMenuOption(3, "List animals", Zookeeper::listAnimals);
        addMenuOption(4, "Feed animals", Zookeeper::feedAnimals);
        addMenuOption(5, "Heal animals", Zookeeper::healAnimals);
        addMenuOption(6, "Open Zoo", Zookeeper::open);
        addMenuOption(7, "Close Zoo",Zookeeper::close);
        addMenuOption(8, "Extra Options",(z) -> new ExtraMenu(z).execute());
        addMenuOption(9, "Switch Color Profile **",(z) -> ColorUtils.switchColorProfiles());
        addMenuOption(0, "Exit", null);
    }

    private void addMenuOption (final Integer id, final String label, Consumer<Zookeeper> action){

        menuOptions.add(new MenuOption(id, label, action));
    }
}
