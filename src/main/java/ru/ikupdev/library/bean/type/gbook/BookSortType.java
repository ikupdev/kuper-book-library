package ru.ikupdev.library.bean.type.gbook;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
public enum BookSortType {
    /**
     * Returns results in order of the relevance of search terms (this is the default).
     */
    RELEVANCE,
    /**
     * Returns results in order of most recently to least recently published.
     */
    NEWEST;

    /**
     * Returns String parameter for google book API
     */
    public static String getParamName(BookSortType type) {
        if (type == null) return null;
        switch (type) {
            case RELEVANCE:
                return "relevance";
            case NEWEST:
                return "newest";
            default:
                return null;
        }
    }
}
