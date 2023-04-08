package Lab_3.Zadanie_1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class CSV {
    List<String[]> csv = new ArrayList<>();

    String csv_str;

    @Override
    public String toString() {
        csv_str = "CSV{\n";
        for (String[] mline : csv) {
            for (String mcolumn : mline) {
                csv_str += mcolumn;
                csv_str += " ";
            }
            csv_str += "\n";
        }

        csv_str += '}';
        return csv_str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CSV csv1)) return false;
        return Objects.equals(csv, csv1.csv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(csv);
    }

    public void read(String path, String separator) throws IOException {

        String line;
        String[] columns;
        FileReader fr = null;
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((line = br.readLine()) != null) {
                columns = line.split(Pattern.quote(separator));
                this.csv.add(columns);
                i++;
            }
        } catch (Exception e) {
            System.out.println("Nie odnaleziono podanego pliku wejściowego.");
            System.exit(0);
        }

    }

    String answer;
    public String getValue(int row, int column) {
        row--;
        column--;
        try{
            String[] columns = csv.get(row);
            this.answer = columns[column];
        }
        catch(IndexOutOfBoundsException e ){
            System.out.println("Podany wiersz i kolumna nie zawierają żadenj danej");
            return "";
        }


        return "at row " + row + " at column " + column + ": " + answer;
    }

    public void write(String path, String separator) {
        String line = "";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {

            for (String[] mline : csv) {

                line = "";
                for (String mcolumn : mline) {
                    line += mcolumn += separator;
                }
                line = line.substring(0, line.length() - 1);
                line += "\n";
                bw.write(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
