public class Results {
    private long adding;
    private long asking;
    private long removing;

    public Results() {

    }

    public Results(long adding, long asking, long removing) {
        this.adding = adding;
        this.asking = asking;
        this.removing = removing;
    }

    public long getAdding() {
        return adding;
    }

    public long getAsking() {
        return asking;
    }

    public long getRemoving() {
        return removing;
    }

    public void setAdding(long adding) {
        this.adding = adding;
    }

    public void setAsking(long asking) {
        this.asking = asking;
    }

    public void setRemoving(long removing) {
        this.removing = removing;
    }
}
