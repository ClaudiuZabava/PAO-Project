package com.company.services;

import com.company.utilizator.Adresa;
import com.company.utilizator.Contact;
import com.company.curs.Materie;
import com.company.curs.Sala;
import com.company.utilizator.Profesor;
import com.company.utilizator.Student;
import com.company.catalog.Catalog;

import java.text.ParseException;
import java.util.*;

public class AppService
{


    private ProfesorService prof_service = ProfesorService.get_Instance();
    private StudentService student_service = StudentService.get_Instance();
    private CatalogService catalog_service = CatalogService.get_Instance();

    private static AppService instance;

    private Scanner scanner = new Scanner(System.in);

    private AppService(){}

    public static AppService get_Instance(){
        if(instance == null){
            instance = new AppService();
        }
        return instance;
    }

    Adresa citeste_adresa(){
        Adresa adresa = new Adresa();

        System.out.println("Tara:");
        adresa.set_tara(scanner.nextLine());

        System.out.println("Judet:");
        adresa.set_judet(scanner.nextLine());

        System.out.println("Oras:");
        adresa.set_oras(scanner.nextLine());

        System.out.println("Strada:");
        adresa.set_strada(scanner.nextLine());

        return adresa;
    }

    Contact citeste_contact(){
        Contact info = new Contact();

        System.out.println("Email personal:");
        info.set_email1(scanner.nextLine());

        System.out.println("Email institutional:");
        info.set_email2(scanner.nextLine());

        System.out.println("Nr de telefon:");
        int nrt;
        try {
            nrt = scanner.nextInt();
        }
        catch(Exception e){
            System.out.println("Te rog tasteaza un nr de telefon format doar din cifre");
            scanner.nextLine();
            nrt = scanner.nextInt();
        }
        info.set_tel(nrt);
        scanner.nextLine();

        return info;
    }
    Sala citeste_sala()
    {
        Sala sl = new Sala();
        System.out.println("Codul salii de curs:");
        sl.set_Cod(scanner.nextLine());

        System.out.println("Etajul salii:");
        int et;
        try {
            et = scanner.nextInt();
        }
        catch(Exception e){
            System.out.println("Te rog tasteaza un etaj valid");
            scanner.nextLine();
            et = scanner.nextInt();
        }
        sl.set_Etaj(et);
        scanner.nextLine();

        return sl;

    }

    Materie citeste_materie(){
        Materie info = new Materie();

        System.out.println("Denumirea materiei:");
        info.set_Denumire(scanner.nextLine());

        System.out.println("Nota studentului la aceasta materie (cu 2 zecimale) :");
        double notas;
        try {
            notas = scanner.nextDouble();
        }
        catch(Exception e){
            System.out.println("Te rog tasteaza o nota valida");
            scanner.nextLine();
            notas = scanner.nextDouble();
        }
        info.set_Nota_student(notas);
        scanner.nextLine();

        info.set_Sala(citeste_sala());

        return info;
    }

    Profesor citire_profesor(){
        Profesor prof = new Profesor();
        System.out.println("Introdu Id profesor nou:");
        int id;
        try {
            id = scanner.nextInt();
        }
        catch(Exception e){
            System.out.println("Te rog tasteaza un id de profesor valid");
            scanner.nextLine();
            id = scanner.nextInt();
        }
        prof.set_Id(id);
        scanner.nextLine();

        System.out.println("Introdu numele Profesorului:");
        prof.set_Nume(scanner.nextLine());

        System.out.println("Introdu pozitia ocupata de profesor:");
        prof.set_Rank(scanner.nextLine());

        System.out.println("Introdu materia predata de profesor:");
        Materie mat = citeste_materie();
        prof.set_Materie(mat);

        System.out.println("Introdu datele de Contact ale profesorului:");
        Contact info = citeste_contact();
        prof.set_Contact(info);

        System.out.println("Introdu adresa:");
        Adresa adresa = citeste_adresa();
        prof.set_Adresa(adresa);


        return prof;
    }

