#include <iostream>
#include <math.h>
#include <iomanip>
using namespace std;

double f(double x) {
  return (pow(x,3)-(3*pow(x,2))+1)/(pow(x,3));
}

double df(double x) {
  return (3*(pow(x,2))-3)/(pow(x,4));
}

int main()
{
    double x0, x1, Dx, tikslumas = 1e-6;;
    int i = 0;

    cout << "x0: ";
    cin >> x0;

    cout << " -----------------------------------------------------------------------------" << endl;
    cout << " | Niutono Metodas                                                           |" << endl;
    cout << " -----------------------------------------------------------------------------" << endl;
    cout << " | n    | Xn                   | Xn+1                 | |Xn - Xn+1|          |" << endl;
    cout << " -----------------------------------------------------------------------------" << endl;

    do {
        cout << " | ";
        cout << left << setw(4) << i << " | ";
        cout << left << setw(20) << fixed << setprecision(15) << x0 << " | ";

        x1 = x0 - f(x0)/df(x0);
        Dx = fabs(x1 - x0);
        x0 = x1;
        i++;

        cout << left << setw(20) << fixed << setprecision(15) << x1 << " | ";
        cout << left << setw(20) << fixed << setprecision(15) << Dx << " | ";
        cout << '\n';

    }   while (Dx > tikslumas);

    cout << " -----------------------------------------------------------------------------" << endl;
}
