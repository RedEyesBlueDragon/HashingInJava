package ceng351.labdb;



public class LabDB {

	
	private Bucket[] HashTable;
	
	private int tableDepth;
	private int buckSize;	
	private Bucket HashBucket;
	
	 
	
	public int StringtoInt (String s)
	{
	    int num = s.length();
	    int i=0;	
	    try
	    {
	    	i = Integer.parseInt(s.substring(1,num).trim());
	    	
	    }catch (NumberFormatException nfe) {	         
	    	}
	    
	    return i;
	    
	}

	 
	
	public int getRightMostBits(int value, int n)
	{
		if (n == 0) {
			return 0;
		}
		else {
			return value & ((1<<n)-1);
		}
	} 
	 
	 
	 
	 
    public LabDB(int bucketSize) 
    {
    	this.HashTable = new Bucket[2];
    	tableDepth = 1;
    	this.HashTable[0] = new Bucket(1,bucketSize);
    	this.HashTable[1] = new Bucket(1,bucketSize);	
    	buckSize = bucketSize;
    }
    
    public  String convert(int number) 
    {
        int a;
        String x = "";
        
        
        
        while(number > 0)
        {
            a = number % 2;
          
            x = a + "" + x;
            number = number / 2;
        }
        
        while(x.length() < tableDepth)
        {
        	x = "0" + x; 
        }
        
        return x;
    }
      
    
    
    public void expandTable(int newDepth,String studentID)
    {
    	
    	Bucket[] temp;
    	temp = new Bucket[(int) Math.pow(2, newDepth)];
    	
    	int j = (int) Math.pow(2, tableDepth);
    	/*for(int i=0; i <  Math.pow(2, tableDepth); i++)
    	{
    		//temp[i] = new Bucket(HashTable[i].getDepth(),buckSize);
    		temp[j] =  HashTable[i];
    		j++;
    	}*/
    	
    	
    	
    	for(int i=0; i< Math.pow(2, tableDepth); i++)
    	{
    		temp[i] = HashTable[i];
    		temp[j+i] =  temp[i];
    		
    		
    	}
    	
    	int hasher = getRightMostBits( StringtoInt(studentID), tableDepth );
    	
    	int hasher2 = 0;
		if(hasher <  Math.pow(2, tableDepth-1)) { hasher2 = (int) (hasher + Math.pow(2, tableDepth-1));}
		else if(hasher >  Math.pow(2, tableDepth-1)) { hasher2 = (int) (hasher - Math.pow(2, tableDepth-1));}
    	
    	HashTable = temp;
    	temp = null;
    	
    	this.tableDepth = newDepth;
    	
    	if( HashTable[hasher].getEmptyNumber() == 0 && HashTable[hasher].getDepth() < tableDepth && HashTable[hasher2] == HashTable[hasher] )
    	{
    		int dep = HashTable[hasher].getDepth();
    		Bucket temp1 = new Bucket(1,buckSize);
    		
    		dep++;
    		int k = HashTable[hasher].getDataNumber();
    		for(int i = 0; i < k; i++)
    		{
    			temp1.insert( HashTable[hasher].getData(i) );
    		}
    		
    			
    		
    		
    		//int hasher2 = (int) (hasher + (Math.pow(2, tableDepth)/2));
    		/*
    		for(int i=0; i < (int) ( Math.pow(2, tableDepth-1)) ; i++)
    		{
    			if(HashTable[i] == HashTable[hasher])
    			{
    				HashTable[i] = HashTable[ getRightMostBits( i, HashTable[i].getDepth() )  ];
    			}
    				
    		}
    		*/
    		
    		HashTable[hasher] = null;
    		HashTable[hasher] =  new Bucket(dep,buckSize);
    		HashTable[hasher2] = new Bucket(dep,buckSize);
    		
    		/*
    		if(k1>2)
    		{
    			for(int i=0;i<k1;i=i+2)
    			{
    				HashTable[hasher] = HashTable[i]; 
    			}
    			for(int i=1;i<k1;i=i+2)
    			{
    				HashTable[hasher2] = HashTable[i]; 
    			}
    		}
    		*/
    		
    		for(int i=0; i < k; i++)
    		{
    			if(temp1.getData(i) != null)
    				{
    				enter(temp1.getData(i));
    				}
    				
    		}
    		
    		
    		
    		
    	}
    	
    	
    }
    
    
    

