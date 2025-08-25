package utils;

import model.Event;
import model.EventCategory;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "events.data";

    public static void saveEvents(List<Event> events) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Event e : events) {
                writer.write(e.getName() + ";" + e.getAddress() + ";" + e.getCategory() + ";" + e.getDateTime() + ";" + e.getDescription());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Event> loadEvents() {
        List<Event> events = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return events;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Event e = new Event(
                        parts[0],
                        parts[1],
                        EventCategory.valueOf(parts[2]),
                        LocalDateTime.parse(parts[3]),
                        parts[4]
                );
                events.add(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return events;
    }
}