    Student citire_student(){
        Student student = new Student();
        System.out.println("Introdu Id student nou:");
        int id;
        try {
            id = scanner.nextInt();
        }
        catch(Exception e){
            System.out.println("Te rog tasteaza un id de student valid");
            scanner.nextLine();
            id = scanner.nextInt();
        }
        student.set_Id(id);
        scanner.nextLine();

        System.out.println("Introdu Numele Studentului:");
        student.set_Nume(scanner.nextLine());

        System.out.println("Introdu anul curent de studiu:");
        int an;
        try {
            an = scanner.nextInt();
        }
        catch(Exception e){
            System.out.println("Te rog tasteaza un an valid");
            scanner.nextLine();
            an = scanner.nextInt();
        }
        student.set_An(an);
        scanner.nextLine();

        System.out.println("Introdu specializarea studentului:");
        student.set_Specializare(scanner.nextLine());

        System.out.println("Introdu datele de Contact:");
        Contact info = citeste_contact();
        student.set_Contact(info);

        System.out.println("Introdu adresa:");
        Adresa adresa = citeste_adresa();
        student.set_Adresa(adresa);


        return student;
    }

    public void meniu_student() {
        while(true)
        {
            afis_meniu(0);
            int option=0;
            try
            {
                option = scanner.nextInt();
            }
            catch(Exception e){
                System.out.println("Te rog tasteaza un id de optiune valid");
                scanner.nextLine();
                option = scanner.nextInt();
            }
            if (option == 0)
            {
                for (int i = 0; i < student_service.get_Studenti().size(); ++i)
                {
                    student_service.get_Studenti().get(i).Date_Utilizator();
                }
            }
            else if (option == 1)
            {
                int id;
                System.out.println("ID student:");
                try {
                    id = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("Te rog tasteaza un id de student valid");
                    scanner.nextLine();
                    id = scanner.nextInt();
                }
                if(student_service.get_Student_id(id) == null)
                {
                    System.out.println("Oops! Studentul cu ID-ul specificat nu exista");
                }
                else{
                    student_service.get_Student_id(id).Date_Utilizator();
                }

            }
            else if (option == 2)
            {
                Student student = citire_student();
                student_service.student_Add(student);
            }
            else if (option == 3)
            {
                int id;
                System.out.println("ID student:");
                try {
                    id = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("Te rog tasteaza un id de student valid");
                    scanner.nextLine();
                    id = scanner.nextInt();
                }
                if(student_service.check_existence(id) == 1)
                {
                    Student student = citire_student();
                    student_service.student_Update(id, student);
                }
                else
                {
                    System.out.println("Studentul cu ID-ul specificat nu a fost gasit");
                }

            }
            else if (option == 4)
            {
                int id;
                System.out.println("ID student:");
                try {
                    id = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("Te rog tasteaza un id de student valid");
                    scanner.nextLine();
                    id = scanner.nextInt();
                }
                student_service.student_Remove_id(id);
            }
            else if (option == 5)
            {
                break;
            }
            else {
                System.out.println("Aceasta optiune nu exista");
            }
        }
    }


