import java.util.*;

public class Solver {

    public static void BestSolution(ArrayList<ListOfAllMusic> musicList, long concertLength, ArrayList<ParticipantsData> participantsPreference) {
        int[] songLengths = musicList.stream().mapToInt(listOfAllMusic -> listOfAllMusic.length).toArray();
        int[] index = musicList.stream().mapToInt(listOfAllMusic -> listOfAllMusic.index).toArray();
        int lengthSum = MusicSumLength(musicList);
        long concertLengthLeft = concertLength % lengthSum;
        int whatLeft = (int) (concertLength / lengthSum);
        SetAdditionalInfo(musicList, whatLeft);

        int[] maxPopularity = musicList.stream().mapToInt(listOfAllMusic -> listOfAllMusic.positivePopularity).toArray();

        printSatisfactionAndSongList((int) concertLengthLeft,
                songLengths,
                maxPopularity,
                songLengths.length,
                index,
                musicList);
        float[] satisfaction = calculatedSatisfaction(musicList, participantsPreference);
        System.out.println("\nobtained satisfaction:" + satisfaction[0]);
        System.out.println("obtained dissatisfaction:" + satisfaction[1]);
    }

    public static void SetAdditionalInfo(ArrayList<ListOfAllMusic> musicList, int whatLeft) {
        int maxPositivePopularity = musicList.stream().mapToInt(listElement -> listElement.maxPopularity).max().getAsInt();
        for (ListOfAllMusic listOfAllMusic : musicList) {
            listOfAllMusic.maxPopularity = listOfAllMusic.maxPopularity * 100 / maxPositivePopularity;
            listOfAllMusic.howManyFlag = whatLeft;
            listOfAllMusic.positivePopularity = listOfAllMusic.maxPopularity;
            listOfAllMusic.negativePopularity = 100 - listOfAllMusic.maxPopularity;
        }
    }

    public static int MusicSumLength(ArrayList<ListOfAllMusic> musicList) {
        int lengthSum = 0;
        for (ListOfAllMusic listOfAllMusic : musicList) {
            lengthSum += listOfAllMusic.length;
        }
        return lengthSum;
    }

    static void printSatisfactionAndSongList(int concertLength,
                                             int[] songLengths,
                                             int[] songPopularity,
                                             int howManySongs,
                                             int[] index,
                                             ArrayList<ListOfAllMusic> musicList) {

        int[][] weight = new int[howManySongs + 1][concertLength + 1];

        for (int i = 0; i <= howManySongs; i++) {
            for (int j = 0; j <= concertLength; j++) {
                if (i == 0 || j == 0) {
                    weight[i][j] = 0;
                } else if (songLengths[i - 1] <= j) {
                    weight[i][j] = Math.max(songPopularity[i - 1] + weight[i - 1][j - songLengths[i - 1]], weight[i - 1][j]);
                } else {
                    weight[i][j] = weight[i - 1][j];
                }
            }
        }
        long popularityResult = weight[howManySongs][concertLength];
        int concertLengthTemp = concertLength;
        for (int i = howManySongs; i > 0 && popularityResult > 0; i--) {
            if (popularityResult != weight[i - 1][concertLengthTemp]) {
                musicList.get(index[i - 1] - 1).howManyFlag += 1;
                popularityResult = popularityResult - songPopularity[i - 1];
                concertLengthTemp = concertLengthTemp - songLengths[i - 1];
            }
        }
        System.out.println("\nSongs that have been played (index from list):");
        for (ListOfAllMusic listOfAllMusic : musicList) {
            if (listOfAllMusic.howManyFlag > 0)
                System.out.print(listOfAllMusic.index + "(x" + listOfAllMusic.howManyFlag + ") ");
        }
    }

    static float[] calculatedSatisfaction(ArrayList<ListOfAllMusic> musicList, ArrayList<ParticipantsData> participantsPreference) {
        float[] satisfaction = new float[2];
        int userActualPreference;
        for (ParticipantsData participantsData : participantsPreference) {
            for (ListOfAllMusic listOfAllMusic : musicList) {
                if (listOfAllMusic.genre.equals(participantsData.preference1)) {
                    userActualPreference = participantsData.valuePref1;
                } else if (listOfAllMusic.genre.equals(participantsData.preference2)) {
                    userActualPreference = participantsData.valuePref2;
                } else {
                    userActualPreference = 0;
                }
                for (int i = 0; i < listOfAllMusic.howManyFlag; i++) {
                    satisfaction[0] += (participantsData.maxWeight - userActualPreference) / 100.0;
                    satisfaction[1] += (participantsData.minWeight - userActualPreference) / 100.0;
                }
            }
        }
        return satisfaction;
    }
}



