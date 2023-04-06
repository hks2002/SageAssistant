/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-09-21 09:17:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:19:20                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/AnalysesPurchase.java                          *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model;

import com.da.sage.assistant.model.base.ModelTemplate;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalysesPurchase extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String ProjectNO;
    private String PurchaseNO;
    private Double NetPrice;
    private Date ProjectDate;
    private Date PurchaseDate;
    private Integer ROWNUM;
}