    public void enter(String studentID) 
    {
    	if(search(studentID) == "-1" )
    	{
	    	int hasher = getRightMostBits( StringtoInt(studentID), tableDepth );
	    	
	    	int hasher2 = 0;
			
	    	if(hasher <  Math.pow(2, tableDepth-1)) { hasher2 = (int) (hasher + Math.pow(2, tableDepth-1));}
			else if(hasher >  Math.pow(2, tableDepth-1)) { hasher2 = (int) (hasher - Math.pow(2, tableDepth-1));}
	    
	    	
	    	if( HashTable[hasher].getEmptyNumber() > 0  )
	    	{
	    		HashTable[hasher].insert(studentID);
	    	}
	    	
	    	else if( HashTable[hasher].getEmptyNumber() == 0 && HashTable[hasher].getDepth() < tableDepth && HashTable[hasher2] == HashTable[hasher] )
	    	{
	    		
	    		/*
	    		int dep = HashTable[hasher].getDepth();
	    		HashBucket = HashTable[hasher];
	    	
	    		
	    		Bucket temp = new Bucket(1,buckSize);
	    		dep++;
	    		int k = HashTable[hasher].getDataNumber();
	    		for(int i = 0; i < k; i++)
	    		{
	    			temp.insert( HashTable[hasher].getData(i) );
	    		}
	    		
	    		
	    		
	            int[] temp2 = new int[(int) Math.pow(2, tableDepth)];
	    		
	    		int k1 = 0;
	    		int has=0;
	    		for(int i = 0; i < (int) Math.pow(2, tableDepth); i++)
	    		{
	    			if(HashTable[i] == HashTable[hasher] && i!= hasher)
	    			{
	    				if(i< hasher)
	    				{
	    					has = i;
	    				}
	    				temp2[k1] = i;
	    				k1++;
	    			}
	    		}
	    		
	    		    	
    			
	    		for(int i=0; i < Math.pow(2, tableDepth);i++ )
	    		{
	    			if(HashTable[i] == HashTable[hasher] && getRightMostBits(i, dep) != getRightMostBits(hasher, dep) )
				   	{	
						hasher2 = i;
						
						break;
				   	}
	    		}
    			
    			
	    		//int hasher2 = (int) (hasher + (Math.pow(2, tableDepth)/2));
	    		
	    		HashTable[hasher2] = new Bucket(dep,buckSize);
	    		
	    		HashTable[hasher] = null;
	    		HashTable[hasher] =  new Bucket(dep,buckSize);
	    		
	    		
	    		
	    		for(int i = 0; i <  Math.pow(2, tableDepth) ; i++)
				{
					if(HashTable[i] == HashBucket && getRightMostBits(i, dep) == getRightMostBits(hasher, dep) )
				   	{
						HashTable[i] = HashTable[hasher];
				   	}
					
					if(HashTable[i] == HashBucket && getRightMostBits(i, dep) != getRightMostBits(hasher, dep) )
				   	{
						HashTable[i] = HashTable[hasher2];
				   	}
				}
	    		
	    		
	    		
	    		for(int i=0; i < k; i++)
	    		{
	    			if(temp.getData(i) != null)
	    				{
	    				enter(temp.getData(i));
	    				}
	    				
	    		}
	    		
	    		*/
	    		split(studentID);
	    		//System.out.println( "enter" );
	    		//printLab();
	    		
	    		enter(studentID) ;
	    		
	    	}
	    	
	    	else
	    	{
	    		expandTable(tableDepth+1,studentID);
	    		split(studentID);
	    		enter(studentID) ;
	    		
	    	}
        }    
    }
    
    
    
    
    public void split(String studentID)
    {
    	int hasher = getRightMostBits( StringtoInt(studentID), tableDepth );
    	int hasher2 = 0;
		if(hasher <  Math.pow(2, tableDepth-1)) { hasher2 = (int) (hasher + Math.pow(2, tableDepth-1));}
		else if(hasher >  Math.pow(2, tableDepth-1)) { hasher2 = (int) (hasher - Math.pow(2, tableDepth-1));}
    	
    	int dep = HashTable[hasher].getDepth();
		HashBucket = HashTable[hasher];
	
		
		Bucket temp = new Bucket(1,buckSize);
		dep++;
		int k = HashTable[hasher].getDataNumber();
		for(int i = 0; i < k; i++)
		{
			temp.insert( HashTable[hasher].getData(i) );
		}
		
		  	
		
		for(int i=0; i < Math.pow(2, tableDepth);i++ )
		{
			if(HashTable[i] == HashTable[hasher] && getRightMostBits(i, dep) != getRightMostBits(hasher, dep) )
		   	{	  
				hasher2 = i;
				break;
		   	}
		}
		
		
				
		HashTable[hasher2] = new Bucket(dep,buckSize);
		
		HashTable[hasher] = null;
		HashTable[hasher] =  new Bucket(dep,buckSize);
		
		
		
		for(int i = 0; i <  Math.pow(2, tableDepth) ; i++)
		{
			if(HashTable[i] == HashBucket && getRightMostBits(i, dep) == getRightMostBits(hasher, dep) )
		   	{
				HashTable[i] = HashTable[hasher];
		   	}
			
			if(HashTable[i] == HashBucket && getRightMostBits(i, dep) != getRightMostBits(hasher, dep) )
		   	{
				HashTable[i] = HashTable[hasher2];
		   	}
		}
		
		
		
		for(int i=0; i < k; i++)
		{
			if(temp.getData(i) != null)
				{
				enter(temp.getData(i));
				}
				
		}
		
		
		
    }
    
    
    
    
    
    
    
    
    
    
    public void shrinkTable()
    {
    	int count = 0;
    	if(tableDepth > 1)
    	{
    		int number = (int) Math.pow(2, tableDepth);
    		for(int i=0; i < number/2; i++)
    		{
    			if(HashTable[i] == HashTable[(int) (i + Math.pow(2, tableDepth-1))] )
    			{
    				count++;
    			}
    		}
    		
    		if(count == number/2)
    		{
    			for(int i= (int) Math.pow(2, tableDepth-1) ; i<number; i++ )
    			{
    				HashTable[i] = null;
    			}
    			
    			tableDepth--;
    			merge();
    			shrinkTable();
    		}
    		
    		
    	}
    }
    
