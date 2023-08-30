package duke.storage;

import duke.exceptions.StorageException;
import duke.exceptions.TimeParsingException;
import duke.task.*;

import java.io.*;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws StorageException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            } catch (IOException e) {
                throw new StorageException(filePath);
            } catch (TimeParsingException e) {
                throw new RuntimeException(e);
            }
        }
        return tasks;
    }


    private Task parseTask(String line) throws TimeParsingException {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case "T":
            return new ToDo(parts[2], "1".equals(parts[1]));
        case "D":
            return new Deadline(parts[2], parts[3], "1".equals(parts[1]));
        case "E":
            String[] eventTimes = parts[3].split("--");
            return new Event(parts[2], eventTimes[0], eventTimes[1], "1".equals(parts[1]));
        default:
            return null;
        }
    }

    public void save(TaskList tasks) throws StorageException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(tasks.toStorage());
        } catch (IOException e) {
            throw new StorageException(filePath);
        }
    }

}
//    public List<Task> load() throws StorageException {
//        List<Task> tasks = new ArrayList<>();
//        File file = new File(filePath);
//
//        if (file.exists()) {
//            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    Task Duke.task = parseTask(line);
//                    if (Duke.task != null) {
//                        tasks.add(Duke.task);
//                    }
//                }
//            } catch (IOException e) {
//                throw new StorageException(filePath);
//            } catch (TimeParsingException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return tasks;
//    }
//
//
//    private Task parseTask(String line) throws TimeParsingException {
//        String[] parts = line.split(" \\| ");
//        switch (parts[0]) {
//        case "T":
//            return new ToDo(parts[2], "1".equals(parts[1]));
//        case "D":
//            return new Deadline(parts[2], parts[3], "1".equals(parts[1]));
//        case "E":
//            String[] eventTimes = parts[3].split("--");
//            return new Event(parts[2], eventTimes[0], eventTimes[1], "1".equals(parts[1]));
//        default:
//            return null;
//        }
//    }