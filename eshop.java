import java.lang.*;
import java.util.*;
import java.io.*;

class Mobile
{
	protected static int itemID=0;
	protected int id;
	protected String brand;
	protected int model;
	protected double price;
	protected String description;
	protected int quantity;
	

	Mobile()
	{
		id = 999999999;
		brand = " ";
		model = 0; 
		price = 0.0;
		description = " ";
		quantity = 0;	
	}
	Mobile ( String itemBrand, int md,int qty,float p )
	{
		id = itemID++;     
		brand = itemBrand;
		setPrice(p);
		setModel(md);
		setQuantity(qty);
	}
	public String getBrand()
	{
		return brand;
	}
	public void setPrice(float p){
		price=p;
	}
	public double getPrice()
	{
		return price;
	}
	
	public void setModel(int m)
	{
		model=m;
	}
	public int getModel()
	{
		return model;
	}
	public void setQuantity(int q)
	{
		quantity+=q;
	}
	public  void printDetails() 
	{
		
		System.out.printf("%2d   %7s     %4d     %8.2f   %d\n",this.id,this.brand,this.model,this.price,this.quantity);
	}
}
//deriverd class of Mobile
class FeaturedMobile extends Mobile
{
	protected float display_size,weight;
	protected String sim_dual;
	protected int battery;
	
	FeaturedMobile()
	{
		display_size=0;
		battery = 00;
		weight =0;
		sim_dual = "no";
	}
	FeaturedMobile(String br,int md,int qty,float p,float d,int b, int w , String ch)
	{
		super(br,md,qty,p);
		setDisplaySize(d);
		setBattery(b);
		setWeight(w);
		setDualSim(ch);
	}
	public void setDisplaySize(float d)
	{
		display_size=d;
	}
	public void setBattery(int b)
	{
		battery = b;
	}
	public void setWeight(float w)
	{
		weight = w;
	}
	public void setDualSim(String ch)
	{
		sim_dual = ch;
	}
	public void displayMobile()
	{
		System.out.println("\nDisplay size   : "+display_size+"inch\nBattery        : "+battery+"/mA\nweight         : "+weight+"grams\ndual sim       : "+sim_dual);
	}
	
}

//derived class of featured mobile
class SmartMobile extends FeaturedMobile
{
	private String sensors,processor;
	private int Ram,internalMemory,externalMemory;
	private float fcam,rcam;
	private os OS;
	
	SmartMobile()
	{
		//OS = "Android";
		Ram = internalMemory = externalMemory = 0;
		fcam = rcam = 0;
		sensors=" ";
	}
	SmartMobile(String br,int md,int qty,float p,float d,int b, int w , String ch,String o,String v,String k,int n,int i,int e, float r, float f)
	{
		super(br,md,qty, p,d,b, w ,ch);
		//setOS(s);
		OS = new os(o,v,k);
		setRam(n);
		setMemory(i, e);
		setCamera(r,f);
	}
	//inner class of SmartMobile
	class os
	{
		private String os,version,kernel;
		
		os(String o,String v,String k)
		{
			setOs(o);
			setVersion(v);
			setKernel(k);
		}
		public void setOs(String s)
		{
			os = s;
		}
		public void setVersion(String s)
		{
			version= s;
		}
		public void setKernel(String s)
		{
			kernel = s;
		}
		public void osDisplay()
		{
			System.out.println("OS             :" + this.os);
			System.out.println("Version        :" + this.version);
			System.out.println("Kernel         :" + this.kernel);	
		}
	}
	public void setSensors(String s)
	{
		sensors = s;
	}
	public void setRam(int n)
	{
		Ram = n;
	}
	public void setMemory(int i,int e)
	{
		internalMemory = i;
		externalMemory = e;
	}
	public void setCamera(float r,float f)
	{
		fcam = f;
		rcam = r;
	}
	public void displayMobile()
	{
		super.displayMobile();
		OS.osDisplay();
		System.out.println("RAM            : "+Ram+" GB\nInternal memory: "+internalMemory+" gb\nExternal memory: "+externalMemory+" gb\nfront camera   : "+fcam+"mp\nrear camera    : "+rcam+"mp");
	}
	protected void finalize(int i)
	{
		System.out.println("ID "+i+" is destructed");
	}
}

class Cart
{
	
	private String productName;
	private float price,subTotal;
	private int quantity;
	private static float total=0;
	
	Cart()
	{
		productName = " ";
		price = 0;
		quantity =0;
	}
	public void addToCart(SmartMobile id,int qty)
	{
		productName = id.getBrand()+id.getModel();
		price = (float) id.getPrice();
		quantity = qty;
		subTotal = quantity * price;
		total +=subTotal;
	}
	public void addToCart(FeaturedMobile id,int qty)
	{
		productName = id.getBrand()+id.getModel();
		price = (float) id.getPrice();
		quantity = qty;
		subTotal = quantity * price;
		total +=subTotal;
	}
	public float getTotal()
	{
		return total;
	}
	public void removeFromCart()
	{
		total -= subTotal;
	}
	public void displayCartItem()
	{
		int i=0;
		System.out.println((i+1)+"        " +productName+"          "+price+"  *  "+quantity+"  =  "+subTotal);
	}
}
class Address
{
	private String door_no,st_name,locality,city,pincode;
	
