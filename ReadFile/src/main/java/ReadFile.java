import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReadFile {
    public static final String USER_PATH = "D:\\TichHop\\file_excel\\customer.tsv";

    public static void writeFile(List<User> listUsers) {
        File file = new File(USER_PATH);
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));

            for(User user: listUsers) {
                bufferedWriter.write(user.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
