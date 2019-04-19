public class ArduinoComunication {
	private Arduino _aduino;
	private String _comport;
	
	public ArduinoComunication(String comPort)
	{
		_comport = comPort;
		_aduino = new Arduino();
		_aduino.setPortDescription(_comport);
		_aduino.setBaudRate(19200);
	}
	
	private void OpenConnection()
	{
		_aduino.openConnection();
	}
	private void CloseConnection()
	{
		_aduino.closeConnection();
	}
	
	public String GetTemparature()
	{
		System.out.println("waiting...");
		OpenConnection();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_aduino.serialWrite(ArduinoFunction.ReadTemparature);
		String result = _aduino.serialRead(1);
		CloseConnection();
		return result;
	}
	
	public String GetHumidity()
	{
		System.out.println("waiting...");
		OpenConnection();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_aduino.serialWrite(ArduinoFunction.ReadHumidity);
		
		
		 String result = _aduino.serialRead(1);
		CloseConnection();
		return result;
	}
	
	public void TurnOnLED()
	{
		System.out.println("waiting...");
		OpenConnection();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_aduino.serialWrite(ArduinoFunction.OnLED);
		CloseConnection();
	}
	
	public void TurnOffLED()
	{
		System.out.println("waiting...");
		OpenConnection();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_aduino.serialWrite(ArduinoFunction.OffLED);
		CloseConnection();
	}
	
	public void BlinkLED()
	{
		System.out.println("waiting...");
		OpenConnection();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_aduino.serialWrite(ArduinoFunction.BlinkLED);
		System.out.println(_aduino.serialRead(0));
		CloseConnection();
	}
}



