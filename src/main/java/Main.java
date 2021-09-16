import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {

    static String dir = "/Users/Andrey/Desktop/files/";//Путь к директории, где должны создаваться файлы
    static String ext = ".txt";
    static int i = 0;

    public static void create(){//Создание файла
        String finName = dir + "ey"+i + ext;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(finName));
            for(int j=0;j<1;j++) {//Количество строк в файле
                out.write(Stroka.createStroka().toString());
                out.newLine();
            }
                out.close();
            System.out.println("File made");
        } catch (IOException e) {

            System.out.println("Didn't work");
        }
    }

    public static void unite() throws IOException {//Объединение файлов в один
        File dir = new File("C:\\Users/Andrey/Desktop/files/");
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start","unite.bat");
        pb.directory(dir);
        Process p = pb.start();
        System.out.println("Merge successful");
        delete();
    }

    public static void delete() throws IOException {//Функция удаления подстрок в строках итогового файла
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter a characters to remove lines from a file");
        String deleteLine=scanner.nextLine();
        String line;
        int count=0;

        File sourceFile = new File("C:\\Users/Andrey/Desktop/files/itog.txt");
        File outputFile = new File("C:\\Users/Andrey/Desktop/files/itogFormat.txt");

        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        while ((line = reader.readLine()) != null) {
            if (!line.contains(deleteLine)) {
                writer.write(line);
                writer.newLine();
            }
            else {
                count++;
            }
        }
        reader.close();
        writer.close();
        sourceFile.delete();
        outputFile.renameTo(sourceFile);
        System.out.println("Deleted lines: "+count);
    }

    public static void importDb(){//Импорт данных из файла в бд
        try{
            String url = "jdbc:mysql://127.0.0.1:3306/EY?autoReconnect=true&useSSL=false&serverTimezone=UTC";
            String username = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            int imorted=0;
            int notImported=10;
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                System.out.println("Connection to EY DB succesfull!");

                String line;
                File sourceFile = new File("C:\\Users/Andrey/Desktop/files/itog.txt");

                BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
                while ((line = reader.readLine()) != null) {
                    String delimeter = "//";
                    String [] subStr;
                    subStr=line.split(delimeter);
                    String date=subStr[0];
                    String latinChars=subStr[1];
                    String rusChars=subStr[2];
                    String evenNumber=subStr[3];
                    String materialNumber=subStr[4];
                    PreparedStatement preparedStatement=conn.prepareStatement("INSERT INTO info(dateEY,latinChars,rusChars,evenNumber,materialNumber) VALUES (?,?,?,?,?)");
                    preparedStatement.setDate(1,Date.valueOf(date));
                    preparedStatement.setString(2,latinChars);
                    preparedStatement.setString(3,rusChars);
                    preparedStatement.setInt(4, Integer.parseInt(evenNumber));
                    preparedStatement.setDouble(5, Double.parseDouble(materialNumber));
                    preparedStatement.executeUpdate();
                    imorted++;
                    notImported--;
                    System.out.println(imorted+" line/lines imported");
                    System.out.println(notImported+" lines left\n");
                }
                reader.close();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    private static void printMenu(){//Отрисовка консольного меню
        System.out.println("Menu----");
        System.out.println("1-Create files");
        System.out.println("2-Unite files into one");
        System.out.println("3-Import into DB");
        System.out.println("4-Exit");
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        int choice;
        do{
            printMenu();
            System.out.println("Enter your choice");
            choice=scanner.nextInt();
            switch (choice){
                case 1:
                    int x = 1;

                    while(x <= 10){//Количество генерируемых файлов
                        i++;
                        Main.create();
                        x++;
                    }
                    break;
                case 2:
                    unite();
                    break;
                case 3:
                    importDb();
                    break;
            }
        }while (choice!=4);
    }
}
