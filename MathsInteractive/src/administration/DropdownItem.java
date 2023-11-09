//Author: Ian Hamilton - 15003706
package administration;

/**
 * Class to allow the addition of an optionID and unique option
 *
 * @author Ian Hamilton - 15003706
 */
public class DropdownItem {

    private final int optionID;
    private final String option;

    /**
     * DropdownItem class constructor
     * @param optionID
     * @param option 
     */
    public DropdownItem(int optionID, String option) {
        this.optionID = optionID;
        this.option = option;
    }

    /**
     * Return the option text instead of the option id
     *
     * @return option
     */
    @Override
    public String toString() {
        return option;
    }

    /**
     * Returns the optionID of the dropdown item
     *
     * @return optionID
     */
    public int getOptionID() {
        return optionID;
    }

    /**
     * Returns the option text of the dropdown item
     *
     * @return option
     */
    public String getOption() {
        return option;
    }

}
