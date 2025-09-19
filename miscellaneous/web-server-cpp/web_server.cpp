#include <csignal>
#include <iostream>
#include <netinet/in.h>
#include <csignal>
#include <sys/socket.h>
#include <unistd.h>

#include "request.h"
#include "web_server.h"

using namespace std;

int* global_socket_fd;

void close_server(int signum)
{
  close(*global_socket_fd);
}

WebServer::WebServer()
{
  /*
   * SOCKET CREATION
   *
   *    Sockets help to facilitate communication between two computers/programs
   *    In this situation, we have are defining a server-side socket that listens on the specified port
   *
   *    Params:
   *      AF_INET: Specifies to use IPv4
   *      SOCK_STREAM: Specifies to use TCP (data stream)
   * */
  this->socket_listen_fd = socket(AF_INET, SOCK_STREAM, 0);
  global_socket_fd = &this->socket_listen_fd;

  /*
   * SERVER ADDRESS + CONFIGURATION
   *
   *    sockaddr_in specifies the IPv4 address and port
   *    INADDR_ANY specifies to bind to all avaliable network (address) interfaces
   *    htons converts unsigned short from host to network byte order
   * */
  struct sockaddr_in addr;
  addr.sin_family = AF_INET;
  addr.sin_addr.s_addr = INADDR_ANY;
  addr.sin_port = htons(this->PORT);

  /*
   * BINDING
   *
   *    Socket binding associates the host address + port with a given socket on a local machine
   *
   *    Params:
   *      __fd: File descriptor of the socket to bind to
   *      __addr: sockaddr structure that contains the address info to be bound to
   *      __len: Length of __addr
   *    Returns: 0 if successful. -1 if unsuccessful
   * */
  int bind_status = bind(this->socket_listen_fd, (sockaddr*) &addr, sizeof(addr));
  if (bind_status < 0) {
    cerr << "Unsuccessfully binded to socket on PORT=" << PORT << endl;
    exit(-1);
  }

  // Signal handling since socket is now bounded
  signal(SIGINT, close_server);
  signal(SIGKILL, close_server);
  signal(SIGTERM, close_server);

  /*
   * START LISTEN
   *
   *    Socket listening is used on a socket to mark it as passive since it will accept incoming connection requests.
   *    In other words: "I am open for connections"
   *    
   *    Params:
   *      __fd: File descriptor of the socket to listen to
   *      __n: Maximum number of subscriptions to the socket
   * */
  int listen_status = listen(this->socket_listen_fd, this->MAX_CONNECTIONS);
  if (listen_status < 0) {
    cerr << "Unsuccessfully opened listener to socket on PORT=" << PORT << "with MAX_CONNECTIONS=" << MAX_CONNECTIONS << endl;
    exit(-1);
  }
}

void WebServer::start_server()
{
  cout << "Listening on port " << PORT << "\n";

  while (true)
  {
    /*
     * ACCEPT INBOUND REQUESTS
     *
     *    Accept takes a connection that is currently in the queue from the listener and creates a new socket to communicate to the client
     *    In other words: "Give me the next client, I will talk to them on a new socket"
     *
     *    A new, seperate socket is created for the purposes of communicating with any new clients. This is done to prevent blocking the main socket
     * */
    int new_client_socket = accept(this->socket_listen_fd, nullptr, nullptr);
    if (new_client_socket < 0)
    {
      continue;
    }
    
    char buf[BUFFER_SIZE];
    int x = read(new_client_socket, buf, BUFFER_SIZE - 1);
    buf[x] = '\0';

    string request = buf;
    // cout << request << "\n";

    parse_request(request);

    string responseBody = "<html><body><h1>Hello world, this is my web server</h1></body></html>";
    string response =
      "HTTP/1.0 200 OK\r\n"
      "Content-Type: text/html; charset=utf-8\r\n"
      "Content-Length: " + to_string(responseBody.size()) + "\r\n"
      "Connection: close\r\n"
      "\r\n"
      + responseBody;

    write(new_client_socket, response.c_str(), response.size());
    close(new_client_socket);
  }
}
