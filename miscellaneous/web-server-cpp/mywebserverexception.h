#ifndef MY_WEB_SERVER_EXCEPTION_H
#define MY_WEB_SERVER_EXCEPTION_H

#include <exception>
#include <string>

using std::exception;
using std::string;

class MyWebServerException : public exception
{
private:
  string message;

public:
  explicit MyWebServerException(string& message);
  explicit MyWebServerException(const char message[]);

  const char* what() const noexcept override;
};

#endif
