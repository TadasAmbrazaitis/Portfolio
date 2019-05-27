#include<iostream>
#include<cmath>

using namespace std;

int main() {
    int i,j,m,n,l;
    float x[10],a[10][10],b[10],c;
    cout<<"Iveskite n \n";
    cin>>n;
    cout<<"\nIveskite iteraciju skaiciu : \n";
    cin>>l;
    cout<<"\nIveskite lygciu reiksmes : \n";
    for(i=0;i<n;i++) {
        cin>>b[i];
    }
    cout<<"\nIveskite matrica : \n";
    for(i=0;i<n;i++) {
        x[i]=0;
        for(j=0;j<n;j++) {
            cin>>a[i][j];
        }
    }
    m=1;
    line:
    for(i=0;i<n;i++) {
        c=b[i];
        for(j=0;j<n;j++) {
            if(i!=j) {
                c=c-a[i][j]*x[j];
            }
        }
        x[i]=c/a[i][i];
    }
    m++;
    if(m<=l) {
        goto line;
    }
    else {
        cout<<"\nSprendimas yra : \n";
        for(i=0;i<n;i++) {
            cout<<"\nx("<<i<<") = "<<x[i]<<"\n";
        }
    }
}
