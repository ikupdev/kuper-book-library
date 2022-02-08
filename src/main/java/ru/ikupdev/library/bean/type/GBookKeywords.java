package ru.ikupdev.library.bean.type;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
public enum GBookKeywords {
    /**
     * Returns results where the text following this keyword is found in the title.
     */
    intitle,
    /**
     * Returns results where the text following this keyword is found in the author.
     */
    inauthor,
    /**
     * Returns results where the text following this keyword is found in the publisher.
     */
    inpublisher,
    /**
     * Returns results where the text following this keyword is listed in the category list of the volume.
     */
    subject,
    /**
     * Returns results where the text following this keyword is the ISBN number.
     */
    isbn,
    /**
     * Returns results where the text following this keyword is the Library of Congress Control Number.
     */
    lccn,
    /**
     * Returns results where the text following this keyword is the Online Computer Library Center number.
     */
    oclc
}
