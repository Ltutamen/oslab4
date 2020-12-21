package ua.axiom.labs.view;

import java.util.Scanner;
import java.util.function.Function;

public class MenuView {
    private final Scanner scanner = new Scanner(System.in);

    public int ask(String[] questions) {
        for (int i = 0; i < questions.length; i++) {
            System.out.println(i+1 + ". " + questions[i]);
        }

        return scanner.nextInt();
    }

    public <T> T askQuestion(String question, Function<Scanner, T> reader) {
        System.out.println(question);

        return reader.apply(scanner);
    }
}
