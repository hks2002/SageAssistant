/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-06-27 14:02:26
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 13:59:13
 * @FilePath       : \server\src\main\java\sageassistant\model\CurrencyHistory.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class CurrencyHistory extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String Sour;
    private String Dest;
    private Float Rate;
    private Date StartDate;
    private Date EndDate;
}
