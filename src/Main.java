import main.HammerBarnPOSService;
import main.ToolRepository;
import main.dto.RentalAgreement;

public class Main {
    public static void main(String[] args) {
        HammerBarnPOSService hammerBarnPOSService = new HammerBarnPOSService(new ToolRepository());
        System.out.println("Welcome to Hammer Barn! Auto-Checkout Commencing!");
        System.out.println("Here's your Ladder!");
        RentalAgreement agreement = hammerBarnPOSService.Checkout("LADW",3,10,"07/02/20");

        System.out.println("Have a great Day!");
    }


}