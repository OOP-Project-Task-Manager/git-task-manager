package utilities;



public class ParsingHelpers {
//
//    private static final String INVALID_NUMBER_FIELD_MESSAGE = "Invalid value for %s. Should be a number.";
//    private static final String INVALID_BOOLEAN_FIELD_MESSAGE = "Invalid value for %s. Should be one of 'true' or 'false'.";
//
//    public static double tryParseDouble(String valueToParse, String parameterName) {
//        try {
//            return Double.parseDouble(valueToParse);
//        } catch (NumberFormatException e) {
//            throw new InvalidUserInputException(String.format(INVALID_NUMBER_FIELD_MESSAGE, parameterName));
//        }
//    }
//
//    public static int tryParseInteger(String valueToParse, String parameterName) {
//        try {
//            return Integer.parseInt(valueToParse);
//        } catch (NumberFormatException e) {
//            throw new InvalidUserInputException(String.format(INVALID_NUMBER_FIELD_MESSAGE, parameterName));
//        }
//    }
//
//
//
    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type, String errorMessage) {
        try {
            return Enum.valueOf(type, valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(errorMessage, valueToParse));
        }
    }
//
}
