#include "web_server.h"

using namespace std;

int main()
{
  WebServer* web_server = new WebServer();

  web_server->start_server();

  delete web_server;
}

