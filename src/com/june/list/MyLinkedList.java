package com.june.list;

import java.util.AbstractSequentialList;
import java.util.Deque;
import java.util.Iterator;
import java.util.ListIterator;

public class MyLinkedList<E> extends AbstractSequentialList<E> implements Deque<E> {

	private int size;
	
	public MyLinkedList() {}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return super.remove(o);
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return super.add(e);
	}
	
	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		super.add(index, element);
	}
	
	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return super.set(index, element);
	}
	
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return super.get(index);
	}
	
	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return super.remove(index);
	}
	
	@SuppressWarnings("unused")
	private static class Node<E> {
		E item;
		Node<E> prev, next;
		
		Node(E item) {
			this(null, item, null);
		}
		
		Node(Node<E> prev, E item, Node<E> next) {
			this.item = item;
			this.prev = prev;
			this.next = next;
		}
	}
	
	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(E e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLast(E e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean offerFirst(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerLast(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peekFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peekLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void push(E e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
