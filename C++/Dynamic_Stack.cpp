#include<iostream>
#include<fstream>
using namespace std;
ifstream fd("input.txt"); // Įvedimo failas
ofstream fr("output.txt"); // Išvedimo failas

//   Sukuriama mazgo struktūra
struct mazgas
{
    int elementas;
    struct mazgas *sekantis; // Rodyklė į sekantį elementą
};

// Sukuriama klasė pavadinimu stekas
class stekas
{
    struct mazgas *top; // Rodyklė į viršutinį steko elementą
    public:
    stekas() // Konstruktorius priskiria 0 pradinei steko reikšmei
    {
        top=NULL;
    }
    void skaitymas(); // Nuskaityti pradinį duomenų failą
    void push(); // Įterpti elementą
    void pop();  // Pašalinti elementą
    void parodyti(); // Parodyti steką
    void galutinis(); // Įrašyti į failą galutinį steko turinį.
};

void stekas::skaitymas()
{
    int skaicius;
    struct mazgas *rodykle;
    if (fd>>skaicius){// Jeigu įvedimo failas dar nėra pilnai nuskaitytas
    // Naujo mazgo sukūrimas ir jo elemento bei rodyklinės reikšmės nustatymas
    rodykle=new mazgas;
    rodykle->elementas=skaicius;
    rodykle->sekantis=NULL;
    if(top!=NULL) rodykle->sekantis=top;
    top=rodykle;
    }
}

// PUSH operacija
void stekas::push()
{
    int skaicius;
    struct mazgas *rodykle;
    cout<<"\nIrasykite skaiciu: ";
    cin>>skaicius;
    // Naujo mazgo sukūrimas ir jo elemento bei rodyklinės reikšmės nustatymas
    rodykle=new mazgas;
    rodykle->elementas=skaicius;
    rodykle->sekantis=NULL;
    if(top!=NULL) rodykle->sekantis=top;
    top=rodykle;
}

// POP Operacija
void stekas::pop()
{
    // Sukuriama laikina rodyklė. Viršutinis elementas perkeliamas į sekantį elementą. Pašalinama laikina rodyklė.
    struct mazgas *laikinas;
    if(top==NULL) cout<<"\nStekas tuscias";
    else{
    laikinas=top;
    top=top->sekantis;
    cout<<"\nPasalintas skaicius "<<laikinas->elementas;
    delete laikinas;
    }
}

// Parodyti steką
void stekas::parodyti()
{
    // Jeigu funkcija paleidžiama pirmą kartą, turinys išvedamas į failą. Kitus kartus išvedama į ekraną.
    struct mazgas *rodykle1=top;

    static bool pirmask = true;
    if (pirmask)
    {
        pirmask = false;
        fr<<"Pradinis stekas\n";
        while(rodykle1!=NULL)
        {
        fr<<rodykle1->elementas<<" -> ";
        rodykle1=rodykle1->sekantis;
        }
        fr<<"NULL\n";
    }

    else{
    cout<<"\nStekas yra\n";
    while(rodykle1!=NULL)
    {
        cout<<rodykle1->elementas<<" -> ";
        rodykle1=rodykle1->sekantis;
    }
    cout<<"NULL\n";}
}

void stekas::galutinis()
{
    struct mazgas *rodykle1=top;
        fr<<"Galutinis stekas\n";
        while(rodykle1!=NULL)
        {
        fr<<rodykle1->elementas<<" -> ";
        rodykle1=rodykle1->sekantis;
        }
        fr<<"NULL";
}

int main()
{
    stekas s;
    int pasirinkimas;
    while(!fd.fail() && !fd.eof()) s.skaitymas(); // Jeigu įvedimo failas egzistuoja ir dar nėra pilnai nuskaitytas
    s.parodyti(); // Į failą išvedama pradinis steko turinys
    while(1)
    {
        cout<<"\n-----------------------------------------------------------";
        cout<<"\n1:PUSH\n2:POP\n3:ISJUNGTI PROGRAMA\n";
        cout<<"-----------------------------------------------------------\n";
        cin>>pasirinkimas;
        switch(pasirinkimas)
        {
            case 1:
                s.push();
                s.parodyti();
                break;
            case 2:
                s.pop();
                s.parodyti();
                break;
            case 3:
                s.galutinis();
                return 0;
                break;
        }
    }
    return 0;
}
