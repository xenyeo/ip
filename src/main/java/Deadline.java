import Exceptions.InvalidDateException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String type, Boolean isDone, String description, String by) throws InvalidDateException {
        super(type, isDone, description);
        String validPattern = "\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4}";
        if (by.matches(validPattern)) {
            this.by = by;
        } else {
            throw new InvalidDateException("Invalid date format");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + TaskList.convertDateTimeFormat(by) + ")";
    }
}
