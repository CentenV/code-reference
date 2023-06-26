#include <iostream>
#include <string>
#include "student.h"

using namespace StudentData;
using std::string;

enum schools {SmokyHill, CherryCreek, Grandview, Eaglecrest, CherokeeTrail, Overland};

int generateID();

class Student
{
private:
	string mName;
	int mAge, mStudentId;
public:
	Student(string str, int a)
	{
		mName = str;
		mAge = a;
	}
};

int generateID()
{
	return 

}