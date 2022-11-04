package org.example.DAO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.Controller.Users.Apprenant;
import org.example.Controller.Users.Formateur;
import org.example.Controller.Users.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class useDao<T> {

     List<T> userList = new ArrayList<>();

    static public <T> ArrayList<T> fill_list(File file) throws IOException {
        ArrayList<T> list = new ArrayList<T>();
        FileReader fileReader = new FileReader(file);
        Type type = new TypeToken<ArrayList<T>>(){}.getType();
        Gson gson = new Gson();
        list = gson.fromJson(fileReader, type);
        fileReader.close();

        return list;
    }

    public void delete_element_from_file(int index, File file) throws IOException {
        userList = fill_list(file);
        userList.remove(index);
        FileWriter fileWriter = new FileWriter(file);
        Gson gson = new Gson();
        gson.toJson(userList, fileWriter);
        fileWriter.close();
    }
}
