package Lab_3.Zadanie_1;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVConverter {


    public static void main(String[] args) throws IOException {
        String inputFileName = args[0];
        String inputSeparator = args[1];
        String outputFileName = args[2];
        String outputSeparator = args[3];

        String regexStr= ".$";
        Pattern pattern = Pattern.compile(regexStr);
        boolean find = false;

        try {


            Matcher matcher = pattern.matcher(inputSeparator);
            find = matcher.matches();
            matcher.group();
            matcher = pattern.matcher(outputSeparator);
            find = matcher.matches();
            matcher.group();

        } catch (IllegalStateException e) {
            System.out.println("Separator może być tylko jednym znakiem.");
            return ;
        }

        String path = "C:\\Users\\User\\IdeaProjects\\OPA_laby\\OPA22Z_Poplawska_310320\\src\\Lab_3\\Zadanie_1\\";

        CSV file = new CSV();
        file.read(path + inputFileName, inputSeparator);
        System.out.println(file);
        System.out.println(file.getValue(2, 3));
        file.write(path + outputFileName, outputSeparator);


    }

}
