/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:03:01
 * @FilePath       : \server\src\main\java\sageassistant\model\StockInfo.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class StockInfo extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String StockSite;
    private String PN;
    private Integer Qty;
}
