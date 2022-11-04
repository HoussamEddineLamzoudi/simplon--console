package org.example.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.Controller.Brief;
import org.example.Controller.Promotion;
import org.example.Controller.Users.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class formateursModel {

    static ArrayList<User> userList;
    static ArrayList<Brief> briefList;
    static File formateurFile = new File("formateur.json");
    static File briefFile = new File("brief.json");


    public ArrayList<Brief> fill_list_of_brief(File file) throws IOException {
        ArrayList<Brief> list = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        Type type = new TypeToken<ArrayList<Brief>>(){}.getType();
        Gson gson = new Gson();
        list = gson.fromJson(fileReader, type);
        fileReader.close();
        return list;
    }
    public void add_brief_to_file(File file, StringBuilder body,String briefName, String formateurName, Integer deadline, boolean destrubier, String promotionName) throws IOException {

        briefList = fill_list_of_brief(briefFile);
        briefList.add(new Brief(briefName, body, deadline, formateurName, destrubier, promotionName));
        FileWriter fileWriter = new FileWriter(file);
        Gson gson = new Gson();
        gson.toJson(briefList, fileWriter);
        fileWriter.close();
    }
    public void delete_brief_from_file(int index) throws IOException {
        briefList = fill_list_of_brief(briefFile);
        briefList.remove(index);
        FileWriter fileWriter = new FileWriter(briefFile);
        Gson gson = new Gson();
        gson.toJson(briefList, fileWriter);
        fileWriter.close();
    }

}
