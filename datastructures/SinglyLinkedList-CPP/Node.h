#ifndef NODE_H
#define NODE_H

// Node in the linked list
template <typename T>
struct Node
{
    T value;
    Node *next;
};

#endif