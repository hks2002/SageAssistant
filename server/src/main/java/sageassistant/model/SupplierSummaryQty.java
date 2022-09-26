/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:30
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:03:59
 * @FilePath       : \server\src\main\java\sageassistant\model\SupplierSummaryQty.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class SupplierSummaryQty extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String Site;
    private String SupplierCode;
    private String CountType;
    private int Qty;
}
