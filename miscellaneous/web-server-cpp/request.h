#ifndef REQUEST_H
#define REQUEST_H

#include <vector>
#include <string>

using std::vector;
using std::string;

enum Method
{
  GET, POST
};

struct Request
{
  Method method;
  vector<string> path;
};

vector<string> extract_header(string& request);
void parse_request(string& request);

#endif
