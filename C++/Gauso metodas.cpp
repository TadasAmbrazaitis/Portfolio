#include<iostream>
#include<cmath>

using namespace std;

int main() {
    int i,j,k,n;
    float x[10],a[10][10],c[10];
    cout<<"Iveskite n \n";
    cin>>n;
    cout<<"\nIveskite lygciu reiksmes : \n";
    for(i=0;i<n;i++) {
        cin>>c[i];
    }
    cout<<"\nIveskite matrica : \n";
    for(i=0;i<n;i++) {
        for(j=0;j<n;j++) {
            cin>>a[i][j];
        }
    }
    for(k=0;k<n-1;k++) {
        for(i=k+1;i<n;i++) {
            for(j=k+1;j<n;j++) {
                a[i][j]=a[i][j]-(a[i][k]/a[k][k])*a[k][j];
            }
            c[i]=c[i]-(a[i][k]/a[k][k])*c[k];
        }
    }
    x[n-1]=c[n-1]/a[n-1][n-1];
    cout<<"\nSprendimas yra : \n";
    cout<<"x["<<n-1<<"]="<<x[n-1]<<"\n";
    for(k=0;k<n-1;k++) {
        i=n-k-2;
        for(j=i+1;j<n;j++) {
            c[i]=c[i]-(a[i][j]*x[j]);
        }
        x[i]=c[i]/a[i][i];
        cout<<"x["<<i<<"]="<<x[i]<<"\n";
    }
    return 0;
}
