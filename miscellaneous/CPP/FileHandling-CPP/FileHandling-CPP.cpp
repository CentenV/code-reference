// Opening, Writing, Reading, and Closing Files
// Language: C++


#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main()
{
    ofstream aFile("test.txt");
    aFile << "rolls royce";
    aFile.close();

    string str;
    ifstream a2File("test.txt");
    while (getline(a2File, str));
    {
        cout << str;
    }
    
}