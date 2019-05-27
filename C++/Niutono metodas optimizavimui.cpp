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

double ddf(double x) { // teigiamas = minimumas, neigiamas = maksimumas
  return -((6*pow(x,2)-12)/pow(x,5));
}

int main()
{
    double x0, x1, Dx, tikslumas = 1e-6;;
    int i = 0;

    cout << "x0: ";
    cin >> x0;

    cout << " -----------------------------------------------------------------------------" << endl;
    cout << " | Niutono Metodas Optimizavimui                                             |" << endl;
    cout << " -----------------------------------------------------------------------------" << endl;
    cout << " | n    | Xn         ||Xn - Xn+1| |" << endl;
    cout << " -----------------------------------------------------------------------------" << endl;

    do {
        cout << " | ";
        cout << left << setw(4) << i << " | ";
        cout << left << setw(10) << fixed << setprecision(5) << x0 << " | ";

        x1 = x0 - df(x0)/ddf(x0);
        Dx = fabs(x1 - x0);
        x0 = x1;
        i++;

        cout << left << setw(10) << fixed << setprecision(5) << Dx << " | ";
        cout << '\n';

    }   while (Dx > tikslumas);

    cout << " -----------------------------------------------------------------------------" << endl;
}
