package ru.ikupdev.library.bean.type.gbook;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
public enum KeywordType {
    /**
     * Returns results where the text following this keyword is found in the title.
     */
    INTITLE,
    /**
     * Returns results where the text following this keyword is found in the author.
     */
    INAUTHOR,
    /**
     * Returns results where the text following this keyword is found in the publisher.
     */
    INPUBLISHER,
    /**
     * Returns results where the text following this keyword is listed in the category list of the volume.
     */
    SUBJECT,
    /**
     * Returns results where the text following this keyword is the ISBN number.
     */
    ISBN,
    /**
     * Returns results where the text following this keyword is the Library of Congress Control Number.
     */
    LCCN,
    /**
     * Returns results where the text following this keyword is the Online Computer Library Center number.
     */
    OCLC;

    /**
     * Returns String parameter for google book API
     */
    public static String getParamName(KeywordType type) {
        if (type == null) return null;
        switch (type) {
            case INTITLE:
                return "intitle";
            case INAUTHOR:
                return "inauthor";
            case INPUBLISHER:
                return "inpublisher";
            case SUBJECT:
                return "subject";
            case ISBN:
                return "isbn";
            case LCCN:
                return "lccn";
            case OCLC:
                return "oclc";
            default:
                return null;
        }
    }
}
