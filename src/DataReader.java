import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataReader {

    public static void ReadParticipantsData(String fileName,
                                            HashMap<String, Integer> preferences,
                                            ArrayList<ParticipantsData> participantsPreferences) {
        try (Scanner data = new Scanner(new File(fileName))) {
            data.nextLine(); //skip first line with description

            while (data.hasNext()) {
                String row = data.next();
                String[] strData = row.split("[;|]");
                String prefPart1 = strData[1];

                String[] prefCalc = prefPart1.split(",");
                String genre = prefCalc[0];
                int pref = Integer.parseInt(prefCalc[1]);
                int prefSum = preferences.getOrDefault(genre, 0);
                prefSum += pref;
                preferences.put(genre, prefSum);

                if (pref < 100) {
                    String prefPart2 = strData[2];
                    String[] prefCalc2 = prefPart2.split(",");
                    String genre2 = prefCalc2[0];
                    int pref2 = Integer.parseInt(prefCalc2[1]);
                    prefSum = preferences.getOrDefault(genre2, 0);
                    prefSum += pref2;
                    preferences.put(genre2, prefSum);
                    participantsPreferences.add(new ParticipantsData(Integer.parseInt(strData[0]), genre, pref, genre2, pref2));
                } else
                    participantsPreferences.add(new ParticipantsData(Integer.parseInt(strData[0]), genre, pref, "", 0));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found.");
        }
    }

    public static void ReadMusicData(String fileName,
                                     HashMap<String, Integer> genreDuration,
                                     HashMap<String, Integer> genreCount,
                                     ArrayList<ListOfAllMusic> musicList,
                                     HashMap<String, Integer> genrePopularity) {

        try (Scanner data = new Scanner(new File(fileName))) {
            data.nextLine(); //skip first line with description

            while (data.hasNext()) {
                String row = data.next();
                String[] strData = row.split(";");
                int index = Integer.parseInt(strData[0]);
                String genre = strData[1];
                int time = Integer.parseInt(strData[2]);
                int maxPopular = genrePopularity.get(genre);

                musicList.add(new ListOfAllMusic(index, genre, time, maxPopular, 0, 0, 0));

                int timeSum = genreDuration.getOrDefault(genre, 0);
                timeSum += time;
                genreDuration.put(genre, timeSum);

                int sumCount = genreCount.getOrDefault(genre, 0);
                sumCount += 1;
                genreCount.put(genre, sumCount);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found.");
        }
    }

    public static long ReadDataFromUser(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        long userValue = 0;
        boolean error;
        do {
            error = false;
            try {
                userValue = Long.parseLong(scanner.nextLine());
            } catch (Exception e) {
                error = true;
                System.out.println("Wrong value, enter your value again");
            }
        } while (error);

        return userValue;
    }
}
