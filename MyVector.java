
// Author : bibliotheque@hanmail.net

// Just for practice..


package MyVector;

import java.util.Iterator;


class MyVector { 
  protected Object[] data = null;  // 객체를 담기 위한 객체배열을 선언한다. 
  protected int capacity = 0;        // 용량(객체배열의 크기) 
  protected int size = 0;              // 객체배열에 저장된 객체의 개수 

  public MyVector(int capacity) { 

    /*

                  다음의 코드를 완성하세요. 

                1. 지역변수 capacity의 값이 0보다 작으면, IllegalArgumentExcepton을 발생시킨다.

                2. 지역변수 capacity의 값을 인스턴스변수 capacity에 저장한다.

                3. 지역변수 capacity와 같은 크기의 Object배열을 생성해서 객체배열 data에 저장한다.

     */
    if (capacity <0) {
      throw new IllegalArgumentException("capacity < 0");
    } else {
      this.capacity = capacity;
      data = new Object[capacity];      
    }

  } 

  public MyVector() { 

    // 크기를 지정하지 않으면 크기를 10으로 한다. 
    /*
         다음의 코드를 완성하세요.

           1. 매개변수가 없는 생성자를 통해 MyVector를 생성한다면,
           용량(capacity)가 10이 되도록 한다.

           매개변수가 있는 생성자 MyVector(int capacity)를 사용하세요.

     */
    this(10);
  } 

  public boolean isEmpty() { // MyVector가 비어있는지 확인한다.
    return size == 0;
  } 

  public int capacity() { // MyVector의 용량(크기)를 반환한다.
    return this.capacity;
  } 

  public int size() { // MyVector에 저장된 객체의 개수를 반환한다. 
    return this.size;
  } 


  public void ensureCapacity(int minCapacity) { 
    int newCapacity = capacity; 


    /*

            다음의 코드를 완성하세요.



            1. minCapacity가 capacity보다 크면 newCapacity의 값을 두배로 한다.

               (사실 반드시 2배이어야 할 필요는 없다. 적절한 비율로 크기를 늘려주기 위한 것임)

            2. 그래도 minCapacity가 newCapacity보다 크면,

                minCapacity의 값을 newCapacity에 저장한다.

            3. setCapacity()를 호출해서 객체배열의 크기가 newCapacity가 되도록 한다.

     */

    if (minCapacity > this.capacity) {
      newCapacity *= 2; 
    }

    if (minCapacity > newCapacity) {
      newCapacity = minCapacity;
    }

    setCapacity(newCapacity);

  } 

  public void setCapacity(int newCapacity) { 
    if(this.capacity==newCapacity) return; 


    /*

              다음의 코드를 완성하세요.



            1. newCapacity 크기의 객체배열을 새로 만든다.

            2. 기존의 객체배열(data)의 내용을 새로운 객체배열에 복사한다.

               (System.arraycopy()사용)

            3. data가 새로 생성된 객체배열을 참조하도록 한다.

            4. capacity의 값을 newCapacity로 변경한다.

     */


    Object[] tempArr = new Object[newCapacity];
    System.arraycopy(this.data, 0, tempArr, 0, this.size);
    this.data = tempArr;
    this.capacity = newCapacity;

  } 

  public boolean add(Object obj) { 

    // 새로운 객체를 저장하기 전에 저장할 공간을 확보한 후에 객체를 추가해야한다.

    if (this.size == this.capacity) { // The data array is full.
      ensureCapacity(2*this.capacity);      
    }

    this.data[this.size] = obj;
    this.size++;

    return true;
  } 

  public Object get(int index) {
    return this.data[index];

  } 


  public Object set(int index, Object obj) { 
    /*

              다음의 코드를 완성하세요.



           1. index가 size보다 크거나 같으면 ArrayIndexOutOfBoundsException을 발생시킨다.

           2. 객체배열 data의 index번째 값을 임시로 저장한다.

           3. 새로운 객체(obj)를 객체배열 data의 index번째 값에 저장한다.

           4. 임시로 저장했던 기존 객체를 반환한다.

     */

    checkIndex(index); 
    // 여기서는  IndexOutOfBoundsException을 사용하는 checkIndex()만 사용하기로 했다

    Object tempObj = this.data[index];
    this.data[index] = obj;
    return tempObj;

  } 

