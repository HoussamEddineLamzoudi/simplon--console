package org.example.Controller.Users;

import org.example.Controller.Brief;
import org.example.Controller.Promotion;
import org.example.Model.administrateursModel;
import org.example.Model.formateursModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import static org.example.Controller.Users.Administrateur.fill_list;
import static org.example.helpers.Bufr.input;

public class Apprenant extends User{

    administrateursModel adk = new administrateursModel();
    formateursModel frm = new formateursModel();


    public Promotion promotion;
    public String promotionName;

    public List<Brief> briefList = new ArrayList<>();
    static File briefFile = new File("brief.json");


    public Apprenant(String fullName, String email, String psw, String promotionName) {
        super(fullName, email, psw);
        this.promotionName = promotionName;
    }

    public Apprenant() {
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

    static List<User> userList = new ArrayList<>();
    static File apprenantFile = new File("apprenant.json");

    @Override
    public User login(String email, String psw) throws IOException {
        userList = adk.fill_list(apprenantFile);
        for (User user : userList) {
            if(user.getEmail().equals(email) && user.getPsw().equals(psw))return user;
        }
        return null;
    }
    public void get_apprenant_brief(String promotionName) throws IOException {
        briefList = frm.fill_list_of_brief(briefFile);
        for (Brief brief : briefList) {
            if(brief.getPromotionName().equals(promotionName)){
                System.out.println(briefList.indexOf(brief) + " : " + brief.getBriefName());
            }
        }
        System.out.println("choisir un nombre pour voir le contenu du brief");
        int choix = Integer.parseInt(input.readLine());
        System.out.println(briefList.get(choix).getBriefBody().toString());
    }


    public <T> void func(T el){
        List<T> l = new ArrayList<>() ;
        l.add(el);
        l.add(el);
        l.add(el);
        System.out.println(l);
    }

}
