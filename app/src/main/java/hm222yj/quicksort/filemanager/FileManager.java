package hm222yj.quicksort.filemanager;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager {

    // Create file
    public boolean createFileIfFileIsMissing(String path) throws Exception {
        Path pathToFile = Path.of(path);
        if (Files.exists(pathToFile)) {
            System.out.println("File alreayd exists, no need to create new one");
            return false;
        }
        Files.createFile(pathToFile);
        System.out.println("Created file: " + pathToFile);
        return true;
    }

    // Write to file CSV-HEADERS
    public void writeCSVheaders(String path, String text) throws Exception {
        FileWriter fileWriter = new FileWriter(path, true);
        fileWriter.write(text + System.lineSeparator());
        fileWriter.close();
        System.out.println("Wrote to " + path + " with the text:" + text);
    }

    // Write to File
    public void writeToFile(String path, int dataSize, int unions, double averageTimeInNanoSeconds,
            double standardDeviation) throws Exception {
        Path pathToOldFile = Path.of(path);
        List<String> lines = Files.readAllLines(pathToOldFile);

        String header = lines.get(0);
        List<String> rows = new ArrayList<String>();
        boolean replaced = false;

        // Kolla efter dubbletter. 
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");
            int currentDataSize = Integer.parseInt(parts[0]);
            int currentUnions = Integer.parseInt(parts[1]);
            if (currentDataSize == dataSize && currentUnions == unions) {
                String newRow = dataSize + "," + unions + "," + averageTimeInNanoSeconds + "," + standardDeviation;
                rows.add(newRow);
                replaced = true;
            } else { 
                rows.add(line);
            }
        }

        if (!replaced) {
            String newRow = dataSize + "," + unions + "," + averageTimeInNanoSeconds + "," + standardDeviation;
            rows.add(newRow);
        }

        BufferedWriter writer = Files.newBufferedWriter(pathToOldFile, StandardOpenOption.TRUNCATE_EXISTING);
        writer.write(header);
        writer.newLine();
        for (String row : rows) {
            writer.write(row);
            writer.newLine();
        }
        writer.close();
    }

    // Overloaded Write to File
    public void writeToFile(String path,int dataSize,int minValue,int maxValue,double seconds,int combinationsFound) throws Exception {
    Path pathToFile = Path.of(path);
    List<String> lines = Files.readAllLines(pathToFile);

    String headerLine = lines.get(0);
    ArrayList<String> rows = new ArrayList<>();
    boolean wasReplaced = false;

    String newRow = dataSize + "," + minValue + "," + maxValue + ","
                  + seconds + "," + combinationsFound;

    for (int i = 1; i < lines.size(); i++) {     
        String currentLine = lines.get(i).trim();
        if (currentLine.isEmpty()) continue;

        String[] parts = currentLine.split(",", -1);
        int currentDataSize = Integer.parseInt(parts[0].trim());
        int currentMinValue = Integer.parseInt(parts[1].trim());
        int currentMaxValue = Integer.parseInt(parts[2].trim());

        if (currentDataSize == dataSize && currentMinValue == minValue && currentMaxValue == maxValue) {
            rows.add(newRow);     
            wasReplaced = true;
        } else {
            rows.add(currentLine); 
        }
    }

    if (!wasReplaced) {
        rows.add(newRow);          
    }

    BufferedWriter writer = Files.newBufferedWriter(pathToFile, StandardOpenOption.TRUNCATE_EXISTING);
    writer.write(headerLine);
    writer.newLine();
    for (String row : rows) {
        writer.write(row);
        writer.newLine();
    }
    writer.close();
}


    // Read File
    public String readFromFile(String path) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        reader.close();
        return line;
    }

    // Sorting the CSV file
    public void sortCSVfile(String path) throws Exception {
        Path pathToFile = Path.of(path);
        List<String> lines = Files.readAllLines(pathToFile);
        String header = lines.get(0);
        List<String> rows = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            rows.add(lines.get(i));
        }

        rows.sort((a, b) -> {
            String[] aa = a.split(",");
            String[] bb = b.split(",");
            int dataSizeA = Integer.parseInt(aa[0]);
            int dataSizeB = Integer.parseInt(bb[0]);
            if (dataSizeA != dataSizeB)
                return Integer.compare(dataSizeA, dataSizeB);
            int unionsA = Integer.parseInt(aa[1]);
            int unionsB = Integer.parseInt(bb[1]);
            return Integer.compare(unionsA, unionsB);
        });

        BufferedWriter writer = Files.newBufferedWriter(
                pathToFile,
                StandardOpenOption.TRUNCATE_EXISTING);
        writer.write(header);
        writer.newLine();
        for (String row : rows) {
            writer.write(row);
            writer.newLine();
        }
        writer.close();
    }

}
