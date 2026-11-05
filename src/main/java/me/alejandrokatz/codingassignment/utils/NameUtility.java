package me.alejandrokatz.codingassignment.utils;

/*
Original implementation

Would not handle "Carmen Elizabeth Juanita Echo Sky Brava Cortez" from Spy kids
Would crash on empty strings/null values


public class NameUtility {
    public static String convertNameToInitials(String name) {
        int firstSpace = name.indexOf(" ");
        int lastSpace = name.lastIndexOf(" ");
        String firstName = name.substring(0, firstSpace);
        String middleName = name.substring(firstSpace, lastSpace);
        String lastName = name.substring(lastSpace);
        String initials = firstName + "." + middleName + "." + lastName + ".";
        return initials;
    }
}
*/

public class NameUtility {
    /**
     * Converts a full name into a string of initials
     *
     * @param input String that includes 0 to N words of a name
     * @return String representation of initials separated by periods, empty strings/null values return empty strings
     */
    public static String convertNameToInitials(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }

        String[] names = input.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            if (!name.isEmpty()) {
                sb.append(name.substring(0, 1).toUpperCase()).append(".");
            }
        }

        return sb.toString();
    }
}
