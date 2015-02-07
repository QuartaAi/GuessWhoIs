/*
 * BinaryNode: ADT che implementa Nodi per valori generics di alberi BINARI bidirezionali.
 */
package model;


public class BinaryNode<T> {

  /* attributes */
  private int id;
  private T value;
  private BinaryNode<T> parent, sx, dx;

  /* constructors */
  public BinaryNode(int id, T value) {
      this.id = id;
      this.value = value;
      parent = sx = dx = null;
  }
  public BinaryNode(int id, T value, BinaryNode parent) {
      this.id = id;
      this.value = value;
      this.parent = parent;
      sx = dx = null;
  }
  public BinaryNode(int id, T value, BinaryNode parent, BinaryNode sx, BinaryNode dx) {
      this.id = id;
      this.value = value;
      this.parent = parent;
      this.sx = sx;
      this.dx = dx;
  }

  /* getter operations */
  public int getId() { return id; }
  public T getValue() { return value; }
  public BinaryNode<T> getSx() { return sx; }
  public BinaryNode<T> getDx() { return dx; }
  public BinaryNode<T> getParent() { return parent; }
  /* setter operations */
  public void setValue(T value) { this.value = value; }
  public void setSx(BinaryNode<T> sx) { this.sx = sx; }
  public void setDx(BinaryNode<T> dx) { this.dx = dx; }
  public void setParent(BinaryNode<T> parent) { this.parent = parent; }
  
  @Override
  public String toString() {
      String des;
      des = id + "," + value;
      des += ( parent==null ? ",0" : ","+parent.id);
      des += ( sx==null ? ",0" : ","+sx.id);
      des += ( dx==null ? ",0" : ","+dx.id);
      return des;
  }

  public String toXML(String indent) {
    String xml = indent + "<node";
    xml += " id=\"" + id + "\"";
    xml += " type=" + ( parent==null ? "\"root\"" : ( parent.sx==this ? "\"sx\"" : "\"dx\"") );
    xml += " value=\"" + value.toString() + "\"";
    if (sx==null && dx==null) return xml + "/>\n";
    xml += ">\n";
    if (sx!=null) xml += sx.toXML(indent + "  ");
    if (dx!=null) xml += dx.toXML(indent + "  ");
    xml += indent + "</node>\n";
    return xml;
  }

}
