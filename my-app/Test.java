import java.io.FileWriter;
public class Test{
    public static void main(String[] args){
        try{
            List<String> lines = Arrays.asList("The first line", "The second line");
            Path file = Paths.get("database.txt");
            Files.write(file, lines, StandardCharsets.UTF_8);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}