package org.example.Model;


import com.google.gson.reflect.TypeToken;
import org.example.Controller.Users.Apprenant;
import org.example.Controller.Users.Formateur;
import org.example.Controller.Users.User;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;

import java.io.*;


public class administrateursModel {


    static List<User> userList = new ArrayList<>();
    static ArrayList<User> apprenantList = new ArrayList<User>();
    static ArrayList<User> formateurList = new ArrayList<User>();
    static File administrateurFile = new File("administrateur.json");
    static File apprenantFile = new File("apprenant.json");
    static File formateurFile = new File("formateur.json");



        public ArrayList<User> fill_list(File file) throws IOException {
        ArrayList<User> list = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        Type type;
        if(file.toString().equals("apprenant.json")) {
            type = new TypeToken<ArrayList<Apprenant>>(){}.getType();
        }else {
            type = new TypeToken<ArrayList<Formateur>>(){}.getType();
        }
        Gson gson = new Gson();
        list = gson.fromJson(fileReader, type);
        fileReader.close();

        return list;
    }

//    public  JsonArray fill(File file) throws IOException {
//        FileReader fileReader = new FileReader(file);
//
//        Gson gson = new Gson();
//        JsonArray j = (JsonArray) gson.fromJson(fileReader, JsonElement.class);
//        fileReader.close();
//
//        return j;
//    }
    public  ArrayList<Formateur> fill_list_for_formateur(File file) throws IOException {
        ArrayList<Formateur> list = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        Type type = new TypeToken<ArrayList<Formateur>>(){}.getType();
        Gson gson = new Gson();
        list = gson.fromJson(fileReader, type);
        fileReader.close();

        return list;
    }
    public  ArrayList<Apprenant> fill_list_for_apprenant(File file) throws IOException {
        ArrayList<Apprenant> list = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        Type type = new TypeToken<ArrayList<Apprenant>>(){}.getType();
        Gson gson = new Gson();
        list = gson.fromJson(fileReader, type);
        fileReader.close();

        return list;
    }
    public void add_elemment_to_file(File file, String Name, String Email, String Password, String promotionName) throws IOException {
        userList = fill_list(file);
        System.out.println(userList);
        if(file.toString().equals("apprenant.json")) {
            userList.add(new Apprenant(Name, Email, Password, promotionName));
        }else {
            userList.add(new Formateur(Name, Email, Password, promotionName));
        }
        System.out.println(userList);

        FileWriter fileWriter = new FileWriter(file);
        Gson gson = new Gson();
        gson.toJson(userList, fileWriter);
        fileWriter.close();
    }
    public  void delete_element_from_file(int index, File file) throws IOException {
        userList = fill_list(file);
        userList.remove(index);
        FileWriter fileWriter = new FileWriter(file);
        Gson gson = new Gson();
        gson.toJson(userList, fileWriter);
        fileWriter.close();
    }

    public  void add_elemment_to_formateur_file(File file, String Name, String Email, String Password) throws IOException {
        formateurList = fill_list(file);
//        formateurList = fill_list_for_formateur(file);

        formateurList.add(new Formateur(Name, Email, Password, ""));
        FileWriter fileWriter = new FileWriter(file);
        Gson gson = new Gson();
        gson.toJson(formateurList, fileWriter);
        fileWriter.close();
    }

    public  void add_elemments_to_apprenant_file(File file, String Name, String Email, String Password) throws IOException {
        apprenantList = fill_list(file);
        apprenantList.add(new Apprenant(Name, Email, Password, ""));
        FileWriter fileWriter = new FileWriter(file);
        Gson gson = new Gson();
        gson.toJson(apprenantList, fileWriter);
        fileWriter.close();
    }

}