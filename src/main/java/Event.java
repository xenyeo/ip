import Exceptions.InvalidDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String type, Boolean isDone, String description, String start, String end)
            throws InvalidDateException {
        super(type, isDone, description);
        String validPattern = "\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4}";
        if (start.matches(validPattern) && end.matches(validPattern)) {
            this.start = start;
            this.end = end;
        } else {
            throw new InvalidDateException("Invalid date format");
        }
    }

    /**
     * Checks if the start and end date strings represent the same calendar date.
     *
     * @param start The start date and time in the format "d/M/yyyy HHmm".
     * @param end The end date and time in the format "d/M/yyyy HHmm".
     * @return true if the start and end dates are the same calendar date, false otherwise.
     */
    private boolean isSameDate(String start, String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);
        return startDateTime.toLocalDate().equals(endDateTime.toLocalDate());
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + TaskList.convertDateTimeFormat(start) + " to: "
                + (isSameDate(start, end) ? end.split(" ")[1] : TaskList.convertDateTimeFormat(end)) + ")";
    }
}
