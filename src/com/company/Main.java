package com.company;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<String>();
        try {
            File file = new File("words.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        Game(words);
    }

    public static void Game(ArrayList<String> words) {
        Scanner scanner = new Scanner(System.in);
        String previousWord = null;

        while (true) {
            //bot
            if (previousWord == null) {
                Random random = new Random();
                int r = random.nextInt(words.toArray().length);
                System.out.println("[BOT]  is starting and took word: " + words.get(r));
                previousWord = words.get(r);
                words.remove(previousWord);
            } else {
                boolean found = false;
                for (int i = 0; i < words.size(); i++) {
                    if (words.get(i).charAt(0) == previousWord.charAt(previousWord.length()-1)) {
                        System.out.println("[BOT] took word: " + words.get(i));
                        previousWord = words.get(i);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("[GAME] PLayer is a winner!");
                    return;
                }
            }

            //player
            for (;;) {
                System.out.printf("[GAME] {Surrender = 'sur'} Player you have to find a word starting at [%c]: " ,  previousWord.charAt(previousWord.length()-1));
                String str = scanner.nextLine();
                str = str.toLowerCase(Locale.ROOT);
                str = str.replace(" ", "");
                if (str.equals("sur")) {
                    System.out.println("[GAME] Bot is a winner");
                    return;
                }
                if (words.contains(str) && str.charAt(0) == previousWord.charAt(previousWord.length()-1)) {
                    words.remove(str);
                    previousWord = str;
                    break;
                } else {
                    System.out.println("[GAME] Invalid input! ");
                }
            }
        }
    }
}