	public void setAddress()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("enter the door number:");
		door_no = in.next();
		System.out.println("enter the street name:");
		st_name = in.next();
		System.out.println("enter the locality:");
		locality = in.next();
		System.out.println("enter the city:");
		city = in.next();
		System.out.println("enter the pincode:");
		pincode = in.next();
	}
	public void getAddress()
	{
		System.out.println("No:"+door_no+","+st_name+","+locality+","+city+","+pincode);
	}
}
interface User  
{
	public void logIn();
	public void addCartItem(SmartMobile m,int q);
	public void addCartItem(FeaturedMobile m,int q);
	public void removeCartItem(int id);
	public void displayCart();
	
}
class Member implements User
{	
	private static int numcart = 0,id=0;
	protected int ph_no;
	protected String name,password2,email,password;
	private Address address = new Address();
	List<Cart> cart = new ArrayList<Cart>();
	
	public void signUp()
	{
		Scanner in = new Scanner(System.in);
		String password2;
		id=id++;
		System.out.println("\n enter the NAME     :");
		name = in.next();
		System.out.println("\n enter the E-mail ID:");
		email = in.next();
		do
		{
			System.out.println("\n enter the PASSWARD :");
			password = in.next();
			System.out.println("\n enter the PASSWARD again :");
			password2 = in.next();
		}while(password.compareTo(password2)!=0);
		
		System.out.println("\n enter the  address:");
		address.setAddress();
	}
	public void logIn()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("\n enter the NAME     :");
		name = in.next();
		System.out.println("\n enter the E-mail ID:");
		email = in.next();
		System.out.println("\n enter the PASSWARD :");
		password = in.next();
		System.out.println("\n LOGGED IN AS   "+name);	
	}
	public void addCartItem(SmartMobile m,int q)
	{
		Cart c = new Cart();
		c.addToCart(m,q);
		cart.add(c);
		displayCart();
		
	}
	public void addCartItem(FeaturedMobile m,int q)
	{
		Cart c = new Cart();
		c.addToCart(m,q);
		cart.add(c);
		displayCart();
	}
	public void removeCartItem(int id)
	{
		cart.get(id-1).removeFromCart();
		cart.remove(id-1);
	}	
	public void displayCart()
	{	
		System.out.println("s_no     Product          price     quantity     Total" );
		for(Cart c:cart)
		{
			c.displayCartItem();
		}
		System.out.println("total                                           "+cart.get(0).getTotal());	
	}
	public void displayMemberDetails()
	{
		System.out.println("name:"+name+"        e-mail:"+email);
		System.out.printf("address:");address.getAddress();
	}
}

class Guest implements User
{
	private int ph_no;
	private String name,email;
	private Address address = new Address();
	List<Cart> cart = new ArrayList<Cart>();
	public void logIn()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("\n enter the NAME     :");
		name = in.next();
		System.out.println("\n enter the E-mail ID:");
		email = in.next();
		
