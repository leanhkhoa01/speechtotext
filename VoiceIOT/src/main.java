import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class main {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		ArduinoComunication comunication = new ArduinoComunication("COM5");

		Configuration configuration = new Configuration();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input method choice:\n1-Arduino\nanother-Digits");
		String input = sc.nextLine();
		

		if(input.equals("1"))
		{
			configuration.setAcousticModelPath("model/vi");

			configuration.setDictionaryPath("model/cmudict-vi.dict");

			configuration.setLanguageModelPath("model/vi.lm.bin");
			
		}
		else
		{
			
			configuration.setAcousticModelPath("model_digit/digit");

			configuration.setDictionaryPath("model_digit/uit.dict");

			configuration.setLanguageModelPath("model_digit/uit.lm.bin");
		}
		

//		StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
//
//		InputStream stream = new FileInputStream(new File("bon_01.wav"));
//
//		recognizer.startRecognition(stream);
//
//		SpeechResult result;
//		
//		if ((result = recognizer.getResult()) != null) {
//
//			System.out.format("Hypothesis: %s\n", result.getHypothesis());
//			RunArduinoFunction(result.getHypothesis(), comunication);
//		}
//		recognizer.stopRecognition();

		LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);


		recognizer.startRecognition(true);

		SpeechResult result;
		while ((result = recognizer.getResult()) != null) {

			System.out.format("Hypothesis: %s\n", result.getHypothesis());
			if(input.equals("1"))
				RunArduinoFunction(result.getHypothesis(), comunication);
		}

		recognizer.stopRecognition();

	}

	private static void RunArduinoFunction(String input, ArduinoComunication arduino) throws InterruptedException {
		if (input.contains("NHIETDO"))
			System.out.println("Current temparature: " + arduino.GetTemparature());
		else if (input.contains("DOAM"))
			System.out.println("Current humidity: " + arduino.GetHumidity());
		else if (input.contains("BATLED"))
			arduino.TurnOnLED();
		else if (input.contains("TATLED"))
			arduino.TurnOffLED();
		else if (input.contains("LEDNHAPNHAY"))
			arduino.BlinkLED();

	}
}
