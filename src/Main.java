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
        ArrayList<ParticipantsData> participantsPreference = new ArrayList<>();

        long concertLength = DataReader.ReadDataFromUser("Enter the value in seconds - how long the concert should last?");
//        concertLength *= 60;// change to seconds

        DataReader.ReadParticipantsData("attendantsList.txt", popularGenre, participantsPreference);// read attendants file
        DataReader.ReadMusicData("musicList.txt", timeGenre, countGenre, musicList, popularGenre);// read music file

        statList = ConvertToArray(timeGenre, countGenre, popularGenre); //converting hashmap to array
        statList.sort(Comparator.comparing(genre -> ((MusicStats) genre).popularity).reversed()); //sorting array

        Solver.BestSolution(musicList, concertLength, participantsPreference); //calculation solution
    }
}
