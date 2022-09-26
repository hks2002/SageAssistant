/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-31 16:23:45
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 13:59:51
 * @FilePath       : \server\src\main\java\sageassistant\model\CustomerSummaryQty.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class CustomerSummaryQty extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String Site;
    private String CustomerCode;
    private String CountType;
    private int Qty;
}
