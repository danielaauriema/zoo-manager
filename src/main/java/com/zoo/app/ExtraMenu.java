package com.zoo.app;

import com.zoo.Zookeeper;
import com.zoo.utils.BoxUtils;
import com.zoo.utils.FileUtils;
import com.zoo.utils.UserInterface;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static com.zoo.utils.ColorUtils.*;

@RequiredArgsConstructor
public class ExtraMenu {

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

        BoxUtils.print("Extra Menu");
        menuOptions.forEach(this::printMenuOption);
        UserInterface.println();
    }

    private void printMenuOption(final MenuOption option){

        UserInterface.println("  %s%2d%s. %-15s ", ANSI_YELLOW, option.getId(), ANSI_RESET, option.getLabel());
    }

    private Optional<MenuOption> findById(final Integer id){

        return menuOptions.stream()
                .filter(o -> o.getId().equals(id))
                .findAny();
    }

    private void loadOptions() {

        addMenuOption(1, "Save Data", this::exportData);
        addMenuOption(2, "Load Data", this::loadData);
        addMenuOption(0, "Go back", null);
    }

    private void addMenuOption (final Integer id, final String label, Consumer<Zookeeper> action){

        menuOptions.add(new MenuOption(id, label, action));
    }

    private void exportData(Zookeeper zookeeper){

        BoxUtils.print("Save Data");
        val fileName = getFileName();
        FileUtils.saveToFile(zookeeper.getZoo(), fileName);
        UserInterface.println();
        UserInterface.colorPrintln(ANSI_GREEN,"   Zoo saved to " + fileName);
        UserInterface.println();
    }

    private void loadData(Zookeeper zookeeper){

        BoxUtils.print("Import Data");
        val fileName = getFileName();
        if (canLoadData(zookeeper)) {
            FileUtils.loadFromFile(zookeeper.getZoo(), fileName);
            UserInterface.println();
            UserInterface.colorPrintln(ANSI_GREEN, "%d animals imported from  %s",
                    zookeeper.getZoo().getCages().size(), fileName);
            UserInterface.println();
        }
    }

    private boolean canLoadData(Zookeeper zookeeper) {

        if (!zookeeper.getZoo().getCages().isEmpty()) {
            UserInterface.colorPrintln(ANSI_YELLOW, "Zoo is not empty.");
            UserInterface.colorPrintln(ANSI_YELLOW, "If you continue, all your current data will be lost.");
            while (true){
                String resp = UserInterface.readLine("Do you still wanna load the data from file? (yes/no)");
                if (resp.equalsIgnoreCase("yes")) {
                    return true;
                } else if (resp.equalsIgnoreCase("no")) {
                    return false;
                }
                UserInterface.printError("Invalid option!");
            }
        }
        return true;
    }

    private String getFileName(){

        return Paths.get(System.getProperty("user.dir"), "zoo.json").toString();
    }
}
