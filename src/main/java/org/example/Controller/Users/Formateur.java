package org.example.Controller.Users;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.Controller.Brief;
import org.example.Controller.Promotion;
import org.example.DAO.useDao;
import org.example.Model.administrateursModel;
import org.example.Model.formateursModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.example.Controller.Users.Administrateur.*;
import static org.example.helpers.Bufr.input;
import static org.example.helpers.sendEmail.send_email;


public class Formateur extends User{

    administrateursModel adm = new administrateursModel();
    formateursModel frm = new formateursModel();

    static ArrayList<User> userList;
    static ArrayList<Brief> briefList;


    static File formateurFile = new File("formateur.json");
    static File briefFile = new File("brief.json");

    public Promotion promotion;
    public String promotionName;
    //ArrayList<Apprenant> apprenantList = new ArrayList<>();



    public Formateur(String fullName, String email, String psw, String promotionName) {
        super(fullName, email, psw);
         this.promotionName = promotionName;
    }

    public Formateur() {
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }


    @Override
    public User login(String email, String psw) throws IOException {
        userList = adm.fill_list(formateurFile);
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPsw().equals(psw)) return user;
        }
        return null;
    }

    public List<Apprenant> get_apprenants_dosnt_have_promotion() throws IOException {
        List<Apprenant> list = new ArrayList<>();
        apprenantList = adm.fill_list_for_apprenant(apprenantFile);
        for (Apprenant apprenant : apprenantList) {
            if(apprenant.getPromotionName().equals("")){
                System.out.println(apprenantList.indexOf(apprenant) + " : " + apprenant.getFullName());
                list.add(apprenant);
            }
        }
        return list;
    }
    public String get_promotion_name(String formateurName) throws IOException {
        formateurList = adm.fill_list_for_formateur(formateurFile);
        for (Formateur formateur : formateurList) {
            if (formateur.getFullName().equals(formateurName)) return formateur.getPromotionName();
        }
        return "";
    }
    public void add_apprenants_to_promotion(String formateurName) throws IOException {
        List<Apprenant> list = new ArrayList<>();
        System.out.println("ajouter des apprenants a votre promotion");
        int apprenantIndex = 1;

        System.out.println("taber le nombre d'apprenant que vous voulez ajouter");
        System.out.println("taber 0 pour STOP");
        list = get_apprenants_dosnt_have_promotion();
        apprenantIndex = Integer.parseInt(input.readLine());

        while (list.size() != 0 && apprenantIndex != 0){
            String promotionName = get_promotion_name(formateurName);
            adm.add_elemment_to_file(apprenantFile, apprenantList.get(apprenantIndex).getFullName(), apprenantList.get(apprenantIndex).getEmail(), apprenantList.get(apprenantIndex).getPsw(), promotionName);
            adm.delete_element_from_file(apprenantIndex, apprenantFile);

            System.out.println("taber le nombre d'apprenant que vous voulez ajouter");
            System.out.println("taber 0 pour STOP");
            list = get_apprenants_dosnt_have_promotion();
            apprenantIndex = Integer.parseInt(input.readLine());
        }
    }

//    public static ArrayList<Brief> fill_list_of_brief(File file) throws IOException {
//        ArrayList<Brief> list = new ArrayList<>();
//        FileReader fileReader = new FileReader(file);
//        Type type = new TypeToken<ArrayList<Brief>>(){}.getType();
//        Gson gson = new Gson();
//        list = gson.fromJson(fileReader, type);
//        fileReader.close();
//        return list;
//    }
//    public static void add_brief_to_file(File file, StringBuilder body,String briefName, String formateurName, Integer deadline, boolean destrubier, String promotionName) throws IOException {
//
//        briefList = fill_list_of_brief(briefFile);
//        briefList.add(new Brief(briefName, body, deadline, formateurName, destrubier, promotionName));
//        FileWriter fileWriter = new FileWriter(file);
//        Gson gson = new Gson();
//        gson.toJson(briefList, fileWriter);
//        fileWriter.close();
//    }
//    public static void delete_brief_from_file(int index) throws IOException {
//        briefList = fill_list_of_brief(briefFile);
//        briefList.remove(index);
//        FileWriter fileWriter = new FileWriter(briefFile);
//        Gson gson = new Gson();
//        gson.toJson(briefList, fileWriter);
//        fileWriter.close();
//    }
    public void creerBrief(String formateurName) throws IOException {
        //body
        StringBuilder stringBuilder = new StringBuilder("*************************************************\t\t");
        System.out.println("donner le titre du Brief");
        String briefName = input.readLine();
        stringBuilder.append(briefName);
        stringBuilder.append("\t\t*************************************************\n");
        System.out.println(stringBuilder.toString());
        System.out.println("donner la descreption du brief");
        System.out.println("taber fin pour terminer");
        String line = input.readLine();
        while (!line.equals("fin")){
            stringBuilder.append(line);
            stringBuilder.append("\n");
            line = input.readLine();
        }
        stringBuilder.append("**********************************************************************************************************************");
        System.out.println("entrer le nombres du jours du deadline");
        Integer deadline = Integer.parseInt(input.readLine());
        //add to our database
        String promotionName = get_promotion_name(formateurName);
        frm.add_brief_to_file(briefFile, stringBuilder,briefName, formateurName, deadline, false, promotionName);
    }

    public void get_all_briefs(String formateurName) throws IOException {
        briefList = frm.fill_list_of_brief(briefFile);
        for (Brief brief : briefList) {
            if(brief.getFormateurName().equals(formateurName)){
                System.out.println(briefList.indexOf(brief) + " : " + brief.getBriefName());
            }
        }
        System.out.println("choisir un nombre pour voir le contenu du brief");
        Integer choix = Integer.parseInt(input.readLine());
        System.out.println(briefList.get(choix).getBriefBody().toString());
    }

    public void destribierBrief(String formateurName) throws IOException {
        briefList = frm.fill_list_of_brief(briefFile);
        for (Brief brief : briefList) {
            if(brief.getFormateurName().equals(formateurName) && !brief.getDestrubier()){
                System.out.println(briefList.indexOf(brief) + " : " + brief.getBriefName());
            }
        }
        String promotionName = get_promotion_name(formateurName);
        System.out.println("choisir le nombre du brief que vous voulez distribier");
        Integer briefIndex = Integer.parseInt(input.readLine());
        frm.add_brief_to_file(briefFile, briefList.get(briefIndex).getBriefBody(), briefList.get(briefIndex).getBriefName(), formateurName, briefList.get(briefIndex).getDeadline(),true, promotionName);
        frm.delete_brief_from_file(briefIndex);
        send_email();
    }



}
