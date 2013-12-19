package model;
//Singleton Class Database Connection
@Deprecated
public class DataBaseConnectionProperties
{	private static DataBaseConnectionProperties dbcp;
	private String driver;
	private String databaseLoc;
	private String dbUserName="root";
	private String dbPassword="root";
	
	private DataBaseConnectionProperties()
	{}
	private DataBaseConnectionProperties(String driver,String databaseLoc)
	{
		this.driver=driver;
		this.databaseLoc=databaseLoc;
	}
	public static synchronized DataBaseConnectionProperties getInstance()
	{
		if(dbcp==null){
				
				dbcp=new DataBaseConnectionProperties();
				return dbcp;
		}
		else
			{System.out.println("DBCP is NOT null");
			return dbcp;
			}
	}
	
	public String getDriver() {
		return driver;
	}
	public String getDatabaseLoc() {
		return databaseLoc;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public void setDatabaseLoc(String databaseLoc) {
		this.databaseLoc = databaseLoc;
	}
}
