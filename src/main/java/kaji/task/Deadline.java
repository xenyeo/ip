package kaji.task;

import kaji.KajiException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a kaji.task.Deadline task with a specific date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a kaji.task.Deadline object with the specified type, completion status, description, and deadline date-time.
     *
     * @param type The type of the task.
     * @param isDone The completion status of the task.
     * @param description The description of the task.
     * @param by The deadline date-time in yyyy-MM-ddTHH:mm format.
     */
    public Deadline(String type, Boolean isDone, String description, String by) throws KajiException {
        super(type, isDone, description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new KajiException("Invalid date & time format");
        }
    }

    public LocalDateTime getDeadline() {
        return by;
    }

    /**
     * Returns a string representation of the kaji.task.Deadline object.
     *
     * @return A string representation of the kaji.task.Deadline object.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