    public void merge()
    {
    	if(tableDepth > 1)
    	{
    		int count = (int) Math.pow(2, tableDepth);
	    	
	    	for(int i=0; i < count; i++)
	    	{
	    		int hasher2 = 0;
	    		if(i <  Math.pow(2, tableDepth-1)) { hasher2 = (int) (i + Math.pow(2, tableDepth-1));}
	    		else if(i >=  Math.pow(2, tableDepth-1)) { hasher2 = (int) (i);}
	    		
	    		if(HashTable[i].getDataNumber() == 0 )
	    		{
	    			
					if( HashTable[i].getDepth() == HashTable[hasher2].getDepth() && HashTable[i] != HashTable[hasher2] )
	    			{
	    				
	    				int localdepth = HashTable[hasher2].getDepth();
			    		Bucket temp = new Bucket(1,buckSize);
			    		
			    		int k = HashTable[hasher2].getDataNumber();
			    		for(int i1 = 0; i1 < k; i1++)
			    		{
			    			temp.insert( HashTable[hasher2].getData(i1) );
			    		}
			    		
			    			    		
			    		HashTable[i] = null;
			    		HashTable[hasher2] = null;
			    		HashTable[i] = new Bucket(localdepth-1, buckSize);
			    		HashTable[hasher2] = HashTable[i];
			    		
			    		for(int i1=0; i1 < k; i1++)
			    		{
			    			
			    			if(temp.getData(i1) != null) 
			    			{
			    				enter(temp.getData(i1));
			    			}
			    		}
			    		
			    		
			    		merge();
	    			}
	    		}
	    		
	    	}
    	}
    }
    