		System.out.println("\n enter the Address:");
		address.setAddress();
		System.out.println("\n LOGGED IN AS guest");
	}
	public void addCartItem(SmartMobile m,int q)
	{
		Cart c = new Cart();
		c.addToCart(m,q);
		cart.add(c);
		displayCart();
	}
	
	public void addCartItem(FeaturedMobile m,int q)
	{
		Cart c = new Cart();
		c.addToCart(m,q);
		cart.add(c);
		displayCart();
	}
	public void removeCartItem(int id)
	{
		cart.get(id-1).removeFromCart();
		cart.remove(id-1);
	}	
	public void displayCart()
	{
		System.out.println("s_no     Product          price     quantity     Total" );
		for(Cart c:cart)
		{
			c.displayCartItem();
		}
		System.out.println("total                                           "+cart.get(0).getTotal());
	}
}
class eshop
{
	public static void main(String[] args) throws IOException
	{
		int i,j,fn=0,sn=0;
		List<SmartMobile> smobile = new ArrayList<SmartMobile>();
		List<FeaturedMobile> fmobile = new ArrayList<FeaturedMobile>();
		//SmartMobile[] smobile = null ;
		//FeaturedMobile[] fmobile  = null ;
		List<Member> member = new ArrayList<Member>();
		List<Guest> guest = new ArrayList<Guest>();
		Scanner in = new Scanner (System.in);
		do{
			System.out.println("\n1->Seller\n2->Customer\n3->exit\n\n enter your choice:");
			int ch = in.nextInt();
			switch(ch)
			{
				case 1: System.out.println("\n1->add mobile to stack\n2->list the member");
						ch = in.nextInt();
						switch(ch)
						{
							case 1: 
									System.out.println("\n the the number of featured Mobiles");
									fn = in.nextInt();
									System.out.println("\n the the number of SmartMobiles");
									sn = in.nextInt();
									for(i=0;i<sn;i++)
									{
										System.out.println("Enter the details of Smart mobile "+(i+1)+" :");
										System.out.println("Enter brand: ");
										String st= in.next();
										System.out.println("Enter model : ");
										int md= in.nextInt();
										System.out.println("Enter rate :");
										float f=in.nextFloat();
										System.out.println("Enter Quantity: ");
										/*System.out.println("Enter Description: ");
										BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
										String des=br.readLine();*/
										int qty=in.nextInt();
										System.out.println("Enter the display size :");
										float ds = in.nextFloat();
										System.out.println("Enter the weight :");
										int w=in.nextInt();
										System.out.println("Enter battery :");
										int b=in.nextInt();
										System.out.println("Enter Dual sim (Yes/No):");
										String d =in.next();
										System.out.println("Enter Ram size :");
										int ram=in.nextInt();
										
										System.out.println("Enter the OS :");
										String o=in.next();
										System.out.println("Enter the Version of OS :");
										String v=in.next();
										System.out.println("Enter Kernel build no :");
										String k=in.next();
										System.out.println("Enter internal memory :");
										int im=in.nextInt();
										System.out.println("Enter external Memory :");
										int em=in.nextInt();
										System.out.println("Enter rear camera :");
										float rc=in.nextFloat();
										System.out.println("Enter front camera :");
										float fc=in.nextFloat();
										SmartMobile tmp = new SmartMobile(st,md,qty,f,ds,b,w,d,o,v,k,ram,im,em,rc,fc);
										smobile.add(tmp);
										
									}
									for(j=0;j<fn;j++)
									{
										System.out.println("Enter the details of Featured mobile "+(j+1)+" :");
										System.out.println("Enter brand: ");
										String st= in.next();
										System.out.println("Enter model : ");
										int md= in.nextInt();
										System.out.println("Enter rate :");
										float f=in.nextFloat();
										System.out.println("Enter Quantity: ");
										int qty=in.nextInt();
										System.out.println("Enter the display size :");
										float ds = in.nextFloat();
										System.out.println("Enter the weight :");
										int w=in.nextInt();
										System.out.println("Enter battery :");
										int b=in.nextInt();
										System.out.println("Enter Dual sim (Yes/No):");
										String d =in.next();
										FeaturedMobile tmp1 = new FeaturedMobile(st,md,qty,f,ds,b,w,d);
										fmobile.add(tmp1);
										continue;
										
									}
								    break;
							case 2: 
									for(Member m:member)
									{
										m.displayMemberDetails();
									}
									break;
						}			
						break;
				case 2: login:
						System.out.println("Log in as....\n1->member\n2->guest\n\nTo be a member ....\n3->register");
						ch=in.nextInt();
						switch(ch)
						{
							case 1: Member m = new Member();
									m.logIn();
									System.out.println("ID     Brand     Model    Price      Quantity");
									System.out.println("Featured mobiles :");
									
									for(FeaturedMobile f:fmobile)
									{
										f.printDetails();
									}
									System.out.println("Smart mobiles  :");
									for(SmartMobile s:smobile)
									{
										s.printDetails();
									}
									System.out.println("enter the ID to see details:");
									i = in.nextInt();
									if (i<smobile.size()){
										smobile.get(i).displayMobile();
									}
									else
									{
										fmobile.get(i-smobile.size()).displayMobile();
									}
									/*System.out.println("\nEnter  \n 1->Browse another mobile \n 2-> add a mobile to cart\n choice :")
									ch= in.nextInt();
									switch(ch)
									{1
									}	*/
									System.out.println("enter the ID to add to cart:");
									i = in.nextInt();
									System.out.println("enter the quantity:");
									int q = in.nextInt();
									if (i<smobile.size()){
										m.addCartItem(smobile.get(i), q);
									}
									else
									{
										m.addCartItem(fmobile.get(i-sn),q);
									}
								  
									break;
							case 2: Guest g = new Guest();
									g.logIn();
									guest.add(g);
									System.out.println("ID     Brand     Model    Price      Quantity");
									System.out.println("Featured mobiles :");
									
									for(FeaturedMobile f:fmobile)
									{
										f.printDetails();
									}
									System.out.println("Smart mobiles  :");
									for(SmartMobile s:smobile)
									{
										s.printDetails();
									}
									System.out.println("enter the ID to see details:");
									i = in.nextInt();
									if (i<sn){
										smobile.get(i).displayMobile();
									}
									else
									{
										fmobile.get(i-smobile.size()).displayMobile();
									}
									/*System.out.println("\nEnter  \n 1->Browse another mobile \n 2-> add a mobile to cart\n choice :")
									ch= in.nextInt();
									switch(ch)
									{
									}	*/
									System.out.println("enter the ID to add to cart:");
									i = in.nextInt();
									System.out.println("enter the quantity:");
									q = in.nextInt();
									
									if (i<smobile.size()){
										g.addCartItem(smobile.get(i), q);
									}
									else
									{
										g.addCartItem(fmobile.get(i-smobile.size()),q);
									}
									break;
							case 3: Member n = new Member();
									n.signUp();
									member.add(n);
									continue;  
							default:System.out.println("illegal user");
									System.exit(0);
						}
						break;
				case 3:
				default : System.exit(0);
			}				
		}while(true);
	}	
}        