  private void checkIndex(int index) {
    if (index<0 || index>=this.size) {
      throw new IndexOutOfBoundsException("Illegal index : " + index);            
    }    
  }

  private void checkArrayIndex(int index){
    if (index<0 || index>this.size) {
      throw new ArrayIndexOutOfBoundsException("Illegal array index : " + index);            
    }    
  }


  private int commonIndexOf(Object obj, int index, int sign) {

    checkIndex(index);


    int i=-1;
    int limit = sign > 0 ? this.size : 1;

    /*
     * increment > 0 : for indexOf 
     * 
     *     condition of for clause below  : i < this.size
     *      so, limit = this.size;
     *     
     * 
     * increment < 0 : for lastIndexOf
     *
     *    condition of for clause below : i > -1
     *                                      -i < 1
     *      so, limit = 1;
     *
     * */

    if (obj == null) {

      for (i=index ; sign*i<limit ; i+=sign) {
        if (this.data[i] == null) break;
      }

    } else {
      for (i=index ; sign*i<limit ; i+=sign) {
        if (this.data[i].equals(obj)) break;        
      }      
    }

    return i;        
  }

  public int indexOf(Object obj, int index) { 

    /*

               다음의 코드를 완성하세요.



             1. 넘겨받은 객체(obj)가 null이면,

                1.1 반복문을 이용해서 객체배열(data)에서 null인 것을 찾아서 그 위치를 반환한다.

                     (검색순서는 index부터 시작해서 증가하는 방향)

             2. 넘겨받은 객체(obj)가 null이 아닌 경우에는

                2.1 equals를 이용해서 같은 객체가 있는지 찾아서 그 위치를 반환한다.                 

                     (검색순서는 index부터 시작해서 증가하는 방향)

             3. 못찾으면 -1을 반환한다.

     */
    return commonIndexOf(obj, index, +1); // private method
  } 

  public int lastIndexOf(Object obj, int index) { 

    /*

                다음의 코드를 완성하세요.


             1. index의 값이 size보다 같거나 크면, IndexOutOfBoundsException을 발생시킨다.

             2. 넘겨받은 객체(obj)가 null이면,

                2.1 반복문을 이용해서 객체배열(data)에서 null인 것을 찾아서 그 위치를 반환한다.

                     (검색순서는 index부터 시작해서, index값을 감소시켜서 객체배열의 0번째까지 )

             3. 넘겨받은 객체(obj)가 null이 아닌 경우에는

                3.1 equals를 이용해서 같은 객체가 있는지 찾아서 그 위치를 반환한다.                 

                     (검색순서는 index부터 시작해서, index값을 감소시켜서 객체배열의 0번째까지 )

             4. 못찾으면 -1을 반환한다.

     */

    return commonIndexOf(obj, index, -1); // private method  
  } 




  public boolean contains(Object obj) { 

    return commonIndexOf(obj, 0, +1) == -1 ? false : true ;
  } 

  public int indexOf(Object obj) { 
    // 찾기 시작할 위치(index)를 지정하지 않으면 처음부터 찾는다. 

    return commonIndexOf(obj, 0, +1);
  } 

  public int lastIndexOf(Object obj) { 
    // 찾기 시작할 위치(index)를 지정하지 않으면 끝부터 찾는다. 

    return commonIndexOf(obj, this.size-1, -1);
  }



  public void add(int index, Object obj) { 

    /*

              다음의 코드를 완성하세요.


             1. index의 값이 size보다 크면, ArrayIndexOutOfBoundsException

             2. ensureCapacity()를 호출해서 새로운 객체가 저장될 공간을 확보한다. 

             3. 객체배열에서 index위치의 객체와 이후의 객체들을 한칸씩 옆으로 이동한다.

                (System.arraycopy()사용)

             4. 객체배열의 index위치에 새로운 객체(obj)를 저장한다.

             5. size의 값을 1 증가시킨다.

     */

    checkArrayIndex(index);
    ensureCapacity(size+1);
    System.arraycopy(this.data, index, this.data, index+1, this.size-index);
    this.data[index] = obj;
    this.size++;     
  } 

