#include <iostream>
#include <stdio.h>
#include <math.h>
#include <iomanip>
using namespace std;

double f(double x) {
    return pow(x,3)-2-x;
}

int main()
{
    double a, b, c, fa, fb, fc, tikslumas = 1e-2;
    int i = 0;

    cout << "a: ";
    cin >> a;
    cout << "b: ";
    cin >> b;

    cout << " --------------------------------------------------------------------------------" << endl;
    cout << " | Pusiaukirtos Metodas                                                         |" << endl;
    cout << " --------------------------------------------------------------------------------" << endl;
    cout << " | n    | An              | Bn              | Cn              | (Bn - An)/2     |" << endl;
    cout << " --------------------------------------------------------------------------------" << endl;

    do {
        cout << " | ";
        cout << left << setw(4) << i << " | ";
        cout << left << setw(15) << fixed << setprecision(10) << a << " | ";
        cout << left << setw(15) << fixed << setprecision(10) << b << " | ";

        c = (a+b) / 2;
        if (f(a) * f(c) < 0) b = c;
        else a = c;
        i++;

        cout << left << setw(15) << fixed << setprecision(10) << c << " | ";
        cout << left << setw(15) << fixed << setprecision(10) << (fabs(b-a))/2 << " | ";
        cout << '\n';
    }   while ((fabs(b-a))/2 > tikslumas);

    cout << " --------------------------------------------------------------------------------" << endl;
}
