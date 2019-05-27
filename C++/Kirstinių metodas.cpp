#include <iostream>
#include <math.h>
#include <iomanip>
using namespace std;

double f(double x) {
    return x * sin(x) - 1;
}

int main()
{
    double x0, x1, x2, Dx, tikslumas = 1e-6;
    int i = 0;

    cout << "x0: ";
    cin >> x0;
    cout << "x1: ";
    cin >> x1;

    cout << " -----------------------------------------------------------------------------" << endl;
    cout << " | Kirstiniu Metodas                                                         |" << endl;
    cout << " -----------------------------------------------------------------------------" << endl;
    cout << " | n    | Xn+1                 | Xn+2                 | |Xn+2 - Xn+1|        |" << endl;
    cout << " -----------------------------------------------------------------------------" << endl;

    do {
        cout << " | ";
        cout << left << setw(4) << i << " | ";
        cout << left << setw(20) << fixed << setprecision(15) << x1 << " | ";

        x2 = (x0 * f(x1) - x1 * f(x0)) / (f(x1) - f(x0));
        Dx = fabs(x2 - x1);
        x0 = x1;
        x1 = x2;
        i++;

        cout << left << setw(20) << fixed << setprecision(15) << x2 << " | ";
        cout << left << setw(20) << fixed << setprecision(15) << Dx << " | ";
        cout << '\n';
    }   while (Dx > tikslumas);

    cout << " -----------------------------------------------------------------------------" << endl;
}