    public void meniu_profesor() {
        while(true)
        {
            afis_meniu(1);
            int option;
            try {
                option = scanner.nextInt();
            }
            catch(Exception e){
                System.out.println("Te rog tasteaza un id de optiune valid");
                scanner.nextLine();
                option = scanner.nextInt();
            }
            if (option == 0)
            {
                for (int i = 0; i < prof_service.get_Profesori().size(); ++i)
                {
                    prof_service.get_Profesori().get(i).Date_Utilizator();
                }
            }
            else if (option == 1)
            {
                int id;
                System.out.println("ID profesor:");
                try {
                    id = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("Te rog tasteaza un id de profesor valid");
                    scanner.nextLine();
                    id = scanner.nextInt();
                }
                if(prof_service.get_Profesor_id(id) == null)
                {
                    System.out.println("Oops! Profesorul cu ID-ul specificat nu exista");
                }
                else
                {
                    prof_service.get_Profesor_id(id).Date_Utilizator();
                }

            }
            else if (option == 2)
            {
                Profesor prof = citire_profesor();
                prof_service.profesor_Add(prof);
            }
            else if (option == 3)
            {
                int id;
                System.out.println("ID profesor:");
                try {
                    id = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("Te rog tasteaza un id de profesor valid");
                    scanner.nextLine();
                    id = scanner.nextInt();
                }
                if(prof_service.check_existence(id) == 1)
                {
                    Profesor prof = citire_profesor();
                    prof_service.profesor_Update(id, prof);
                }
                else
                {
                    System.out.println("Profesorul cu ID-ul specificat nu a fost gasit");
                }

            }
            else if (option == 4)
            {
                int id;
                System.out.println("ID profesor:");
                try {
                    id = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("Te rog tasteaza un id de profesor valid");
                    scanner.nextLine();
                    id = scanner.nextInt();
                }
                prof_service.profesor_Remove_id(id);
            }
            else if (option == 5)
            {
                break;
            }
            else {
                System.out.println("Aceasta optiune nu exista");
            }
        }
    }
    Catalog citire_catalog() throws ParseException {
        System.out.println("Id fila catalog:");
        Catalog cat = new Catalog();
        int id;
        try {
            id = scanner.nextInt();
        }
        catch(Exception e){
            System.out.println("Te rog tasteaza un id valid");
            scanner.nextLine();
            id = scanner.nextInt();
        }
        cat.set_Id(id);
        scanner.nextLine();

        System.out.println("Datele studentului:");
        Student st1 = citire_student();
        cat.set_Studenti(st1);

        System.out.println("Numarul de materii ale studentului:");
        int nr;
        try {
            nr = scanner.nextInt();
        }
        catch(Exception e){
            System.out.println("Te rog tasteaza un nr valid");
            scanner.nextLine();
            nr = scanner.nextInt();
        }
        scanner.nextLine();



        System.out.println("Introduceti profesorii si materiile predate:");
        HashSet<Profesor> h = new HashSet<>();
        for(int i = 0; i < nr; ++i)
        {
            Profesor profesor = citire_profesor();
            h.add(profesor);
        }

        cat.set_Profesori(h);

        return cat;
    }

    public void meniu_catalog() throws ParseException {
        while(true)
        {
            afis_meniu(2);
            int option;
            try {
                option = scanner.nextInt();
            }
            catch(Exception e){
                System.out.println("Te rog tasteaza un id de optiune valid");
                scanner.nextLine();
                option = scanner.nextInt();
            }
            if (option == 0) {
                for (int i = 0; i < catalog_service.get_File().size(); ++i)
                {
                    catalog_service.get_File().get(i).get_Studenti().Date_Utilizator();

                    for(int j = 0; j < catalog_service.get_File().get(i).get_Profesori().size(); ++j)
                    {
                        catalog_service.get_File().get(i).get_Profesori().forEach(prof ->
                        {
                                ((Profesor) prof).Date_Utilizator();
                        });
                    }
                }
            }
            else if (option == 1)
            {
                int id;
                System.out.println("ID fila:");
                try {
                    id = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("Te rog tasteaza un id valid");
                    scanner.nextLine();
                    id = scanner.nextInt();
                }
                scanner.nextLine();
                if(catalog_service.get_Fila_id(id) == null)
                {
                    System.out.println("Oops! Fila cu ID-ul specificat nu exista");
                }
                else
                {
                    catalog_service.get_Fila_id(id).get_Studenti().Date_Utilizator();

                    for(int j = 0; j < catalog_service.get_Fila_id(id).get_Profesori().size(); ++j)
                    {
                        catalog_service.get_Fila_id(id).get_Profesori().forEach(prof ->
                        {
                            ((Profesor) prof).Date_Utilizator();
                        });
                    }
                }


            }
            else if (option == 2)
            {
                Catalog cat = citire_catalog();
                catalog_service.adauga_Fila(cat);
            }
            else if (option == 3)
            {
                int id;
                System.out.println("ID fila:");
                try {
                    id = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("Te rog tasteaza un id valid");
                    scanner.nextLine();
                    id = scanner.nextInt();
                }
                scanner.nextLine();
                if(catalog_service.check_existence(id) == 1)
                {
                    Catalog cat = citire_catalog();
                    catalog_service.catalog_Update(id, cat);
                }
                else
                {
                    System.out.println("Fila de catalog cu ID-ul specificat nu a fost gasita");
                }
            }
            else if (option == 4)
            {
                int id;
                System.out.println("ID fila:");
                try {
                    id = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("Te rog tasteaza un id valid");
                    scanner.nextLine();
                    id = scanner.nextInt();
                }
                scanner.nextLine();
                catalog_service.sterge_Fila_id(id);
            }
            else if (option == 5)
            {
                System.out.println("Specifica Id-ul studentului:");
                int id;
                try {
                    id = scanner.nextInt();
                }
                catch(Exception e){
                    System.out.println("Te rog tasteaza un id de student valid");
                    scanner.nextLine();
                    id = scanner.nextInt();
                }
                int k=-1;
                scanner.nextLine();

                for (int i = 0; i < catalog_service.get_File().size(); ++i)
                {

                    if( catalog_service.get_File().get(i).get_Studenti().get_Id() == id)
                    {
                        k=i;
                        break;
                    }
                }
                if(k!=-1)
                {
                    List<Double> mar = new ArrayList<>();

                    catalog_service.get_File().get(k).get_Profesori().forEach(prof ->
                    {
                        mar.add(((Profesor) prof).get_Materie().get_Nota_student());

                    });

                    System.out.println("Media studentului pe acest an = " + (mar.stream().reduce(0.00, Double::sum)/mar.size()));
                }
                else
                {
                    System.out.println("Studentul cu ID-ul specificat nu exista");
                }
            }
            else if(option == 6)
            {
                break;
            }
            else {
                System.out.println("Aceasta optiune nu exista");
            }
        }
    }

