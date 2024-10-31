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

template <typename T>
SinglyLinkedList<T>::SinglyLinkedList()
{
    this->_size = 0;
    this->_head = nullptr;
    this->_tail = nullptr;
}

template <typename T>
SinglyLinkedList<T>::SinglyLinkedList(const SinglyLinkedList &OTHER)
{
}

template <typename T>
T &SinglyLinkedList<T>::operator[](const int INDEX)
{
}

template <typename T>
T SinglyLinkedList<T>::get(const int INDEX) const
{
}

template <typename T>
bool SinglyLinkedList<T>::append(const T &VALUE)
{
    // New node
    Node<T> *newNode = createNewNode(VALUE);

    // When linked list is empty
    if (this->_head == nullptr)
    {
        this->_head = newNode;
        this->_tail = newNode;
    }
    // When there is only 1 element in the linked list
    else if (this->_head == this->_tail)
    {
        this->_tail = newNode;
        this->_head->next = newNode;
    }
    // More than 1 element in the array
    else
    {
        this->_tail->next = newNode;
        this->_tail = newNode;
    }
}

template <typename T>
Node<T> *SinglyLinkedList<T>::createNewNode(const T &VALUE)
{
    Node<T> *newNode = new Node<T>;
    newNode->value = VALUE;
    newNode->next = nullptr;

    return newNode;
}

template <typename T>
ostream &SinglyLinkedList<T>::operator<<(ostream &OUTPUTSTREAM)
{
    // Iterate and print out all elements in the list
    OUTPUTSTREAM << "{";
    Node<T> *currentNode = _head;
    int i = 0;
    while (currentNode->next != nullptr)
    {
        OUTPUTSTREAM << currentNode->value;
        if (i < _size - 1)
        {
            OUTPUTSTREAM << " ";
        }
    }
    OUTPUTSTREAM << "}";
}

#endif