package com.demo.demo.file;

import com.demo.demo.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUser {
    public static final String USER_PATH = "D:\\TichHop\\file_excel\\customer.tsv";

    /* Write file */
    public void writeFile(User user) {
        List<User> storeUsers = new ArrayList<>();
        storeUsers.add(user);

        File file = new File(USER_PATH);
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));

            for (User u : storeUsers) {
                bufferedWriter.write(u.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /* Read file */
    public List<User> readFile() {
        List<User> users = new ArrayList<>();
        File file = new File(USER_PATH);
        BufferedReader bufferedReader = null;
        String line = null;
        String a[] = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(" , ");
                User user = new User(Long.parseLong(a[0]), String.valueOf(a[1]));
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
