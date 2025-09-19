#include <vector>
#include "request.h"
#include "mywebserverexception.h"

// using std::vector;
#include <iostream>
using namespace std;

/*
 * Extracts the header from the request
 * */
vector<string> extract_header(string& request)
{
  // Get the index in the request string where the header would end
  // Double \r\n\r\n represents end of the header
  size_t index_of_eoh = request.find("\r\n\r\n");
  if (index_of_eoh == string::npos) // End of header is not present
  {
    throw MyWebServerException("Unable to seperate/differentiate header in request");
  }

  string header_string = request.substr(0, index_of_eoh);
  cout << header_string << endl;

  vector<string> header;

  return header;
}


void parse_request(string& request)
{
  // string header;

  extract_header(request);
}
