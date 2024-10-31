#ifndef SINGLYLINKEDLIST_H
#define SINGLYLINKEDLIST_H

#include "Node.h"

#include <iostream>
using std::ostream;

// Singly Linked List Class
template <typename T>
class SinglyLinkedList
{
public:
    /**
     * @brief Create a new, empty singly linked list
     * 
     */
    SinglyLinkedList();
    /**
     * @brief Create a new singly linked list with the same values as the other list
     *
     * @param OTHER Other SinglyLinkedList
     */
    SinglyLinkedList(const SinglyLinkedList &OTHER);
    T& operator[] (const int INDEX);
    T get(const int INDEX) const;
    bool append(const T &VALUE);
    bool insert(const int INDEX, const T &VALUE);
    T remove(const int INDEX);
    void clear();
    int* toArray() const;
    ostream& operator<<(ostream &OUTPUTSTREAM);

private:
    size_t _size;
    Node<T>* _head;
    Node<T>* _tail;

    Node<T>* createNewNode(const T& VALUE);
};

#endif