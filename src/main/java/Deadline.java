import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task{
    private String by;

    /**
     * Constructor for Deadline tasks
     *
     * @param description description of task /by specific date or time
     */
    public Deadline(String description) {
        super(description.split("/by", -1)[0].trim());
        String[] arr = description.split("/by", -1);
        if (arr[0].length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYDEADLINE);
        } else if (arr.length <= 1) {
            throw new AilurusException(AilurusException.Error.BY);
        } else if (arr[1].length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYBY);
        } else {
            this.by = arr[1].trim();
        }
    }

    /**
     * Write data to log task
     *
     * @param writer file writer for writing data to file
     */
    @Override
    public void log(FileWriter writer) {
        try {
            writer.write(String.format("D|%d|%s|%s\n", this.isDone ? 1 : 0, this.description, this.by));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
