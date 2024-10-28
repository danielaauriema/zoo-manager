package com.zoo;

import com.zoo.app.MainMenu;
import com.zoo.utils.Banner;
import com.zoo.utils.BoxUtils;
import com.zoo.utils.ColorUtils;
import com.zoo.utils.UserInterface;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.zoo.utils.ColorUtils.*;

@AllArgsConstructor
@SpringBootApplication
public class ZooManager implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(ZooManager.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Banner.show();
        BoxUtils.print("Welcome to Zoo Management System", 2, ANSI_YELLOW);

        ColorUtils.warningDisclaimer();

        UserInterface.println("Before start, let me know your name...");
        val zookeeperName = UserInterface.readLine("Zookeeper name");
        UserInterface.println();

        val zookeeper = new Zookeeper(zookeeperName, new Zoo());
        val menu = new MainMenu(zookeeper);
        menu.execute();

        Banner.show();
        UserInterface.println();
        UserInterface.println("%sGoodbye %s%s%s & Thanks for use Zoo Management System%s", ANSI_YELLOW, ANSI_CYAN, zookeeperName, ANSI_YELLOW, ANSI_RESET);
        UserInterface.println();
    }
}
