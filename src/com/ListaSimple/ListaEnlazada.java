public class ListaEnlazada<T>{
	Nodo<T> head;
	int largo;

	public ListaEnlazada() {
		head = null;
		largo = 0;
	}
	
	public void agregarFinal(T valor) {
		if (head == null) {
			head = new Nodo<T>(valor); 
		}
		else {
			Nodo<T> tmp = head;
			while (!(tmp.siguiente == null)) {
				tmp = tmp.siguiente;
			}
			tmp.siguiente = new Nodo<T>(valor);
		}
		largo ++;
	}

	public void eliminar(T valor) {
		Nodo<T> tmp = head;
		if (tmp == null) {
			System.out.println("lista vacia");
		}
		else if(tmp.valor == valor) { 
			head = tmp.siguiente;
			largo -= 1;
		}
		else {
			while(!(tmp.siguiente == null)){ 
				if(tmp.siguiente.valor == valor) {
					if (!(tmp.siguiente.siguiente == null)) {
						tmp.siguiente = tmp.siguiente.siguiente;
					}
					else if (tmp.siguiente.siguiente == null) {
						tmp.siguiente = null;
					}
					largo--;
					break;
				}
				tmp = tmp.siguiente;		
			}
		}
	}
	
	/*public Nodo<T> get(int i) {
		Nodo<T> out = null;
		if (head != null && i >= 0) {
			Nodo<T> tmp = head;
			while(tmp != null && i > 0) {
				tmp = tmp.siguiente;
				i--;
			}
			if (tmp != null) {
				out = tmp;
			}
		}
		return out;
	}
	
	public void quickSort() {
		if(largo > 0)
			quickSort(0, largo);
		else {
			System.out.println("Lista vacía");
		}
	}
	
	public void quickSort(int menor, int mayor) {
		if(menor >= menor) {
			return;
		}
		//escoger pivote
		Nodo<T> pivote = get(mayor / 2);
		
		//izq < pivote & right > pivote
		int i = menor;
		int j = mayor;
		while (i <= j) {
			while ((get(i).valor).compareTo(pivote.valor) < 0) {
				i++;
			}
			while((get(j).valor).compareTo(pivote.valor) > 0 ) {
				j--;
			}
			if (i <= j) {
				String tmp = get(i).valor;
				get(i).valor = get(j).valor;
				get(j).valor = tmp;
				i++;
				j--;
			}
		}
		if (menor < j) {
			quickSort(menor, j);
		}
		if (mayor > i) {
			quickSort(i, mayor);
		}	
	}
	
	public void bubbleSort() {
		bubbleSort(true, largo);
	}
	
	private void bubbleSort(boolean ascendiendo, int largo) {
		String tmp;
		boolean sorted;
		
		for (int i = 0; i < largo; i++) {
			sorted = true;
			
			for (int j = 1; j < (largo - i); j++) {
				if (ascendiendo) {
					if((get(j - 1).valor).compareTo(get(j).valor) > 0) {
						tmp = get(j - 1).valor;
						get(j - 1).valor = get(j).valor;
						get(j).valor = tmp;
						sorted = false;
					}
				}
				else {
					if((get(j -1).valor).compareTo(get(j).valor) < 0) {
						tmp = get(j - 1).valor;
						get(j - 1).valor = get(j).valor;
						get(j).valor = tmp;
						sorted = false;
					}
				}
			}
			if(sorted) break;
		}
		
	}	*/
		

	public void print() {
		Nodo<T> tmp = head;
		while (!(tmp == null)) {
			System.out.println(tmp.valor);
			tmp = tmp.siguiente;
		}
		System.out.println();
	}
	
	public void eliminarTodo() {
		head = null;
		largo = 0;
	}
}