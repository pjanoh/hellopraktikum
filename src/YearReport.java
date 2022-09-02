import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearReport {
    public ArrayList<YearlyLineRecord> records = new ArrayList<>();

    public YearReport(String path) {
        String content = readFileContentsOrNull(path); // \n
        String[] lines = content.split("\r?\n"); //
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i]; // "02,14000,true"
            String[] parts = line.split(","); // ["02", "14000", "true"]
            int month = Integer.parseInt(parts[0]); // "02" -> 2
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            YearlyLineRecord record = new YearlyLineRecord(month, amount, isExpense);
            records.add(record);
        }
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

}
