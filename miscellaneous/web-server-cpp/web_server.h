#ifndef WEB_SERVER_H
#define WEB_SERVER_H

#include <string>

void close_server(int signum);

class WebServer
{
private:
  const unsigned int PORT = 8081;
  const unsigned int MAX_CONNECTIONS = 10;
  const size_t BUFFER_SIZE = 30720;
  int socket_listen_fd;
public:
  explicit WebServer();
  void start_server();
};

#endif
