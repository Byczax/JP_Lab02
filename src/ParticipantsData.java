public class ParticipantsData {
    int index;
    String preference1;
    int valuePref1;
    String preference2;
    int valuePref2;
    int maxWeight;
    int minWeight;

    public ParticipantsData(int index, String preference1, int valuePref1, String preference2, int valuePref2) {
        this.index = index;
        this.preference1 = preference1;
        this.valuePref1 = valuePref1;
        this.preference2 = preference2;
        this.valuePref2 = valuePref2;
        setWeight();
    }

    private void setWeight() {
        if (valuePref1 > valuePref2) {
            maxWeight = valuePref1;
            minWeight = valuePref2;
        } else {
            maxWeight = valuePref2;
            minWeight = valuePref1;
        }
    }
}
