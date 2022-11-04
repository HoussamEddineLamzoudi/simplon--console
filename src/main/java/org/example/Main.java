package org.example;

import org.example.Controller.Users.Administrateur;
import org.example.Controller.Users.Apprenant;
import org.example.Controller.Users.Formateur;
import org.example.Controller.Users.User;

import java.io.*;

import static org.example.helpers.Bufr.input;
import static org.example.helpers.Menus.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader inbt = new BufferedReader(new InputStreamReader(System.in));
        String platformUser;
        User user;
        int choix;
        int back = 0;

        //login for all paltform users
        do {
            back = 0;
            while (true){
                System.out.println("Entrer votre email : ");
                String userEmail = input.readLine();
                System.out.println("Entrer votre password : ");
                String userPassword = input.readLine();

                if(true){
                    Administrateur usr = new Administrateur();
                    if(usr.login(userEmail, userPassword) != null){
                        platformUser = "Administrateur";
                        user = usr.login(userEmail, userPassword);
                        break;
                    }
                }
                if(true){
                    Formateur usr = new Formateur();
                    if(usr.login(userEmail, userPassword) != null){
                        platformUser = "Formateur";
                        user = usr.login(userEmail, userPassword);
                        break;
                    }
                }
                if(true){
                    Apprenant usr = new Apprenant();
                    if(usr.login(userEmail, userPassword) != null){
                        platformUser = "Apprenant";
                        user = usr.login(userEmail, userPassword);
                        break;
                    }else{
                        System.out.println("email ou password est incorrect ");
                    }
                }
            }

            // Affichier le menu for the exact user

            //Administrateur
            if(platformUser.equals("Administrateur")){

                System.out.println("\n Binevenu " + user.getFullName() + " ^-^ \n");
                Administrateur administrateur = new Administrateur();
                do {
                    administrateurMenu();
                    choix = Integer.parseInt(input.readLine());
                    switch (choix){
                        //add apprenant
                        case 1 -> {
                            administrateur.addApprenant();
                            back = 2;
                            continue;
                        }
                        case 2 -> {
                            administrateur.addFormateur();
                            back = 2;
                            continue;
                        }
                        case 3 -> {
                            administrateur.CreerPromotion();
                            back = 2;
                            continue;
                        }
                        case 99 -> {
                            back = 1;
                            continue;
                        }
                        default -> System.out.println("message");
                    }
                }while (back==2);
            } else if(platformUser.equals("Formateur")){

                System.out.println("\n\n Binevenu " + user.getFullName() + " ^-^ \n\n");
                Formateur formateur = new Formateur();
                do {
                    formateurMenu();
                    choix = Integer.parseInt(input.readLine());
                    switch (choix){
                        case 1 -> {
                            formateur.add_apprenants_to_promotion(user.getFullName());
                            back = 2;
                            continue;
                        }
                        case 2 -> {
                            formateur.creerBrief(user.getFullName());
                            back = 2;
                            continue;
                        }
                        case 3 -> {
                            formateur.get_all_briefs(user.getFullName());
                            back = 2;
                            continue;
                        }
                        case 4 -> {
                            formateur.destribierBrief(user.getFullName());
                            back = 2;
                            continue;
                        }
                        case 99 -> {
                            back = 1;
                            continue;
                        }
                    }
                }while (back==2);

            } else {

                System.out.println("\n\n Binevenu " + user.getFullName() + " ^-^ \n\n");
                Apprenant apprenant = new Apprenant();
                do {
                    apprenantMenu();
                    choix = Integer.parseInt(input.readLine());
                    switch (choix){
                        case 1 -> {
                            apprenant.get_apprenant_brief("java2");
                            back = 2;
                            continue;
                        }
                        case 2 -> {
                            apprenant.func(new Apprenant("imad","imad","imad",""));
                            apprenant.func(12);
                            apprenant.func("oop");
                            back = 2;
                            continue;
                        }
                        case 99 -> {
                            back = 1;
                            continue;
                        }
                    }
                }while (back==2);
            }
        } while (back==1);


        System.out.println("wa5dam wasafi");
    }
}