package utilities;



public class ParsingHelpers {
    public static final String NO_SUCH_ENUM = "There is no %s in %ss.";

    public static double tryParseDouble(String valueToParse, String errorMessage) {
        try {
            return Double.parseDouble(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
    public static int tryParseInteger(String valueToParse, String errorMessage) {
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(errorMessage, valueToParse));
        }
    }
    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type, String errorMessage) {
        try {
            return Enum.valueOf(type, valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(errorMessage, valueToParse));
        }
    }
}
