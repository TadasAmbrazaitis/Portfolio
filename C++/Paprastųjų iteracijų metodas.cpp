#include <iostream>
#include <math.h>
#include <iomanip>
using namespace std;

double f(double x)
{
	return atan(x) + 3.1415926535897;
}

int main()
{
	double x0, x1, Dx, tikslumas = 1e-8;
	int i = 0;
	cout << "Iveskite x0";
	cin >> x0;
	cout << " | Paprastuju iteraciju metodas                                              |" << endl;
	cout << " | n    | Xn+1                 | Xn+2                 | |Xn+2 - Xn+1|        |" << endl;
	do {
		cout << " | ";
		cout << setw(4) << i << " | ";
		cout << setw(20) << fixed << setprecision(15) << x0 << " | ";

		x1 = f(x0);
		Dx = fabs(x1 - x0);
		x0 = x1;
		i++;

		cout << setw(20) << fixed << setprecision(15) << x1 << " | ";
		cout << setw(20) << fixed << setprecision(15) << Dx << " | " << endl;
	} while (Dx > tikslumas);
}
