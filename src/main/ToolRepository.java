package main;

import main.dto.ToolDTO;

public class ToolRepository {

    //private variables that represent what you get back from the database
    //// Query would be something like this below
    // select * from ToolsTable
    // FULL JOIN ToolTypesTable on ToolsTable.Tool_Type = ToolTypesTable.ToolType
    // where Tool_Type = <input>
    private final ToolDTO CHNS;
    private final ToolDTO LADW;
    private final ToolDTO JAKD;
    private final ToolDTO JAKR;
    public ToolRepository() {
        CHNS = createTool("CHNS");
        LADW = createTool("LADW");
        JAKD = createTool("JAKD");
        JAKR = createTool("JAKR");
    }

    public ToolDTO getToolByToolCode(String toolCode) {
        return switch (toolCode) {
            case "CHNS" -> CHNS;
            case "LADW" -> LADW;
            case "JAKD" -> JAKD;
            case "JAKR" -> JAKR;
            default -> null;
        };
    }

    private ToolDTO createTool(String input) {
        String toolType = "";
        String brand = "";
        double daily = 0;
        boolean weekday = true;
        boolean weekend = true;
        boolean holiday = true;

        if (input.startsWith("CHN")) {
            toolType = "Chainsaw";
            daily = 1.99;
            weekend = false;
        }
        else if (input.startsWith("LAD")) {
            toolType = "Ladder";
            daily = 1.49;
            holiday = false;
        }
        else if (input.startsWith("JAK")) {
            toolType = "Jackhammer";
            daily = 2.99;
            weekend = false;
            holiday = false;
        }
        if (input.endsWith("S")) {
            brand = "Stihl";
        }
        else if (input.endsWith("W")) {
            brand = "Werner";
        }
        else if (input.endsWith("D")) {
            brand = "DeWalt";
        }
        else if (input.endsWith("R")) {
            brand = "Ridgid";
        }
        return ToolDTO.builder()
                .toolCode(input)
                .toolType(toolType)
                .brand(brand)
                .dailyCharge(daily)
                .weekdayCharge(weekday)
                .weekendCharge(weekend)
                .holidayCharge(holiday)
                .build();
    }


}