  public Object remove(int index) { 
    Object oldObj = null; 

    /*

              다음의 코드를 완성하세요.


            1. index가 배열의 범위를 벗어나는지 체크하고, 벗어나면 IndexOutOfBoundsException를 발생시킨다.

            2. 삭제하고자하는 데이터를 oldObj에 저장한다.

            3. 삭제하고자 하는 객체가 마지막 객체가 아니라면, 배열복사를 통해 빈자리를 채워준다.

            4. 마지막 데이터를 null로 한다. 배열은 0 부터 시작하므로 마지막 요소는 index가 size-1이다. 
            5. size의 값을 1 감소시킨다.

            6. oldObj를 반환한다.

     */
    checkIndex(index);
    oldObj = this.data[index];
    if (index != this.size-1) {
      System.arraycopy(this.data, index+1, this.data, index, this.size-index-1);        
    }
    this.data[this.size-1] = null;
    this.size--;
    return oldObj;

  } 

  public boolean remove(Object obj) { 


    /*

            다음의 코드를 완성하세요.



          1. 반복문을 이용해서 객체배열의 모든 요소와 obj가 일치하는지 확인한다.

              1.1 일치하면 remove(int index)를 호출해서 삭제하고 true를 반환한다.

              1.2 일치하는 것을 찾지 못하면, false를 반환한다.

     */

    for (int i=0 ; i<this.size ; i++) {
      if ( obj.equals(this.data[i]) ) {
        remove(i);
        return true;
      }
    }

    return false;      
  } 

  public void clear(){
    for (int i=0 ; i<this.size ; i++) {
      this.data[i] = null;        
    }
    this.size = 0;
  } 

  public Object[] toArray(){ 
    /*

             다음의 코드를 완성하세요.


           1. 객체배열 data와 같은 크기의 객체배열을 생성한다.

           2. 배열의 내용을 복사한다. (System.arraycopy()사용)

           3. 생성한 객체배열을 반환한다.

     */

    Object[] tempData = new Object[this.size];
    System.arraycopy(this.data, 0, tempData, 0, this.size);
    return tempData;
  } 

  public String toString() { 
    /*

             다음의 코드를 완성하세요.



           1. StringBuffer를 생성한다.

           2. 반복문과 get(int i)를 사용해서 배열의 모든 요소에 접근해서 toString()을 호출해서

              sb에 저장한다.

           3. sb를 String으로 변환해서 반환한다.

     */
    StringBuffer sb = new StringBuffer();
    sb.append("[");
    for (int i=0 ; i<this.size ; i++) {
      String tempStr = (i == 0) ? "" : ", ";
      sb.append( tempStr + get(i).toString() );
    }
    sb.append("]");
    return sb.toString();
  } 

  public Iterator iterator() { 

    // 내부클래스 Itr의 객체를 생성해서 반환한다.

    return new Itr();
  } 

  private class Itr implements Iterator { 
    int cursor = 0;    // 읽어올 요소의 위치(index) 
    int lastRet = -1;  // 직전에 읽어온 객체의 위치(index)


    public Object next() { 
      /*

                  다음의 코드를 완성하세요.



                1. cursor가 가리키고 있는 위치(index)의 객체를 꺼내온다.(get()사용)

                2. cursor의 값을 lastRet에 저장하고 cursor의 값을 1 증가시킨다.

                   (예를 들어 cursor의 값이 1 이었으면 lastRet의 값은 1이 되고, cursor의 값은 2가 된다.)

                3. 1에서 꺼내온 객체를 반환한다.

       */

      Object obj = get(this.cursor);
      this.lastRet = this.cursor;
      this.cursor++;
      return obj;
    } 

    public boolean hasNext() { 
      /*

                     코드를 완성하세요.

                    hint : cursor의 값이 객체배열의 마지막 요소의 위치(index)에 다다랐는지 확인한다.

       */
      return this.cursor < size;

    } 


