package ru.ikupdev.library.model.gbook;

import lombok.Data;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
@Data
public class GBookItem {
    private String kind;
    private String id;
    private GVolumeInfo volumeInfo;
    private GSaleInfo saleInfo;
    private GSearchInfo searchInfo;
    private GAccessInfo accessInfo;
    private GError error;
}
