// Finding and printing the minimum and maximum values of each variable type
// Language: C++

#include <iostream>
#include <string>
#include <limits>
using namespace std;

template <class type>
class dataInfo {
private:
    string maxPrintString = "The maximum limit of ", minPrintString = "The minimum limit of ", endPrintString = " data type: ";
    string typeName;
    type minValue = 0, maxValue = 0;

    // Print out the output
    void print() {
        if (maxValue == 0) {
            cout << "ERROR";
            exit;
        }
        else if (minValue == 0) {
            cout << maxPrintString << typeName << endPrintString << maxValue << endl;
        }
        else {
            cout << maxPrintString << typeName << endPrintString << maxValue << endl;
            cout << minPrintString << typeName << endPrintString << minValue << endl;
        }
    }
    void dataCorrection() {
        if (typeName == "__int64") {
            typeName = "long";
        }
        if (typeName == "unsigned __int64") {
            typeName = "unsigned long";
        }

        int character_minValue = 0;
        int character_maxValue = 0;
        if (typeName == "char" || typeName == "signed char" || typeName == "unsigned char") {
            character_maxValue = numeric_limits<type>::max();
            character_minValue = numeric_limits<type>::min();

            if (typeName == "char") {
                cout << "The Bits contain in char data type: " << CHAR_BIT << endl;
            }

            if (minValue == 0) {
                cout << maxPrintString << typeName << endPrintString << character_maxValue << endl;
            }
            else {
                cout << maxPrintString << typeName << endPrintString << character_maxValue << endl;
                cout << minPrintString << typeName << endPrintString << character_minValue << endl;
            }
        }
    }

public:
    void findValues(type num) {
        maxValue = numeric_limits<type>::max();
        minValue = numeric_limits<type>::min();
        typeName = typeid(type).name();

        dataCorrection();
        if (typeName == "char" || typeName == "signed char" || typeName == "unsigned char") {}
        else {
            print();
        }
    }
};

void initialize() {
    // Original integer: meant to pass in type. Variables had to be initialized or else error
    // Create a dataInfo object the does all the printing and computation
    // Call the function to initiate all the calculations

    int integer = 0;
    dataInfo<int> integer_Size;
    integer_Size.findValues(integer);

    unsigned int unsignedInt = 0;
    dataInfo<unsigned int> unsignedInt_Size;
    unsignedInt_Size.findValues(unsignedInt);

    long long longLong = 0;
    dataInfo<long long> longLong_Size;
    longLong_Size.findValues(longLong);

    unsigned long long unsignedLongLong = 0;
    dataInfo<unsigned long long> unsignedLongLong_Size;
    unsignedLongLong_Size.findValues(unsignedLongLong);

    char character = 0;
    dataInfo<char> character_Size;
    character_Size.findValues(character);

    signed char signedChar = 0;
    dataInfo<signed char> signedChar_Size;
    signedChar_Size.findValues(signedChar);

    unsigned char unsignedChar = 0;
    dataInfo<unsigned char> unsignedChar_Size;
    unsignedChar_Size.findValues(unsignedChar);

    short shortNumber = 0;
    dataInfo<short> shortNumber_Size;
    shortNumber_Size.findValues(shortNumber);

    unsigned short unsignedShort = 0;
    dataInfo<unsigned short> unsignedShort_Size;
    unsignedShort_Size.findValues(unsignedShort);
}


int main() {
    initialize();
}