import java.util.*;

public class Solver {

    public static void SetAdditionalInfo(ArrayList<ListOfAllMusic> musicList, int whatLeft) {
        int maxPositivePopularity = musicList.stream().mapToInt(listElement -> listElement.maxPopularity).max().getAsInt();
        int minPositivePopularity = musicList.stream().mapToInt(listElement -> listElement.maxPopularity).min().getAsInt();
        for (ListOfAllMusic listOfAllMusic : musicList) {
            listOfAllMusic.howManyFlag = whatLeft;
            listOfAllMusic.positivePopularity = listOfAllMusic.maxPopularity - minPositivePopularity;
            listOfAllMusic.negativePopularity = maxPositivePopularity - listOfAllMusic.maxPopularity;
        }
    }

    public static int MusicSumLength(ArrayList<ListOfAllMusic> musicList) {
        int lengthSum = 0;
        for (ListOfAllMusic listOfAllMusic : musicList) {
            lengthSum += listOfAllMusic.length;
        }
        return lengthSum;
    }

    public static void BestSolution(ArrayList<ListOfAllMusic> musicList, int concertLength) {
        int[] songLengths = musicList.stream().mapToInt(listOfAllMusic -> listOfAllMusic.length).toArray();
//        int[] songPopularity = musicList.stream().mapToInt(listOfAllMusic -> listOfAllMusic.maxPopularity).toArray();
        int[] index = musicList.stream().mapToInt(listOfAllMusic -> listOfAllMusic.index).toArray();


        int lengthSum = MusicSumLength(musicList);
        int concertLengthLeft = concertLength % lengthSum;
        int whatLeft = concertLength / lengthSum;
        SetAdditionalInfo(musicList, whatLeft);
        int[] maxPopularity = musicList.stream().mapToInt(listOfAllMusic -> listOfAllMusic.positivePopularity).toArray();
        int[] minPopularity = musicList.stream().mapToInt(listOfAllMusic -> listOfAllMusic.negativePopularity).toArray();

        System.out.println("\nMaximum Satisfaction:");
        printSatisfactionAndSongList(concertLengthLeft, songLengths, maxPopularity, songLengths.length, index, musicList);
        System.out.println("\n\nMinimal Satisfaction:");
        printSatisfactionAndSongList(concertLengthLeft, songLengths, minPopularity, songLengths.length, index, musicList);
    }

    static void printSatisfactionAndSongList(int concertLength,
                                             int[] songLengths,
                                             int[] songPopularity,
                                             int howManySongs,
                                             int[] index,
                                             ArrayList<ListOfAllMusic> musicList) {
        int i, w;
        int[][] K = new int[howManySongs + 1][concertLength + 1];

        for (i = 0; i <= howManySongs; i++) {
            for (w = 0; w <= concertLength; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (songLengths[i - 1] <= w)
                    K[i][w] = Math.max(songPopularity[i - 1] +
                            K[i - 1][w - songLengths[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }
        // stores the result of Knapsack
        int PopularityResult = K[howManySongs][concertLength];
        System.out.println("uzyskane zadowolenie:" + PopularityResult);
        w = concertLength;
        for (i = howManySongs; i > 0 && PopularityResult > 0; i--) {
            // either the result comes from the top
            // (K[i-1][w]) or from (songPopularity[i-1] + K[i-1]
            // [w-songLengths[i-1]]) as in Knapsack table. If
            // it comes from the latter one/ it means
            // the item is included.
            if (PopularityResult != K[i - 1][w]) {
                // This item is included.
                musicList.get(index[i - 1] - 1).howManyFlag += 1;
                // Since this weight is included its
                // value is deducted
                PopularityResult = PopularityResult - songPopularity[i - 1];
                w = w - songLengths[i - 1];
            }
        }
        System.out.println("\nSongs that have been played:");
        for (ListOfAllMusic listOfAllMusic : musicList) {
            if (listOfAllMusic.howManyFlag > 0)
                System.out.print(listOfAllMusic.index + " (x" + listOfAllMusic.howManyFlag + ") ");
        }
    }
}



