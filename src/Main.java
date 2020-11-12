import java.util.*;


public class Main {

    private static ArrayList<MusicStats> ConvertToArray(HashMap<String, Integer> timeGenre,
                                                        HashMap<String, Integer> countGenre,
                                                        HashMap<String, Integer> popularGenre) {
        ArrayList<MusicStats> statList = new ArrayList<>();
        for (var entry : timeGenre.entrySet()) {
            String mGenre = entry.getKey();
            int mTime = timeGenre.get(mGenre);
            int mCount = countGenre.get(mGenre);
            int mPopular = popularGenre.get(mGenre);
            statList.add(new MusicStats(mGenre, mTime, mCount, mPopular));
        }
        return statList;
    }


    public static void main(String[] args) {

        HashMap<String, Integer> timeGenre = new HashMap<>();
        HashMap<String, Integer> countGenre = new HashMap<>();
        HashMap<String, Integer> popularGenre = new HashMap<>();
        ArrayList<MusicStats> statList;
        ArrayList<ListOfAllMusic> musicList = new ArrayList<>();

        int concertLength = DataReader.ReadDataFromUser("Podaj wartosc w minutach ile powinien trwac koncert w minutach");
        concertLength *= 60;// change to seconds
        DataReader.ReadParticipantsData("attendantsList.txt", popularGenre);// read attendants file
        DataReader.ReadMusicData("musicList.txt", timeGenre, countGenre, musicList, popularGenre);// read music file


        statList = ConvertToArray(timeGenre, countGenre, popularGenre); //converting hashmap to array
        statList.sort(Comparator.comparing(genre -> ((MusicStats) genre).popularity).reversed()); //sorting array

//        Printing lists
//        System.out.println("Gatunek;Suma Długości;Ilość;Popularność");
//        for (MusicStats musicStats : statList) {
//            System.out.println(musicStats.genre + ";" + musicStats.length + ";" + musicStats.count + ";" + musicStats.popularity);
//        }
//        System.out.println("\n");
//        for (ListOfAllMusic listOfAllMusic : musicList) {
//            System.out.println(listOfAllMusic.index + ";" + listOfAllMusic.genre + ";" + listOfAllMusic.length + ";" + listOfAllMusic.maxPopularity);
//
//        }
        Solver.BestSolution(musicList, concertLength);


    }


}
