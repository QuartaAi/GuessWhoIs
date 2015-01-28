/*
 * Nodo: ADT che implementa Nodi per valori generics di alberi BINARI.
 */
package model;


public class Nodo<T> {

  private T value;
  private Nodo<T> parent, sx, dx;

  public Nodo(T value) {
      this.value = value;
      parent = sx = dx = null;
  }
  
  public Nodo(T value, Nodo parent) {
      this.value = value;
      this.parent = parent;
      sx = dx = null;
  }
  
  public Nodo(T value, Nodo parent, Nodo sx, Nodo dx) {
      this.value = value;
      this.parent = parent;
      this.sx = sx;
      this.dx = dx;
  }

  public T getValue() {
    return value;
  }

  public Nodo<T> getDx() {
    return dx;
  }

  public Nodo<T> getSx() {
    return sx;
  }

  public Nodo<T> getParent() {
    return parent;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public void setDx(Nodo<T> dx) {
    this.dx = dx;
  }

  public void setSx(Nodo<T> sx) {
    this.sx = sx;
  }

  public void setParent(Nodo<T> parent) {
    this.parent = parent;
  }

}
