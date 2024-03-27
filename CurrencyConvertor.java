import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConvertor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Step 1: Currency Selection
        System.out.println("Enter the base currency (e.g., USD, EUR, GBP):");
        String baseCurrency = scanner.nextLine().toUpperCase();
        
        System.out.println("Enter the target currency (e.g., USD, EUR, GBP):");
        String targetCurrency = scanner.nextLine().toUpperCase();
        
        // Step 2: Currency Rates (Fetching real-time exchange rates from the API)
        double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);
        
        if (exchangeRate == -1) {
            System.out.println("Failed to fetch exchange rate. Please try again later.");
            return;
        }
        
        // Step 3: Amount Input
        System.out.println("Enter the amount to convert from " + baseCurrency + " to " + targetCurrency + ":");
        double amount = scanner.nextDouble();
        
        // Step 4: Currency Conversion
        double convertedAmount = amount * exchangeRate;
        
        // Step 5: Display Result
        System.out.println("Converted Amount: " + convertedAmount + " " + targetCurrency);
        
        scanner.close();
    }
    
    public static double fetchExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            URL url = new URL("https://open.er-api.com/v6/latest/" + baseCurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject rates = jsonResponse.getJSONObject("rates");
            
            return rates.getDouble(targetCurrency);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

