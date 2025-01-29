public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String type, String start, String end) {
        super(description, type);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
