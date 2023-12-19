package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    private String filePath;
    private String command;
    private String text;

    public void start() {
        Scanner s = new Scanner(System.in);
        System.out.println("Введите команду для исполнения файловым менеджером:");
        String tmp = s.nextLine();
        String[] separated = tmp.split(" ", 3);
        if (separated.length < 2) {
            printInfo();
            start();
        } else {
            filePath = separated[0];
            command = separated[1];
            if (command.equals("-write") || command.equals("-writeA")) {
                if (separated.length != 3) {
                    System.out.println("Не введен текст для записи в файл!");
                    start();
                } else if (!hasBrackets(separated[2])) {
                    System.out.println("Вводимый текст должен быть ограничен двойными кавычками!");
                    start();
                } else {
                    text = separated[2].substring(1, separated[2].length() - 1);
                }
            }
        }
        getCommand();
    }

    private boolean hasBrackets(String text) {
        return text.startsWith("\"") && text.endsWith("\"");
    }

    private boolean isAvailablePath() {
        return new File(filePath).exists();
    }

    private void getCommand() {
        Commands c = Commands.getEnum(command);
        try {
            switch (c) {
                case READ -> read();
                case WRITE -> write(true);
                case REWRITE -> write(false);
                case CREATE -> create();
                case DELETE -> delete();
            }
        } catch (NullPointerException e) {
            printInfo();
            start();
        }
    }

    private void printInfo() {
        System.out.println("""
                Указанной команды не существует! Файловый менеджер поддерживает следующие команды:\s
                Чтение из файла: [путь] -read\s
                Запись в файл (дописать в конец): [путь] -writeA "записываемый текст"\s
                Запись в файл (перезаписать полностью): [путь] -write "записываемый текст"\s
                Удаление файла: [путь] -delete\s
                Создание файла: [путь] -create""");
    }

    private void delete() {
        File file = new File(filePath);
        if (file.delete()) {
            System.out.println("Файл удален!");
        } else System.out.println("По указанному пути файл не существует!");
        start();
    }

    private void write(boolean append) {
        if (text == null) {
            System.out.println("Не введен текст для сохранения в файл. Попробуйте еще раз");
            start();
        }
        if (isAvailablePath()) {
            try (FileWriter writer = new FileWriter(filePath, append)) {
                writer.write(text);
                writer.flush();
                System.out.println("Записано успешно!");
                start();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else System.out.println("По указанному пути файл не существует!");
    }

    private void read() {
        if (isAvailablePath()) {
            try (FileReader reader = new FileReader(filePath)) {
                System.out.println("Содержимое файла: ");
                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
                System.out.println();
                start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else System.out.println("По указанному пути файл не существует!");
    }

    private void create() {
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("Файл успешно создан");
            } else {
                System.out.println("По указанному пути уже существует файл!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        start();
    }
}
