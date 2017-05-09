import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {


        HashMap<String, ArrayList<String>> keysSongs = new HashMap<>();
        ArrayList<String> keysAuthors = new ArrayList<>();
        HashMap<String, HashMap<String, ArrayList<String>>> songs = new HashMap<>();

        File dir = new File("/home/sidney/IdeaProjects/Learn/src/songs");//init dir which all songs
        File[] dirAuthors = dir.listFiles();//Get dirs which songs

        for(File author: dirAuthors) {

            File[] fileSongs = author.listFiles();
            HashMap<String, ArrayList<String>> song = new HashMap<>();
            ArrayList<String> nameSongs = new ArrayList<>();

            for (File fileSong : fileSongs) {
                ArrayList<String> stringsSong = new ArrayList<>();
                try {
                    //Объект для чтения файла в буфер
                    BufferedReader in = new BufferedReader(new FileReader(fileSong.getAbsoluteFile()));
                    try {
                        //В цикле построчно считываем файл
                        String s;
                        while (true) {
                            if ((s = in.readLine()) != null) {
                                stringsSong.add(s);
                            } else break;
                        }
                        song.put(fileSong.getName(), stringsSong);
                        nameSongs.add(fileSong.getName());
                    } finally {
                        //Также не забываем закрыть файл
                        in.close();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            songs.put(author.getName(), song);
            keysSongs.put(author.getName(), nameSongs);
            keysAuthors.add(author.getName());
        }

        for(int i = 0; i < keysAuthors.size(); i++){
            System.out.println((i+1) + ": " + keysAuthors.get(i));
        }
        int num = 0;
        while (true) {
            System.out.print(">> ");
            Scanner input = new Scanner(System.in);
            String str = input.next();
            try {
                num = Integer.parseInt(str);
                if ((num<1)||(num>keysAuthors.size()))
                    throw new IndexOutOfBoundsException();
                break;
            } catch (NumberFormatException e) {
                int count = 0;
                boolean check = true;
                for (int i = 0; i < keysAuthors.size(); i++) {
                    if((keysAuthors.get(i).toLowerCase()).startsWith(str.toLowerCase())){
                        count++;
                        check = false;
                        num = i+1;
                    }
                }
                if (check){
                    System.out.println("Try again");
                    continue;
                }
                else
                    if(count==1){
                        break;
                    }
                    else{
                        System.out.println("Try again");
                        continue;
                    }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Try again");
                continue;
            }
        }
        String author = keysAuthors.get(num - 1);
        if (keysSongs.get(author).size() == 0)
            System.out.println("net pesen");
        else {

            for (int i = 0; i < keysSongs.get(author).size(); i++)
                System.out.println(keysSongs.get(author).get(i));
        }

        num = 0;
        while (true) {
            System.out.print(">> ");
            Scanner input = new Scanner(System.in);
            String str = input.next();
            //HashMap<String, HashMap<String, ArrayList<String>>> songs = new HashMap<>();
            try {
                num = Integer.parseInt(str);
                if ((num<1)||(num>songs.get(author).size()))//author
                    throw new IndexOutOfBoundsException();
                break;
            } catch (NumberFormatException e) {
                int count = 0;
                boolean check = true;
                for (int i = 0; i < songs.get(author).size(); i++) {
                    if((keysSongs.get(author).get(i).toLowerCase()).startsWith(str.toLowerCase())){
                        count++;
                        check = false;
                        num = i+1;
                    }
                }
                if (check){
                    System.out.println("Try again");
                    continue;
                }
                else
                if(count==1){
                    break;
                }
                else{
                    System.out.println("Try again");
                    continue;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Try again");
                continue;
            }
        }

        for (int i = 0; i < songs.get(author).get(keysSongs.get(author).get(num-1)).size(); i++)
            System.out.println(songs.get(author).get(keysSongs.get(author).get(num-1)).get(i));
    }
}
