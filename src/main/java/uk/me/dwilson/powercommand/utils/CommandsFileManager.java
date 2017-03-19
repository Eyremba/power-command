package uk.me.dwilson.powercommand.utils;

import com.google.common.io.ByteStreams;
import uk.me.dwilson.powercommand.Main;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by dwils on 17/03/2017.
 */
public class CommandsFileManager {
    private Main main;
    private File file;

    public CommandsFileManager(Main main) {
        this.main = main;

        if (!main.getDataFolder().mkdir()) {
            main.getLogger().info("Created plugin data folder.");
        }

        this.file = new File(main.getDataFolder(), "commands.txt");

        try {
            if (file.createNewFile()) {
                main.getLogger().info("Created plugin commands.txt.");

                try (InputStream inputStream = main.getResource("commands.txt")) {
                    OutputStream outputStream = new FileOutputStream(file);
                    ByteStreams.copy(inputStream, outputStream);
                }
            }
        } catch (IOException exception) {
            throw new RuntimeException("Unable to create commands.txt!", exception);
        }
    }

    public ArrayList<String> getCommandList() {
        ArrayList<String> commandList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                commandList.add(line);
            }
        } catch (IOException exception) {
            throw new RuntimeException("Unable to read from commands.txt!", exception);
        }

        return commandList;
    }
}
