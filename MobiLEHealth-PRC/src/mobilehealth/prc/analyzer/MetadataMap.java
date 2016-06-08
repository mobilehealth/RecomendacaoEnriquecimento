
package mobilehealth.prc.analyzer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implements Map interface in using less memory. Very simple but usefull
 * for small number of items on it.
 */

public class MetadataMap implements Map<Object, Object>,
      java.lang.Cloneable, java.io.Serializable {
  
  /**
   * Special marker class used to represent null in the keys array.
   */
  private static class NullKey implements Serializable {
    private static final long serialVersionUID = 6391916290867211345L;

    private NullKey() {
    }
  }

  /**
   * The capacity of the map
   */
  int capacity = 11;

  /**
   * The current number of elements of the map
   */
  int count = 0;

  /**
   * Array keeping the keys of the entries in the map. It is "synchrnized"
   * with the values array - the Nth position in both arrays correspond
   * to one and the same entry
   */
  Object theKeys[];

  /**
   * Array keeping the values of the entries in the map. It is "synchrnized"
   * with the keys array - the Nth position in both arrays correspond
   * to one and the same entry
   */
  Object theValues[];
  
  /** Freeze the serialization UID. */
  static final long serialVersionUID = -6747241616127229116L;

  /** the Object instance that will represent the NULL keys in the map */
  transient static Object nullKey = new NullKey();

  /** the static 'all keys' collection */
  transient public static ConcurrentHashMap theKeysHere;

  /** additional static members initialization */
  static {
    theKeysHere = new ConcurrentHashMap();
    theKeysHere.put(nullKey, nullKey);
  } // static code

  /**
   * Constructor
   */
  public MetadataMap() {
    theKeys = new Object[capacity];
    theValues = new Object[capacity];
  } // SimpleMapImpl()

  /**
   * return the number of elements in the map
   */
  public int size() {
    return count;
  } // size()

  /**
   * return true if there are no elements in the map
   */
  public boolean isEmpty() {
    return (count == 0);
  } // isEmpty()

  /**
   * Not supported. This method is here only to conform the Map interface
   */
  


  public Collection values() {
    throw new UnsupportedOperationException(
      "SimpleMapImpl.values() not implemented!");
  } // values()


  /**
   * return the set of the keys in the map. The changes in the set DO NOT
   * affect the map.
   */
  
  
  public Set keySet()
  {
    HashSet s = new HashSet(size());
    Object k;
    for (int i = 0; i < count; i++) {
      k = theKeys[i];
      if (k == nullKey)
           s.add(null);
        else
           s.add(k);
    } //for
    return s;
  } // keySet()

  /**
   * clear the map
   */
  public void clear()
  {
    for (int i = 0; i < count; i++) {
      theKeys[i] = null;
      theValues[i] = null;
    } // for
    count = 0;
  } // clear

  /**
   * return true if the key is in the map
   */
  public boolean containsKey(Object key) {
    return (getPostionByKey(key) != -1);
  }// containsKey

  /**
   * return true if the map contains that value
   */
  public boolean containsValue(Object value) {
    return (getPostionByValue(value) != -1);
  }// containsValue

  /**
   * return the value associated with the key. If the key is
   * not in the map returns null.
   */
  public Object get(Object key) {
    int pos = getPostionByKey(key);
    return (pos == -1) ? null : theValues[pos];
  } // get

  /**
   * put a value in the map using the given key. If the key exist in the map
   * the value is replaced and the old one is returned.
   */
  public Object put(Object key, Object value) {
    Object gKey;
    if (key == null) {
      key = nullKey;
      gKey = nullKey;
    } else
      gKey = theKeysHere.putIfAbsent(key, key);
    // if the key is already in the 'all keys' map - try to find it in that instance
    // comparing by reference
    if (gKey != null) {
      for (int i = 0; i < count; i++) {
        if (gKey == theKeys[i]) {
          // we found the reference - return the value
          Object oldVal = theValues[i];
          theValues[i] = value;
          return oldVal;
        }
      } // for
    } else {// if(gKey != null)
      // no, the key is not in the 'all keys' map - put it there
      gKey = key;
    }
    // enlarge the containers if necessary
    if (count == capacity)
      increaseCapacity();

    // put the key and value to the map
    theKeys[count] = gKey;
    theValues[count] = value;
    count++;
    return null;
  } // put

  /**
   * remove value from the map using it's key.
   */
  public Object remove(Object key) {
    int pos = getPostionByKey(key);
    if (pos == -1)
        return null;

    // save the value to return it at the end
    Object oldVal = theValues[pos];
    count--;
    // move the last element key and value removing the element
    if (count != 0) {
        theKeys[pos] = theKeys[count];
        theValues[pos] = theValues[count];
    }
    // clear the last position
    theKeys[count] = null;
    theValues[count] = null;

    // return the value
    return oldVal;
  } // remove

  /**
   * put all the elements from a map
   */
  public void putAll(Map t)
  {
    if (t == null) {
      throw new UnsupportedOperationException(
      "SimpleMapImpl.putAll argument is null");
    } // if (t == null)

    if (t instanceof MetadataMap) {
      MetadataMap sfm = (MetadataMap)t;
      Object key;
      for (int i = 0; i < sfm.count; i++) {
        key = sfm.theKeys[i];
        put(key, sfm.theValues[i]);
      } //for
    } else { // if (t instanceof SimpleMapImpl)
      Iterator entries = t.entrySet().iterator();
      Map.Entry e;
      while (entries.hasNext()) {
        e = (Map.Entry)entries.next();
        put(e.getKey(), e.getValue());
      } // while
    } // if(t instanceof SimpleMapImpl)
  } // putAll

  /**
   * return positive value as index of the key in the map.
   * Negative value means that the key is not present in the map
   */
  private int getPostionByKey(Object key) {
    if (key == null)
      key = nullKey;
    // check the 'all keys' map for the very first key occurence
    key = theKeysHere.get(key);
    if (key == null)
      return -1;

    for (int i = 0; i < count; i++) {
      if (key == theKeys[i])
        return i;
    } // for
    return -1;
  } // getPostionByKey

  /**
   * return the index of the key in the map comparing them by reference only.
   * This method is used in subsume check to speed it up.
   */
  protected int getSubsumeKey(Object key) {
    for (int i = 0; i < count; i++) {
      if (key == theKeys[i])
        return i;
    } // for
    return -1;
  } // getPostionByKey

  /**
   * return positive value as index of the value in the map.
   */
  private int getPostionByValue(Object value) {
    Object av;
    for (int i = 0; i < count; i++) {
      av = theValues[i];
      if (value == null) {
        if (av == null)
          return i;
      } else {//if (value == null)
        if (value.equals(av))
          return i;
      } //if (value == null)
    } // for

    return -1;
  } // getPostionByValue

  // Modification Operations
  private void increaseCapacity() {
    int oldCapacity = capacity;
    capacity *= 2;
    Object oldKeys[] = theKeys;
    theKeys = new Object[capacity];

    Object oldValues[] = theValues;
    theValues = new Object[capacity];

    System.arraycopy(oldKeys, 0, theKeys, 0, oldCapacity);
    System.arraycopy(oldValues, 0, theValues, 0, oldCapacity);
  } // increaseCapacity

  /**
   * Auxiliary classes needed for the support of entrySet() method
   */
  private static class Entry implements Map.Entry {
    int hash;
    Object key;
    Object value;

    Entry(int hash, Object key, Object value) {
      this.hash = hash;
      this.key = key;
      this.value = value;
    }

    protected Object clone() {
      return new Entry(hash, key, value);
    }

    public Object getKey() {
      return key;
    }

    public Object getValue() {
      return value;
    }

    public Object setValue(Object value) {
      Object oldValue = this.value;
      this.value = value;
      return oldValue;
    }

    public boolean equals(Object o) {
      if (!(o instanceof Map.Entry))
        return false;
      Map.Entry e = (Map.Entry)o;

      return (key==null ? e.getKey()==null : key.equals(e.getKey())) &&
        (value==null ? e.getValue()==null : value.equals(e.getValue()));
    }

    public int hashCode() {
      return hash ^ (key==null ? 0 : key.hashCode());
    }

    public String toString() {
      return key+"="+value;
    }
  } // Entry


  public Set entrySet() {
    HashSet s = new HashSet(size());
    Object v, k;
    for (int i = 0; i < count; i++) {
      k = theKeys[i];
      s.add(new Entry(k.hashCode(), ((k==nullKey)?null:k), theValues[i]));
    } //for
    return s;
  } // entrySet

  // Comparison and hashing
  public boolean equals(Object o) {
    if (!(o instanceof Map)) {
      return false;
    }

    Map m = (Map)o;
    if (m.size() != count) {
      return false;
    }

    Object v, k;
    for (int i = 0; i < count; i++) {
      k = theKeys[i];
      v = m.get(k);
      if (v==null) {
        if (theValues[i]!=null)
          return false;
      }
      else if (!v.equals(theValues[i])){
        return false;
      }
    } // for

    return true;
  } // equals

  /**
   * return the hashCode for the map
   */
  public int hashCode() {
    int h = 0;
    Iterator i = entrySet().iterator();
    while (i.hasNext())
      h += i.next().hashCode();
    return h;
  } // hashCode

  /**
   * Create a copy of the map including the data.
   */
  public Object clone() {
    MetadataMap newMap;
    try {
      newMap = (MetadataMap)super.clone();
    } catch (CloneNotSupportedException e) {
      throw(new InternalError(e.toString()));
    }

    newMap.count = count;
    newMap.theKeys = new Object[capacity];
    System.arraycopy(theKeys, 0, newMap.theKeys, 0, capacity);

    newMap.theValues = new Object[capacity];
    System.arraycopy(theValues, 0, newMap.theValues, 0, capacity);

    return newMap;
  } // clone

  public String toString() {
    int max = size() - 1;
    StringBuffer buf = new StringBuffer();
    Iterator i = entrySet().iterator();

    buf.append("{");
    for (int j = 0; j <= max; j++) {
      Entry e = (Entry) (i.next());
      buf.append(e.getKey() + "=" + e.getValue());
      if (j < max)
        buf.append(", ");
    }
    buf.append("}");
    return buf.toString();
  } // toString

  /**
   * readObject - calls the default readObject() and then initialises the
   * transient data
   *
   * @serialData Read serializable fields. No optional data read.
   */
  private void readObject(ObjectInputStream s)
      throws IOException, ClassNotFoundException {
    s.defaultReadObject();
        
    for (int i = 0; i < theKeys.length; i++) {
      if(theKeys[i] instanceof NullKey) {
        theKeys[i] = nullKey;
      }
      else if(theKeys[i] != null) {
        // check if the key is in the 'all keys' map, adding it if not
        Object o = theKeysHere.putIfAbsent(theKeys[i], theKeys[i]);
        if (o != null) // yes - so reuse the reference
          theKeys[i] = o;
      }
    }//for
  }//readObject
} //SimpleMapImpl