package com.zoo.app;

import com.zoo.Zookeeper;
import com.zoo.utils.UserInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
@AllArgsConstructor
public class MenuOption {

    private final Integer id;
    private final String label;
    private final Consumer<Zookeeper> action;

    public Boolean isExit() {

        return id == 0;
    }

    public void execute(final Zookeeper zookeeper){

        if (action != null){
            UserInterface.println();
            action.accept(zookeeper);
        }
    }
}
