#include <exception>
#include <string>
#include "mywebserverexception.h"

using std::exception;
using std::string;

MyWebServerException::MyWebServerException(string& message)
{
  this->message = message;
}

MyWebServerException::MyWebServerException(const char message[])
{
  this->message = message;
}

const char* MyWebServerException::what() const noexcept
{
  return this->message.c_str();
}
