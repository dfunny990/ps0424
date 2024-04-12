package test;

import main.dto.ToolDTO;
import main.ToolRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToolRepositoryTest {

    private final ToolRepository toolRepository = new ToolRepository();

    @Test
    public void createCHNSObject() {
        ToolDTO actual = toolRepository.getToolByToolCode("CHNS");

        ToolDTO expected = ToolDTO.builder()
                .toolCode("CHNS")
                .toolType("Chainsaw")
                .brand("Stihl")
                .dailyCharge(1.99)
                .weekdayCharge(true)
                .weekendCharge(false)
                .holidayCharge(true)
                .build();
        assertEquals(expected, actual);
    }

    @Test
    public void createLADWObject() {
        ToolDTO actual = toolRepository.getToolByToolCode("LADW");

        ToolDTO expected = ToolDTO.builder()
                .toolCode("LADW")
                .toolType("Ladder")
                .brand("Werner")
                .dailyCharge(1.49)
                .weekdayCharge(true)
                .weekendCharge(true)
                .holidayCharge(false)
                .build();
        assertEquals(expected, actual);
    }

    @Test
    public void createJAKDObject() {
        ToolDTO actual = toolRepository.getToolByToolCode("JAKD");

        ToolDTO expected = ToolDTO.builder()
                .toolCode("JAKD")
                .toolType("Jackhammer")
                .brand("DeWalt")
                .dailyCharge(2.99)
                .weekdayCharge(true)
                .weekendCharge(false)
                .holidayCharge(false)
                .build();
        assertEquals(expected, actual);
    }

    @Test
    public void createJAKRObject() {
        ToolDTO actual = toolRepository.getToolByToolCode("JAKR");

        ToolDTO expected = ToolDTO.builder()
                .toolCode("JAKR")
                .toolType("Jackhammer")
                .brand("Ridgid")
                .dailyCharge(2.99)
                .weekdayCharge(true)
                .weekendCharge(false)
                .holidayCharge(false)
                .build();
        assertEquals(expected, actual);
    }

}