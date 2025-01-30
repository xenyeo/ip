package kaji.task;

/**
 * Represents a task with a type, completion status, and description.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String type, boolean isDone, String description) {
        this.type = type;
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Returns the status icon representing whether the task is completed.
     *
     * @return A string representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed by setting its status to done.
     */
    public void markTask() {
        isDone = true;
    }

    /**
     * Marks the task as uncompleted by setting its status to not done.
     */
    public void unmarkTask() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The task's description as a string.
     */
    @Override
    public String toString() {
        return "[" + type + "][" + getStatusIcon() + "] " + description;
    }
}