    public void afis_meniu_utilizatori(){
        System.out.println(" 0 - Profesor");
        System.out.println(" 1 - Student");
        System.out.println(" 2 - Inapoi");
    }
    public void afis_meniu(int x)
    {
        if(x==0)
        {
            System.out.println(" 0 - Afisare Studenti");
            System.out.println(" 1 - Afisare Student dupa ID");
            System.out.println(" 2 - Adauga Student");
            System.out.println(" 3 - Actualizeaza Date Student");
            System.out.println(" 4 - Sterge Studentul dupa ID");
            System.out.println(" 5 - Inapoi");

        }
        else if(x==1)
        {
            System.out.println(" 0 - Afisare Profesori");
            System.out.println(" 1 - Afisare Profesor dupa ID");
            System.out.println(" 2 - Adauga Profesor");
            System.out.println(" 3 - Actualizeaza Datele unui Profesorului ");
            System.out.println(" 4 - Sterge Profesorul dupa ID");
            System.out.println(" 5 - Inapoi");
        }
        else if(x==2)
        {
            System.out.println(" 0 - Afisare catalog");
            System.out.println(" 1 - Afiseaza doar o anumita fila din catalog");
            System.out.println(" 2 - Adauga date in catalog");
            System.out.println(" 3 - Actualizeaza catalog");
            System.out.println(" 4 - Sterge o fila din catalog");
            System.out.println(" 5 - Media Notelor Studentului x");
            System.out.println(" 6 - Inapoi");
        }
    }

    public void afis_meniu_general(){
        System.out.println(" 0 - Meniu Principal");
        System.out.println(" 1 - Catalog");
        System.out.println(" 2 - Utilizatori");
        System.out.println(" 3 - Iesire");

    }

    public void meniu_utilizator(){
        while(true){
            afis_meniu_utilizatori();
            int option = scanner.nextInt();
            if (option == 0) {
                meniu_profesor();
            } else if (option == 1) {
                meniu_student();
            } else if (option == 2) {
                break;
            }
            else {
                System.out.println("Aceasta optiune nu exista");
            }
        }
    }

    public void meniu() throws ParseException {
        while(true){
            afis_meniu_general();
            int option;
            try {
                option = scanner.nextInt();
            }
            catch(Exception e){
                System.out.println("Te rog tasteaza un id de optiune valid");
                option = scanner.nextInt();
            }
            if(option == 0)
            {
                continue;
            }
            else if(option == 1)
            {
                meniu_catalog();
            }
            else if(option == 2)
            {
                meniu_utilizator();
            }
            else if(option == 3)
            {
                break;
            }
            else {
                System.out.println("Aceasta optiune nu exista");
            }
        }
    }

}
