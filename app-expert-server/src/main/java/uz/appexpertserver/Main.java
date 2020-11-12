package uz.appexpertserver;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Timestamp timestamp = null;
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = scanner.nextLine();
        timestamp=new Timestamp(simpleDateFormat.parse(date).getTime());
        System.out.println(timestamp);
    }
}
