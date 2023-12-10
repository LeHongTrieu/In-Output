import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    /**
     * Hàm xóa file.
     */
    public static void clearFileContent(String path) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm đọc file.
     */
    public static String readContentFromFile(String path) {
        StringBuilder s = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = read.readLine()) != null) {
                s.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s.toString();
    }

    /**
     * Hàm ghi file.
     */
    public static void writeContentToFile(String path) {
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedWriter write = null;
        Scanner sc = null;

        try {
            write = new BufferedWriter(new FileWriter(path, true));
            sc = new Scanner(System.in);
            String writeIn = sc.nextLine();
            boolean check = true;
            for (String s : lines) {
                if (writeIn.equals(s)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                write.write(writeIn);
            } else {
                clearFileContent(path);
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).equals(writeIn)) {
                        lines.remove(i);
                        i--;
                    }
                }
                lines.add(writeIn);
                write.write(lines.get(0));
                for (int i = 1; i < lines.size(); i++) {
                    write.write("\n");
                    write.write(lines.get(i));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (write != null) {
                    write.close();
                }
                if (sc != null) {
                    sc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Hàm ghi file.
     */
    public static void appendContentToFile(String path) {
        BufferedWriter write = null;
        Scanner sc = null;

        try {
            write = new BufferedWriter(new FileWriter(path, true));
            sc = new Scanner(System.in);
            String writeIn = sc.nextLine();
            write.write("\n");
            write.write(writeIn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (write != null) {
                    write.close();
                }
                if (sc != null) {
                    sc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Hàm tìm file.
     */
    public static File findFileByName(String folderPath, String fileName) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().equals(fileName)) {
                    return file;
                }
            }
        }
        return null;
    }
}
