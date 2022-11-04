package org.example.Controller.Users;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.example.Model.administrateursModel;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.example.helpers.Bufr.input;

public class Administrateur extends User{

    static administrateursModel adm = new administrateursModel();

    static List<User> userList = new ArrayList<>();
    static List<Apprenant> apprenantList = new ArrayList<>();
    static List<Formateur> formateurList = new ArrayList<>();
    static File administrateurFile = new File("administrateur.json");
    static File apprenantFile = new File("apprenant.json");
    static File formateurFile = new File("formateur.json");

    public Administrateur() {

    }
    public Administrateur(String fullName, String email, String psw) {
        super(fullName, email, psw);
    }


//    public static ArrayList<User> fill_list(File file) throws IOException {
//        ArrayList<User> list = new ArrayList<>();
//        FileReader fileReader = new FileReader(file);
//        Type type;
//        if(file.toString().equals("apprenant.json")) {
//            type = new TypeToken<ArrayList<Apprenant>>(){}.getType();
//        }else {
//            type = new TypeToken<ArrayList<Formateur>>(){}.getType();
//        }
//        //Type type = new TypeToken<ArrayList<Administrateur>>(){}.getType();
//        Gson gson = new Gson();
//        list = gson.fromJson(fileReader, type);
//        fileReader.close();
//
//        return list;
//    }
//    public static JsonArray fill(File file) throws IOException {
//        FileReader fileReader = new FileReader(file);
//
//        Gson gson = new Gson();
//        JsonArray j = (JsonArray) gson.fromJson(fileReader, JsonElement.class);
//        fileReader.close();
//
//        return j;
//    }
//    public static ArrayList<Formateur> fill_list_for_formateur(File file) throws IOException {
//        ArrayList<Formateur> list = new ArrayList<>();
//        FileReader fileReader = new FileReader(file);
//        Type type = new TypeToken<ArrayList<Formateur>>(){}.getType();
//        Gson gson = new Gson();
//        list = gson.fromJson(fileReader, type);
//        fileReader.close();
//
//        return list;
//    }
//    public static ArrayList<Apprenant> fill_list_for_apprenant(File file) throws IOException {
//        ArrayList<Apprenant> list = new ArrayList<>();
//        FileReader fileReader = new FileReader(file);
//        Type type = new TypeToken<ArrayList<Apprenant>>(){}.getType();
//        Gson gson = new Gson();
//        list = gson.fromJson(fileReader, type);
//        System.out.println(type);
//        System.out.println(JsonElement.class);
//        fileReader.close();
//
//        return list;
//    }
//    public static void add_elemment_to_file(File file, String Name, String Email, String Password, String promotionName) throws IOException {
//        userList = fill_list(file);
//        if(file.toString().equals("apprenant.json")) {
//            userList.add(new Apprenant(Name, Email, Password, promotionName));
//        }else {
//            userList.add(new Formateur(Name, Email, Password, promotionName));
//        }
//        FileWriter fileWriter = new FileWriter(file);
//        Gson gson = new Gson();
//        gson.toJson(userList, fileWriter);
//        fileWriter.close();
//    }
//    public static void delete_element_from_file(int index, File file) throws IOException {
//        userList = fill_list(file);
//        userList.remove(index);
//        FileWriter fileWriter = new FileWriter(file);
//        Gson gson = new Gson();
//        gson.toJson(userList, fileWriter);
//        fileWriter.close();
//    }
//    public static void add_elemment_to_formateur_file(File file, String Name, String Email, String Password) throws IOException {
//        formateurList = fill_list_for_formateur(file);
//
//        formateurList.add(new Formateur(Name, Email, Password, ""));
//        FileWriter fileWriter = new FileWriter(file);
//        Gson gson = new Gson();
//        gson.toJson(formateurList, fileWriter);
//        fileWriter.close();
//    }
//    public static void add_elemments_to_apprenant_file(File file, String Name, String Email, String Password) throws IOException {
//        apprenantList = fill_list_for_apprenant(file);
//        apprenantList.add(new Apprenant(Name, Email, Password, ""));
//        FileWriter fileWriter = new FileWriter(file);
//        Gson gson = new Gson();
//        gson.toJson(apprenantList, fileWriter);
//        fileWriter.close();
//    }
    public static void get_formateur_dosnt_have_promotion() throws IOException {
        formateurList = adm.fill_list_for_formateur(formateurFile);
//        formateurList = adm.fill_list_for_formateur(formateurFile);
        //System.out.println(formateurList);
        for (Formateur formateur : formateurList) {
            if(formateur.getPromotionName().equals("")){
                System.out.println(formateurList.indexOf(formateur) + " : " + formateur.getFullName());
            }
        }
    }

    @Override
    public User login(String email, String psw) throws IOException {
        userList = adm.fill_list(administrateurFile);
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPsw().equals(psw))
                return user;
        }
        return null;
    }

    public void addApprenant() throws IOException {
        System.out.println("Add Apprenante \n");
        System.out.println("Full Name : ");
        String apprenantName = input.readLine();
        System.out.println("email : ");
        String apprenantEmail = input.readLine();
        System.out.println("Password : ");
        String apprenantPassword = input.readLine();
        String promotionName = "";


        adm.add_elemment_to_file(apprenantFile, apprenantName, apprenantEmail, apprenantPassword, promotionName);
        //add_elemments_to_apprenant_file(apprenantFile, apprenantName, apprenantEmail, apprenantPassword);
    }
    public void addFormateur() throws IOException {
        System.out.println("Add Formateur \n");
        System.out.println("Full Name : ");
        String formateurName = input.readLine();
        System.out.println("email : ");
        String formateurEmail = input.readLine();
        System.out.println("Password : ");
        String formateurPassword = input.readLine();
        String promotionName = "";

        //add_elemment_to_formateur_file(formateurFile, formateurName, formateurEmail, formateurPassword);
        adm.add_elemment_to_file(formateurFile, formateurName, formateurEmail, formateurPassword, promotionName);
    }
    public void CreerPromotion() throws IOException {
        System.out.println("Creer Une promotion \n");
        System.out.println("afficter un formateur a cette promotion : ");
        get_formateur_dosnt_have_promotion();
        Integer formateurIndex = Integer.parseInt(input.readLine());
        System.out.println("donner un nom pour cette promotion");
        String promotionName = input.readLine();

        adm.add_elemment_to_file(formateurFile, formateurList.get(formateurIndex).getFullName(), formateurList.get(formateurIndex).getEmail(), formateurList.get(formateurIndex).getPsw(), promotionName);
        adm.delete_element_from_file(formateurIndex, formateurFile);

//        test01

//        System.out.println(formateurList.get(formateurIndex).getFullName() + " : " +formateurList.get(formateurIndex).getPromotionName() + " : ");
//        formateurList.get(formateurIndex).setPromotionName(promotionName);
//        System.out.println(formateurList.get(formateurIndex).getFullName() + " : " +formateurList.get(formateurIndex).getPromotionName() + " : ");
//
//        test02
//        JsonArray j = adm.fill(formateurFile);
//        JsonObject jo = j.get(formateurIndex).getAsJsonObject();
//        System.out.println(jo);
//        jo.addProperty("promotionName", promotionName);
//        System.out.println(jo);



    }






}
