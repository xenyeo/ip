public class Deadline extends Task {
    protected String by;

    public Deadline(String type, Boolean isDone, String description, String by) {
        super(type, isDone, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
