package projects.goodthoughts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projects.goodthoughts.model.Quote;
import projects.goodthoughts.option.DefaultQuoteOption;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Set;

public class GoodThoughtsApp {

    private static final Logger logger = LoggerFactory.getLogger(GoodThoughtsApp.class);

    public static void main(String[] args) {
        System.out.println("Witaj");
        logger.info("Uruchamianie aplikacji ...");

        Quote quote1 = new Quote("Life is beautiful", "Życie jest piękne",
                "Anonim", "Życiowe", LocalDateTime.now());

        Quote quote2 = new Quote("Life is brutal and full of zasadzkasz",
                "Życie jest brutalne i pełne zasadzek", "Wujek dobra rada", "Życiowe",
                LocalDateTime.now().minusMonths(3));

        logger.debug("Test cytatu 1: " + quote1 + " - koniec cytatu");
        logger.debug("Test cytatu 2: {} - koniec cytatu", quote2);
        logger.debug(String.format("Oba cytaty, 1: %s, 2: %s", quote1, quote2));

        GoodThoughtsApp app = new GoodThoughtsApp();
        app.run();
    }

    public void run(Object... args) {
        logger.info("Rozpoczęcie pracy z aplikacją");
        showWelcomeMessage();
        while (true) {
            showMenu();
            String userInput = getUserInput();
            boolean isValidOption = validateOption(userInput);
            if (isValidOption) {
                executeOption(userInput);
                boolean isExitOption = checkIsExitOption(userInput);
                if (isExitOption) {
                    break;
                }
            } else {
                showInvalidOptionMessage(userInput);
            }
        }
        showGoodbyeMessage();
        logger.info("Zakończono pracę z aplikacją");
    }

    private void showGoodbyeMessage() {
        System.out.println("Dziękujemy za skorzystanie z naszej aplikacji. Wróć tu jeszcze!");
    }

    private void showInvalidOptionMessage(String userInput) {
        System.out.println("Wybrałeś nieprawidłową opcję ('" + userInput + "')");
    }

    private boolean checkIsExitOption(String userInput) {
//        return userInput.equals("0");
        return "0".equals(userInput);
    }

    private void executeOption(String userInput) {
        logger.debug("Wykonywana opcja: {}", userInput);
        switch (userInput) {
            case "1":
                executeDefaultQuoteOption();
                break;
            case "2":
            case "3":
        }
    }

    private void executeDefaultQuoteOption() {
        DefaultQuoteOption option = new DefaultQuoteOption();
        option.run();
    }

    private boolean validateOption(String userInput) {
        Set<String> validOptions = Set.of("0", "1", "2", "3");
        return validOptions.contains(userInput);
//        switch (userInput) {
//            case "0":
//            case "1":
//            case "2":
//            case "3":
//                return true;
//            default:
//                return false;
//        }
//        if (userInput.equals("1")) {
//            return true;
//        }
//        return false;
    }

    private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nTwój wybór: ");
        String userInput = scanner.nextLine();
        return userInput.trim();
    }

    private void showMenu() {
        System.out.println("Wybierz opcje z listy poniżej:");
        System.out.println("\t1. Pobierz domyślny cytat");
        System.out.println("\t2. Dodaj własny cytat");
        System.out.println("\t3. Wyświetl zapisane cytaty");
        System.out.println("\t0. Zakończ");
    }

    private void showWelcomeMessage() {
        System.out.println("Witaj w naszej aplikacji (" + LocalDateTime.now() + ")");
    }
}