    public void remove() { 

      /*

                    다음의 코드를 완성하세요.



                  1. lastRet의 값이 -1이면(직전에 읽어온 객체가 없거나 삭제 되었으면)

                     IllegalStateException을 발생시킨다.

                  2. 직전에 읽어온 객체를 객체배열에서 제거한다.(MyVector의 remove()사용)

                  3. lastRet의 값이 cursor의 값보다 작으면 cursor의 값을 1감소 시킨다.
                     (현재 cursor의 위치보다 이전의 값이 삭제되면 cursor의 위치도 변경되어야 하므로)

                  4. lastRet에 -1을 저장한다.(직전에 읽어온 객체가 삭제되었으므로)



                    예를 들어 next()를 호출해서 객체배열의 index가 3인 요소를 읽어오면.. 

                   cursor(다음에 읽어올 객체의 위치)의 값은 4가 되고, lastRet의 값은 3이 된다.



                     이때 remove()가 호출되면, 읽어온 객체인 index가 3인 요소는 삭제 되고

                   index가 4인 위치에 있던 객체는 index가 3인 위치로 이동하게 된다.

                   (빈 자리를 메꾸기 위해 index가 4 이후의 모든 객체가 이동해야함)



                     그래서 cursor의 값은 4에서 3이 되어야 하고, lastRet의 값은 -1이 되어서

                     읽어온 객체가 없거나 삭제 되었음을 의미한다.                      

       */

      if (this.lastRet == -1) {
        throw new IllegalStateException();
      }

      MyVector.this.remove(this.lastRet);
      if (this.lastRet<this.cursor) {
        this.cursor--;
      }
      this.lastRet = -1;    

    } // Itr.remove()
  } // private class Itr
} // class MyVector 


class MyVectorTest { 
  public static void main(String args[]) { 

    MyVector v = new MyVector(2); 

    // #1
    System.out.println("<basic functions and add, set, get Test>");
    v.add("AAA"); 
    v.add("BBB"); 
    v.add("CCC"); 
    v.add("BBB");

    for(int i=0; i < v.size();i++) { 
      System.out.println(v.get(i)); 
    } 

    System.out.println("size : "+v.size()); 
    System.out.println("capacity : "+v.capacity()); 
    System.out.println("isEmpty : "+v.isEmpty());     
    System.out.println("return value of set : " + v.set(0, "aaa") ); 

    for(int i=0; i < v.size();i++) { 
      System.out.println(v.get(i));       
    } 




    // #2
    System.out.println("\n<indexOf Test>");
    System.out.println("indexOf BBB : "+v.indexOf("BBB")); 
    System.out.println("lastIndexOf BBB : "+v.lastIndexOf("BBB")); 
    System.out.println("contains BBB : "+v.contains("BBB")); 




    // #3
    System.out.println("\n<add, remove, clear, toArray, toString Test>");
    v.clear();
    v.add("AAA"); 
    v.add("BBB"); 
    v.add("CCC"); 
    v.add("DDD"); 
    v.add(2,"EEE"); 

    Object[] objArr = v.toArray(); 

    for(int i=0; i < objArr.length;i++) { 
      System.out.print(v.get(i)+","); 
    } 
    System.out.println(); 

    System.out.println("size:"+v.size()); 
    System.out.println("capacity:"+v.capacity()); 
    System.out.println("isEmpty:"+v.isEmpty()); 
    System.out.println(); 

    v.remove(1); // BBB를 삭제 
    v.remove("CCC"); 

    System.out.println(v); // System.out.println(v.toString()); 
    System.out.println("size:"+v.size()); 
    System.out.println("capacity:"+v.capacity()); 
    System.out.println("isEmpty:"+v.isEmpty()); 
    System.out.println(); 

    v.clear(); 
    System.out.println(v); 
    System.out.println("size:"+v.size()); 
    System.out.println("capacity:"+v.capacity()); 
    System.out.println("isEmpty:"+v.isEmpty()); 




    // #4
    System.out.println("\n<iterator Test>");
    v.add("AAA"); 
    v.add("BBB"); 
    v.add("CCC"); 
    v.add("DDD"); 

    Iterator it = v.iterator(); 

    while(it.hasNext()) { 
      Object obj = it.next(); 
      System.out.println(obj); 

      if(obj.equals("BBB")) 
        it.remove(); 
    } 

    System.out.println(v); 

  } 


} // class MyVectorEx
 	
// [Java1000제] 객체배열 MyVector 구현하기 5 - iterator() (남궁성의 코드초보스터디(자바 java, c언어, javascript, python) |작성자 즐거웁게

