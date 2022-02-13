package ru.ikupdev.library.bean.type.gbook;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
public enum BookSaleType {
    /**
     * Returns results where at least parts of the text are previewable.
     */
    PARTIAL,
    /**
     * Only returns results where all of the text is viewable.
     */
    FULL,
    /**
     * Only returns results that are free Google eBooks.
     */
    FREE_EBOOKS,
    /**
     * Only returns results that are Google eBooks with a price.
     */
    PAID_EBOOKS,
    /**
     * Only returns results that are Google eBooks, paid or free.
     * Examples of non-eBooks would be publisher content that is available in limited preview and not for sale, or magazines.
     */
    EBOOKS;

    /**
     * Returns String parameter for google book API
     */
    public static String getParamName(BookSaleType type) {
        if (type == null) return null;
        switch (type) {
            case PARTIAL:
                return "partial";
            case FULL:
                return "full";
            case FREE_EBOOKS:
                return "free-ebooks";
            case PAID_EBOOKS:
                return "paid-ebooks";
            case EBOOKS:
                return "ebooks";
            default:
                return null;
        }
    }
}
