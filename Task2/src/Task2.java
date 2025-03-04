import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2 {

    public static void main(String[] args) {
        // Указание пути к файлам
        String circleFile = "E:\\test\\task2\\Task2\\src\\file1.txt";
        String pointsFile = "E:\\test\\task2\\Task2\\src\\file2.txt";

        try {
            // Чтение данных о окружности
            Circle circle = readCircleData(circleFile);

            // Чтение данных о точках и вывод их положения относительно окружности
            try (BufferedReader pointsReader = new BufferedReader(new FileReader(pointsFile))) {
                String line;
                while ((line = pointsReader.readLine()) != null) {
                    String[] parts = line.split("\\s+");
                    if (parts.length == 2) {
                        double x = Double.parseDouble(parts[0]);
                        double y = Double.parseDouble(parts[1]);
                        int position = determinePointPosition(circle, x, y);
                        System.out.println(position);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для чтения данных о окружности из файла
    private static Circle readCircleData(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String[] centerCoords = reader.readLine().split("\\s+");
            double centerX = Double.parseDouble(centerCoords[0]);
            double centerY = Double.parseDouble(centerCoords[1]);
            double radius = Double.parseDouble(reader.readLine());
            return new Circle(centerX, centerY, radius);
        }
    }

    // Метод для определения положения точки относительно окружности
    private static int determinePointPosition(Circle circle, double x, double y) {
        double distance = Math.sqrt(Math.pow(x - circle.centerX, 2) + Math.pow(y - circle.centerY, 2));
        if (distance < circle.radius) {
            return 1; // Внутри окружности
        } else if (distance > circle.radius) {
            return 2; // Снаружи окружности
        } else {
            return 0; // На окружности
        }
    }

    // Класс для хранения данных о окружности
    private static class Circle {
        double centerX;
        double centerY;
        double radius;

        Circle(double centerX, double centerY, double radius) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.radius = radius;
        }
    }
}
