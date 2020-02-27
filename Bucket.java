package ceng351.labdb;

public class Bucket {
	
	private String[] bucket;
	
	private int depth;
	
	private int dataNumber;
	private int emptyNumber;
	private int Capacity;
	

	
	
	
	public Bucket(int Depth, int Capacity)
	{
		this.bucket = new String[Capacity];
		this.depth = Depth;
		this.dataNumber = 0;
		this.emptyNumber = Capacity;
		this.Capacity = Capacity;
		
	}
	
	
	public void insert(String value)
	{
		this.bucket[this.dataNumber] = value;
		this.dataNumber++;
		this.emptyNumber--;
	}
	
	
	public void delete(String value)
	{
		int i=0;
		for(i=0; i < this.Capacity; i++)
		{
			if(this.bucket[i].equals(value))
			{
				this.bucket[i] = null;
				break;
			}
		}
		
		String temp[];
		temp = new String[dataNumber];
		for(i=0; i <this.dataNumber; i++)
		{
			if(this.bucket[i] != null)
			{
			temp[i] = this.bucket[i];
			  
			}
		}
		
		this.bucket = null;
		this.bucket = new String[Capacity]; 
		int j = 0;
		for(i=0; i <this.dataNumber; i++)
		{
			if(temp[i] != null)
			{
				this.bucket[j] = temp[i];
				j++;
			}
			
		}
		
		this.dataNumber--;
		this.emptyNumber++;
		
	}
	
	
	public boolean search(String value)
	{
		int i=0;
		for(i=0; i < this.Capacity; i++)
		{
			if(this.bucket[i] != null)
			{
						if(this.bucket[i].equals(value))
						{
								return true;
						}
			}
		}
		
		return false;
	}
	
	
	
	public void writeBucket()
	{
		for(int i=0; i < this.dataNumber; i++)
		{
			if(this.bucket[i] != null)
			{
			System.out.print( "<");
			System.out.print( this.bucket[i] );
			System.out.print( ">");
			}
		}
		
	}
	
	
	public String getData(int i)
	{
		return this.bucket[i];
	}
		
	public int getCapacity()
	{
		return this.bucket.length;
	}
	
	public int getDepth()
	{
		return this.depth;
	}
	
	public void incDepth()
	{
		this.depth++;
	}
	
	public int getEmptyNumber()
	{
		return this.emptyNumber;
	}
	
	public int getDataNumber()
	{
		return this.dataNumber;
	}

}
