package org.example.helpers;

public class Menus {

    public static void administrateurMenu(){
        System.out.println("1 : Ajouter un apprenant ");
        System.out.println("2 : Ajouter un Formateur ");
        System.out.println("3 : Creer Une promotion ");
        System.out.println("99 : Exit ");
    }

    public static void formateurMenu(){
        System.out.println("1 : Ajoute les apprenants a votre promotion ");
        System.out.println("2 : Creer un brief ");
        System.out.println("3 : voir toutes les briefs ");
        System.out.println("4 : distribuer un brief ");
        System.out.println("99 : Exit ");
    }

    public static void apprenantMenu(){
        System.out.println("1 : voir les briefs ");
        System.out.println("99 : Exit ");
    }
}
