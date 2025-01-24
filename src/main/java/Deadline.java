public class Deadline extends Task {
    private String by;

    public Deadline(String description, String type, String by) {
        super(description, type);
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
