import java.io.*;
import java.util.Scanner;
class Mat {

    //Function to calculate the number of submatrices in which a given element
    //of a Matrix occcurs
    static void calcNumofSubMatOcc(int x, int y,int[][]arr)
    {   int num;
        for(int i=0;i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                num=(i+1)*(j+1)*(x-i)*(y-j);
                arr[i][j]=num;
            }
        }
        
    }

    //Function to accept matrix from user
    static void enterMatrixData(int x, int y, int[][] mat)
    {   Scanner in=null;
        try{
            in=new Scanner(System.in);
        for(int i=0;i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                
                mat[i][j]=in.nextInt();
            }
        }
        
        }catch(Exception e){} finally{in.close();}
    }

    
    //Function to multiply num of occurence of an element and the element itself
    static void priorityMatrix(int x, int y, int[][] arr, int[][] mat,int[][] res)
    {
        //Here we perform simple multiplication of elements at the same position in both matrices
        // Look carefully, this is'nt matrix multiplication!

        int i, j, k; 
        for (i = 0; i < x; i++) 
        { 
            for (j = 0; j < y; j++) 
            { 
                
                    res[i][j] += arr[i][j]  
                                * mat[i][j]; 
            } 
        } 
    }

    //Function to perform reduction operation on the matrix
    static void reduce(int x, int y,int[][] res,int[][] mat, int k)
    {   int temp1=0,temp2=0;
        int max=0;

        while(k!=0)
        {
        
            for(int i=0;i<x;i++)
            {
                for(int j=0;j<y;j++)
                {
                    if(max<res[i][j])
                    {
                        temp1=i;temp2=j;
                        max=res[i][j];
                    }
                }
            }
            
            //This is set to zero so that the same element is'nt selected 
            //in the next iteration
            res[temp1][temp2]=0;

            //Actual reduction operation occurs here
            while((mat[temp1][temp2])>0 && k!=0)
            {
                mat[temp1][temp2]-=1;
                k--;
            }
        }

        
    }

    //Function to calculate the sum of all submatrices
    static int sumofSubMatrices(int x, int y,int[][]mat, int finalans)
    {   
        for(int i=0;i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                finalans+=(i+1)*(j+1)*(x-i)*(y-j)*mat[i][j];
                
            }
        }
        return finalans;
    }
    
	public static void main (String[] args) {
	  // Mat obj=new Mat();
	    final int x,y;
        int k,finalans=0;

	    Scanner sc=new Scanner(System.in);
	    
        System.out.println("Enter size of Matrix (Rows)");
	    x=sc.nextInt();
	    
	    System.out.println("Enter size of Matrix (Columns)");
	    y=sc.nextInt();
	    
        System.out.println("Enter the number of operations");
        k=sc.nextInt();

	    //To keep track of the occurance of elements in  submatrices
	    int[][] arr = new int[x][y];
	    calcNumofSubMatOcc(x,y,arr);
	    
	   /*
	    for(int i=0;i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                System.out.print(arr[i][j]+"\t");
            }
            
            System.out.println("");
        }
		*/
		
		int[][] mat = new int[x][y];
		System.out.println("Enter matrix data");
		enterMatrixData(x,y,mat);

        
		
		/*
        System.out.println("Elements of the matrix are"); 
            for (int i = 0; i < x; i++) { 
                for (int j = 0; j < y; j++) 
                   { System.out.print(mat[i][j] + "  ");} 
                System.out.println(); 
            }
        
        */
       int[][] res = new int[x][y];
       priorityMatrix(x,y,arr,mat,res);     
        /*
       System.out.println("Elements of the matrix are"); 
            for (int i = 0; i < x; i++) { 
                for (int j = 0; j < y; j++) 
                   { System.out.print(res[i][j] + "  ");} 
                System.out.println(); 
            }
        */
       reduce(x,y,res,mat,k);
       /*
       System.out.println("Elements of the matrix are"); 
            for (int i = 0; i < x; i++) { 
                for (int j = 0; j < y; j++) 
                   { System.out.print(mat[i][j] + "  ");} 
                System.out.println(); 
            }
        
        */
       
       finalans=sumofSubMatrices(x,y,mat,finalans);

       System.out.println(finalans);

	}
}
