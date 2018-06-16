package com.ListaSimple;

public class Nodo<T> {
	public Nodo<T> siguiente;
	public T valor;
	public Nodo<T> anterior;
	
	public Nodo(T pValor) {
		valor = pValor;
		siguiente = null;
		anterior = null;
	}
}
