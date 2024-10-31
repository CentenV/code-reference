#include "SinglyLinkedList.h"
#include "Node.h"

template <typename T>
SinglyLinkedList<T>::SinglyLinkedList()
{
    this->_size = 0;
    this->_head = nullptr;
    this->_tail = nullptr;
}

template <typename T>
SinglyLinkedList<T>::SinglyLinkedList(const SinglyLinkedList &OTHER)
{}

template <typename T>
T& SinglyLinkedList<T>::operator[](const int INDEX)
{}

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
Node<T>* SinglyLinkedList<T>::createNewNode(const T& VALUE)
{
    Node<T>* newNode = new Node<T>;
    newNode->value = VALUE;
    newNode->next = nullptr;

    return newNode;
}

template <typename T>
ostream &SinglyLinkedList<T>::operator<<(ostream &OUTPUTSTREAM)
{
    // Iterate and print out all elements in the list
    OUTPUTSTREAM << "{";
    Node<T>* currentNode = _head;
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