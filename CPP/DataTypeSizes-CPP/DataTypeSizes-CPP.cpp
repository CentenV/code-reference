// Finding and printing the sizes of each built-in data type
// Language: C++

#include <iostream>

using namespace std;

template <typename type>
void dataSize(type var)
{
    string typeName = typeid(type).name();
    if (typeName == "__int64") 
    {
        typeName = "long";
    }
    if (typeName == "unsigned __int64") 
    {
        typeName = "unsigned long";
    }

    cout << "Size of " << typeName << ": " << sizeof(type) << ((typeName == "char") ? " byte\n" : " bytes\n");
}


void initialize()
{
    // Original integer: meant to pass in type. Variables had to be initialized or else error
    // Create a dataInfo object the does all the printing and computation
    // Calls the method to initiate all the calculations

    int integer = 0;
    dataSize<int>(integer);

    unsigned int unsignedInt = 0;
    dataSize<unsigned int>(unsignedInt);

    long long longLong = 0;
    dataSize<long long>(longLong);

    unsigned long long unsignedLongLong = 0;
    dataSize<unsigned long long>(unsignedLongLong);

    char character = 0;
    dataSize<char>(character);

    signed char signedChar = 0;
    dataSize<signed char>(signedChar);

    unsigned char unsignedChar = 0;
    dataSize<unsigned char>(unsignedChar);

    short shortNumber = 0;
    dataSize<short>(shortNumber);

    unsigned short unsignedShort = 0;
    dataSize<unsigned short>(unsignedShort);
}


int main()
{
    cout << "Sizes of every data type in memory (in bytes)\n\n";
    initialize();
}