    public void leave(String studentID)
    {
    	if(search(studentID) != "-1" )
    	{
    		int hasher = getRightMostBits( StringtoInt(studentID), tableDepth );
	    	int hasher2 = 0;
	    	
	    	if(HashTable[hasher].getDataNumber() == 1 && HashTable[hasher].search(studentID))
	    	{
	    		if(hasher <  Math.pow(2, tableDepth-1)) { hasher2 = (int) (hasher + Math.pow(2, tableDepth-1));}
	    		else if(hasher >  Math.pow(2, tableDepth-1)) { hasher2 = (int) (hasher - Math.pow(2, tableDepth-1));}
	    		
	    		if(HashTable[hasher2].getDepth() == HashTable[hasher].getDepth() && HashTable[hasher2] != HashTable[hasher] && tableDepth > 1)
	    		{
	    			HashTable[hasher].delete(studentID);
	    			
	    			
	    			int localdepth = HashTable[hasher2].getDepth();
		    		Bucket temp = new Bucket(1,buckSize);
		    		
		    		int k = HashTable[hasher2].getDataNumber();
		    		for(int i = 0; i < k; i++)
		    		{
		    			temp.insert( HashTable[hasher2].getData(i) );
		    		}
		    		
		    			    		
		    		HashTable[hasher] = null;
		    		HashTable[hasher2] = null;
		    		HashTable[hasher] = new Bucket(localdepth-1, buckSize);
		    		HashTable[hasher2] = HashTable[hasher];
		    		
		    		for(int i=0; i < k; i++)
		    		{
		    			
		    			if(temp.getData(i) != null) 
		    			{
		    				enter(temp.getData(i));
		    			}
		    		}
		    		merge();
		    		
	    		}
	    		
	    		else
	    		{
	    			HashTable[hasher].delete(studentID);
	    		}
	    	}
    	
    	
    	
    	
    	
    	else
    	{
    		HashTable[hasher].delete(studentID);
    	}
    	
    	
    	shrinkTable();
    	
    	}
    	
    }

    
    
    
    public String search(String studentID) 
    {
    	int hasher = getRightMostBits( StringtoInt(studentID), tableDepth );
    	boolean ischange = false;
    	int count = 0;
    	String answer;
    	
    	/*if(HashTable[hasher] == null)
    	{
    		count = hasher;
    		while(HashTable[hasher] == null) {
    			hasher = getRightMostBits(hasher,tableDepth-1);
    		}
    		ischange = true;
    	}*/
    	
    	if( HashTable[hasher].search(studentID) )
    	{
    		
    		if(!ischange)
    		{
    			answer =  convert(hasher) + "";
    		}
    		else
    		{
    			answer =  convert(count) + "";
    		}
    		return answer;
    	}
    	
    	
        return "-1";
    }

    
    
    public void printLab()
    {
    	System.out.print( "Global depth : ");
    	System.out.println( tableDepth );
    	for(int i=0; i < Math.pow(2,tableDepth); i++)
    	{    		
    		System.out.print( convert(i) );
    		System.out.print( " : " );  		
    	/*	if(HashTable[i] == null)
        	{
    			System.out.print( "[Local depth:" );
    			System.out.print( HashTable[(int) (i- (Math.pow(2, tableDepth)/2)) ].getDepth() );
	    		System.out.print( "]" );
	    		HashTable[(int) (i- (Math.pow(2, tableDepth)/2)) ].writeBucket();
        	}
    		else
    		{*/
	    		System.out.print( "[Local depth:" );
	    		System.out.print( HashTable[i].getDepth() );
	    		System.out.print( "]" );
	    		HashTable[i].writeBucket();  
	    		
    		//}
    		System.out.println();
    	}
	
    }
}
