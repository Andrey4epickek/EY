import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Data
public class Stroka {

    private LocalDate date;
    private String latinChars;
    private String rusChars;
    private int evenNumber;
    private double materialNumber;

    public static Stroka createStroka(){//Функция создания строки
        Stroka stroka=new Stroka();
        stroka.setDate(DateFile());
        stroka.setLatinChars(latinChars());
        stroka.setRusChars(rusChars());
        stroka.setEvenNumber(evenNumber());
        stroka.setMaterialNumber(materialNumber());
        return stroka;
     }

    public static LocalDate DateFile(){//Генерация даты
        LocalDate startDate = LocalDate.of(2016, 1, 1); //start date
        long start = startDate.toEpochDay();

        LocalDate endDate = LocalDate.now();
        long end = endDate.toEpochDay();

        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
        return LocalDate.ofEpochDay(randomEpochDay);
    }

    public static String latinChars(){//Генерация символов латинского алфавита
        String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String random = new Random().ints(10, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
        return random;
    }

    public static String rusChars(){//Генерация символов русского алфавита
        String symbols = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        String random = new Random().ints(10, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
        return random;
    }

    public static int evenNumber(){//Генерация случайного четного числа
        int evenNumberGen= (int) (1+Math.random()*99999999);
        if(evenNumberGen%2==1){
            evenNumberGen++;
        }
        return evenNumberGen;
    }

    public static double materialNumber(){//Генерация случайного числа с 8 знаками после запятой
        double materialNumberGen= (1+Math.random()*19);
        double scale = Math.pow(10, 8);
        double result = Math.ceil(materialNumberGen * scale) / scale;
        return result;
    }

    @Override
    public String toString() {
        return "" + date +
                "//" + latinChars +
                "//" + rusChars +
                "//" + evenNumber +
                "//" + materialNumber ;
    }
}
