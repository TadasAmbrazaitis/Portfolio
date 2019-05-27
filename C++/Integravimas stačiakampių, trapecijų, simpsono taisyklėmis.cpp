#include <iostream>
#include <math.h>
#include <iomanip>
#include <cmath>
using namespace std;

double staciakampiu(double a, double b, int n);
double trapeciju(double a, double b, int n);
double simpsono(double a, double b, int n);

    // Funkcija
double func(double x)
{
    return log(x);
}

int main()
{
    // a - apatinis limitas, b - virsutinis limitas, n - tasku skaicius
    double a=1;
    double b=4;
    int n=8;

    // Isvedimas
    cout << " -----------------------------------------------------------------------------" << endl;
    cout << " | Integravimas                                                              |" << endl;
    cout << " -----------------------------------------------------------------------------" << endl;
    cout << " | N    | Staciakampiu         | Trapeciju            | Simpsono             |" << endl;
    cout << " -----------------------------------------------------------------------------" << endl;

    for (int i=1; i<=n; i++){
        if (i%2==0) {
            cout << " | ";
            cout << left << setw(4) << i << " | ";
            cout << left << setw(20) << fixed << setprecision(15) << staciakampiu(a,b,i) << " | ";
            cout << left << setw(20) << fixed << setprecision(15) << trapeciju(a,b,i) << " | ";
            cout << left << setw(20) << fixed << setprecision(15) << simpsono(a,b,i) << " | ";
            cout << '\n';
        }

        else {
            cout << " | ";
            cout << left << setw(4) << i << " | ";
            cout << left << setw(20) << fixed << setprecision(15) << staciakampiu(a,b,i) << " | ";
            cout << left << setw(20) << fixed << setprecision(15) << trapeciju(a,b,i) << " | ";
            cout << left << setw(23) << "-------------------- | ";
            cout << '\n';
        }
    }

    cout << " -----------------------------------------------------------------------------" << endl;
    cout << " | Paklaidos pagal runges taisykle                                           |" << endl;
    cout << " -----------------------------------------------------------------------------" << endl;
    cout << " | N    | Staciakampiu         | Trapeciju            | Simpsono             |" << endl;
    cout << " -----------------------------------------------------------------------------" << endl;

    int nr=1;
    for (int i=1; i<=n; i=i*2){

    // Paklaidu pagal runges taisykle skaiciavimas
        double pakStaciakampiu = abs(staciakampiu(a,b,2*i)-staciakampiu(a,b,i))/3;
        double pakTrapeciju = abs(trapeciju(a,b,2*i)-trapeciju(a,b,i))/3;
        double pakSimpsono = abs(simpsono(a,b,4*i)-simpsono(a,b,2*i))/15;

        cout << " | ";
        cout << left << setw(4) << nr << " | ";
        cout << left << setw(20) << fixed << setprecision(15) << pakStaciakampiu  << " | ";
        cout << left << setw(20) << fixed << setprecision(15) << pakTrapeciju << " | ";
        if (pow(2,nr)>n) cout << left << setw(23) << "-------------------- | " << endl;
        else cout << left << setw(20) << fixed << setprecision(15) << pakSimpsono << " | " << endl;
        nr++;
    }

    cout << " -----------------------------------------------------------------------------" << endl;

    return 0;
}

// Integravimas staciakampiu taisykle
double staciakampiu(double a, double b, int n)
{
    double h = (b - a) / n;
    double x[1000], y[1000];
    for (int i=0; i<=n; i++){
        x[i] = (a + i * h + (i+1) *h)/2;
        y[i] = func(x[i]);
    }
    double rez = 0;
    for (int i=0; i<n; i++){
        rez = rez + y[i];
    }
    rez = rez * h;
    return rez;
}

// Integravimas trapeciju taisykle
double trapeciju(double a, double b, int n)
{
    double h = (b - a) / n;
    double x[1000], y[1000];
    for (int i=0; i<=n; i++){
        x[i] = a + i * h;
        y[i] = func(x[i]);
    }
    double rez = 0;
    for (int i=0; i<=n; i++){
        if (i==0 || i==n) rez = rez + y[i];
        else rez = rez + 2 * y[i];
    }
    rez = rez * (h / 2);
    return rez;
}

// Integravimas simpsono taisykle
double simpsono(double a, double b, int n)
{
    double h = (b - a) / n;
    double x[1000], y[1000];
    for (int i=0; i<=n; i++){
        x[i] = a + i * h;
        y[i] = func(x[i]);
    }
    double rez = 0;
    for (int i=0; i<=n; i++){
        if (i==0 || i==n) rez = rez + y[i];
        else if (i % 2 !=0) rez = rez + 4 * y[i];
        else rez = rez + 2 * y[i];
    }
    rez = rez * (h / 3);
    return rez;
}
