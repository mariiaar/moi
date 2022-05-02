package com.example.dd.retrofit2;

public class Lake {

    // Lake class, its attributes and methods

    private String Jarvi_Id, Nimi, KuntaNimi, Vesiala;


    public Lake(String Jarvi_Id, String Nimi, String KuntaNimi, String Vesiala) {
        this.Jarvi_Id = Jarvi_Id;
        this.Nimi = Nimi;
        this.KuntaNimi = KuntaNimi;
        this.Vesiala = Vesiala;
    }


    public String getJarvi_Id() {return Jarvi_Id;}

    public String getNimi() {return Nimi;}

    public String getKuntaNimi() {return KuntaNimi;}

    public String getVesiala() {return Vesiala;}
}
