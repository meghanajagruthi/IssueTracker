package com.elecnor.issue.tracker.util;

import java.io.InputStream;
import java.util.Properties;


public class PropertyFileReader {

	/**
	 * @param args
	 */
	private static Properties properties ;
	private static PropertyFileReader factory ;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		getInstance();
	}
	

	private PropertyFileReader() throws Exception {
		// TODO Auto-generated constructor stub
	}
	
	public static PropertyFileReader getInstance() throws Exception{
		if( factory == null )
		{
				synchronized( PropertyFileReader.class ){
					if( factory == null )
					{
						PropertyFileReader temp = new PropertyFileReader() ;
						PropertyFileReader.initialize() ;
						factory = temp ;											
					}
				}
		}
		return factory ;
	}
	
	public static InputStream getFileFromClassPath(String fileName) {
		InputStream in;
		ClassLoader cl = PropertyFileReader.class.getClassLoader();
		if (cl == null) {
			in = ClassLoader.getSystemResourceAsStream(fileName);
			System.out.println("Inside cl == null");
		} else {
			in = cl.getResourceAsStream(fileName);
			System.out.println("Inside cl !!== null");
		}
		return in;
	}
	
	private static void initialize()throws Exception {
		  
		  String envMode = System.getenv("ENV_MODE");
		  String fileName = envMode + ".configuration.properties";
		  
		  InputStream in = getFileFromClassPath(fileName) ;
		  if(in == null){
		   System.out.println("null");
		  }
		  properties = new Properties() ;
		  properties.load(in);
		  System.out.println("\n\n\n === Property File Loaded.." + properties);
		    
		  in.close();
		 }
	public Object getObjectProperty(String name ) throws Exception{

		Object property = properties.get( name ) ;
		if(property == null )
			throw new Exception( "Property:"+name+": not found") ;
		return property ;
	}	
	
	public String getStringProperty(String name ) throws Exception{
		Object property = getObjectProperty( name ) ;
		if(!( property instanceof java.lang.String ) )
			throw new Exception( "Property:"+name+":is not string type:"+property.getClass().getName()) ;
		return (String)property ;
	}

		
	private boolean toBoolean( String name )  { 
		return ((name != null) && name.equalsIgnoreCase("true"));
	}	
		
	public boolean getBooleanProperty(String name ) throws Exception{
		String property = getStringProperty( name ) ;
		if( !( property.equalsIgnoreCase("true") || property.equalsIgnoreCase("false") ) )
			throw new Exception( "Property:"+name+":is not boolean type:"+property ) ;
		return  toBoolean( property ) ;
	}
	
	public int getIntProperty(String name ) throws Exception{
		String property = getStringProperty( name ) ;
		try{
			int intVal = Integer.parseInt( property ) ;
			return intVal ;
		}
		catch( Exception e ){
			throw new Exception( "Property:"+name+":is not int type:"+property+":"+e.toString() ) ;
		}
	}
}
