// Opening, Writing, Reading, and Closing Files
// Language: C++


#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main()
{
    fstream inputFile1("TestFile.txt", ios::in);
    string s1;
    
    inputFile1 == s1.getline();
    cout << s1;

    //file1.open("TestFile.txt")

    inputFile1.close();
}