import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

static public class Heap {
        private int[] Heap;
        private int size;
        private int maxsize;
        private static final int FRONT = 1;
        private boolean type;

        public int getSize() {
            return size;
        }

        public Heap(int maxsize,boolean tipo){
            this.maxsize = maxsize;
            this.size = 0;
            Heap = new int[this.maxsize + 1];
            this.type = tipo;
            if(tipo){
                Heap[0] = Integer.MIN_VALUE;    
            }
            else{
                Heap[0] = Integer.MAX_VALUE;
            }
        }

        private int parent(int pos){
            return pos/2;
        }

        private int leftChild(int pos){
            return (2*pos);
        }

        private int rightChild(int pos){
               return (2*pos)+1;
        }

        private boolean isLeaf(int pos){
            if(pos > (size/2) && pos <= size) return true;
            return false;
        }

        private void swap(int fpos, int spos){
            int tmp;
            tmp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = tmp;
        }

        private void minHeapify(int pos){
            
            if(!isLeaf(pos)){

                int right = rightChild(pos) <= size ? Heap[rightChild(pos)] : Integer.MAX_VALUE; 
                int left = leftChild(pos) <= size ? Heap[leftChild(pos)] : Integer.MAX_VALUE;
                int parent = pos <= size ? Heap[pos] : Integer.MAX_VALUE;

                if(parent > left || parent > right){

                    if(left < right){
                        swap(pos,leftChild(pos));
                        minHeapify(leftChild(pos));
                    }
                    else{
                        swap(pos,rightChild(pos));
                        minHeapify(rightChild(pos));
                    }
                }
            }
        }
        
        private void maxHeapify(int pos){
            
            if(!isLeaf(pos)){

                int right = rightChild(pos) <= size ? Heap[rightChild(pos)] : Integer.MIN_VALUE; 
                int left = leftChild(pos) <= size ? Heap[leftChild(pos)] : Integer.MIN_VALUE;
                int parent = pos <= size ? Heap[pos] : Integer.MIN_VALUE;

                if(parent < left || parent < right){

                    if(left > right){
                        swap(pos,leftChild(pos));
                        minHeapify(leftChild(pos));
                    }
                    else{
                        swap(pos,rightChild(pos));
                        minHeapify(rightChild(pos));
                    }
                }
            }
        }

        public void insert(int Element){

            //System.out.println("La galleta tiene Sweetness K: " + Element);

            // System.out.println("Antes " + size);
            Heap[++size] = Element;
            // System.out.println("Despues " + size);

            int current = size;

            if(this.type){
                while(Heap[current] < Heap[parent(current)]){
                    swap(current,parent(current));
                    current = parent(current);
                }
            }else{
                while(Heap[current] > Heap[parent(current)]){
                    swap(current,parent(current));
                    current = parent(current);
                }
            }
            
        }

        public void print(){
            for(int i = 1; i<= size/2; i++){
                System.out.println(" Valor padre:    " + (i <= size ? Heap[i] : "No"));
                System.out.println(" Hijo izquierdo: " + (2*i <= size ? Heap[2*i] : "No"));
                System.out.println(" Hijo Derecho:   " + (2*i+1 <= size ? Heap[2*i+1] : "No"));
                System.out.println();
            }
        }

        public void heapi(){
            for(int pos = (size/2); pos >= 1; pos--){
                if(this.type){
                    minHeapify(pos);
                }
                else{
                    maxHeapify(pos);
                }
            }
        }

        public int poll(){
            
            int popped = Heap[FRONT];
            Heap[FRONT] = Heap[size];
            
            if(this.type){
                Heap[size] = Integer.MAX_VALUE;
            }else{
                Heap[size] = Integer.MIN_VALUE;
            }
            
            size--;
            
            if(this.type){
                minHeapify(FRONT);    
            }else{
                maxHeapify(FRONT);
            }
            
            return popped;
        }

        public int peek(){
            return Heap[FRONT];
        }
    }